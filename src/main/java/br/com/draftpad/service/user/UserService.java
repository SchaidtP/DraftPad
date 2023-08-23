package br.com.draftpad.service.user;

import br.com.draftpad.model.entity.Permission;
import br.com.draftpad.model.entity.User;
import br.com.draftpad.repository.IUserRepository;
import br.com.draftpad.service.permission.IPermissionService;
import br.com.draftpad.service.user.request.RequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    IPermissionService iPermissionService;

    @Override
    public ResponseEntity<?> createUser(RequestUser requestUser) {
        if(repository.findByUserName(requestUser.getUserName()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is already a user with the same username.");
        } else if (checkIfParamsIsNotNull(requestUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some parameters are null or empty.");
        }
        try {
            var password = encryptPassword(requestUser.getPassword());
            var user = new User(requestUser.getUserName(), password);
            user.addPermission(iPermissionService.getPermissionUser());
            repository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            String errorMessage = "Failed to create user: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @Override
    public ResponseEntity<?> deleteUser() {
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        //if (user.seekPermission("ADMIN")) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not allowed");
        try {
            repository.delete(user);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            String errorMessage = "Failed to delete user: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @Override
    public ResponseEntity<?> editUser(RequestUser requestUser) {
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if(requestUser.getUserName() != null && !requestUser.getUserName().isBlank()
                && requestUser.getUserName() != user.getUsername()) {
            user.setUserName(requestUser.getUserName());
        }

        if(requestUser.getPassword() != null && !requestUser.getPassword().isBlank()
                && requestUser.getPassword() != user.getPassword()) {
            var password = encryptPassword(requestUser.getPassword());
            user.setPassword(password);
        }

        try {
            repository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("User successfully edited");
        } catch (Exception e) {
            String errorMessage = "Failed to edit user: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @Override
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @Override
    public ResponseEntity<?> userPermission(UUID id) {
        String errorMessage;
        var editedUser = repository.findById(id).orElse(null);
        if (editedUser == null) {
            errorMessage = "User not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (user.seekPermission("ADMIN") && user.getId() != id && !editedUser.seekPermission("ADMIN")) {
            try {
                List<Permission> permissions = new ArrayList<>();
                var permission = iPermissionService.getPermissionUser();
                permissions.add(permission);
                editedUser.setPermissions(permissions);
                repository.save(editedUser);
                return ResponseEntity.status(HttpStatus.OK).body("Success when changing user permission");
            } catch (Exception e) {
                errorMessage = "Failed to change permission: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
            }
        }
        errorMessage = "unauthorized moderator";
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    @Override
    public ResponseEntity<?> moderatorPermission(UUID id) {
        String errorMessage;
        var editedUser = repository.findById(id).orElse(null);
        if (editedUser == null) {
            errorMessage = "User not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (user.seekPermission("ADMIN") || user.seekPermission("MODERATOR")
                && user.getId() != id && !editedUser.seekPermission("ADMIN")) {
            try {
                var permission = iPermissionService.getPermissionModerator();
                editedUser.addPermission(permission);
                repository.save(editedUser);
                return ResponseEntity.status(HttpStatus.OK).body("Success when changing user permission");
            } catch (Exception e) {
                errorMessage = "Failed to change permission: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
            }
        }
        errorMessage = "unauthorized moderator";
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    private boolean checkIfParamsIsNotNull(RequestUser requestUser) {
        return requestUser == null
                || requestUser.getUserName() == null || requestUser.getPassword() == null
                || requestUser.getUserName().isBlank() || requestUser.getPassword().isBlank();
    }

    private String encryptPassword(String password) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        Pbkdf2PasswordEncoder pbkdf2Encoder =
                new Pbkdf2PasswordEncoder(
                        "", 8, 185000,
                        Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

        String hashedPassword = pbkdf2Encoder.encode(password);
        return hashedPassword.replace("{pbkdf2}", "");
    }
}

