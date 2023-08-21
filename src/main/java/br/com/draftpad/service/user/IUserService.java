package br.com.draftpad.service.user;

import br.com.draftpad.model.entity.Permission;
import br.com.draftpad.model.entity.User;
import br.com.draftpad.service.user.request.RequestUser;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    ResponseEntity<?> createUser(RequestUser requestUser);

    ResponseEntity<?> deleteUser();

    ResponseEntity<?> editUser(RequestUser requestUser);

    ResponseEntity<?> getUsers();

    ResponseEntity<?> userPermission(UUID id);

    ResponseEntity<?> moderatorPermission(UUID id);
}
