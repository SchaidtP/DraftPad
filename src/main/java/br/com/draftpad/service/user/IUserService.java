package br.com.draftpad.service.user;

import br.com.draftpad.service.user.request.RequestUser;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<?> createUser(RequestUser requestUser);

    ResponseEntity<?> deleteUser();

    ResponseEntity<?> editUser(RequestUser requestUser);

    ResponseEntity<?> getUsers();
}
