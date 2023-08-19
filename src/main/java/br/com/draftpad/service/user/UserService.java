package br.com.draftpad.service.user;

import br.com.draftpad.model.entity.User;
import br.com.draftpad.repository.IUserRepository;
import br.com.draftpad.service.permission.IPermissionService;
import br.com.draftpad.service.user.request.RequestCreatingUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    IPermissionService iPermissionService;

    @Override
    public ResponseEntity<?> createUser(RequestCreatingUser requestCreatingUser) {
        if(repository.findByUserName(requestCreatingUser.getUserName()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is already a user with the same username.");
        } else if (checkIfParamsIsNotNull(requestCreatingUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some parameters are null or empty.");
        }
        try {
            var permissions = iPermissionService.getPermissionUser();
            var password = encryptPassword(requestCreatingUser.getPassword());
            var user = new User(requestCreatingUser.getUserName(), password, permissions);
            repository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            String errorMessage = "Failed to create note: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    private boolean checkIfParamsIsNotNull(RequestCreatingUser requestCreatingUser) {
        return requestCreatingUser == null
                || requestCreatingUser.getUserName() == null || requestCreatingUser.getPassword() == null
                || requestCreatingUser.getUserName().isBlank() || requestCreatingUser.getPassword().isBlank();
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

