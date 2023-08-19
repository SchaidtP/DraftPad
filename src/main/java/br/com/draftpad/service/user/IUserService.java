package br.com.draftpad.service.user;

import br.com.draftpad.service.user.request.RequestCreatingUser;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<?> createUser(RequestCreatingUser requestCreatingUser);
}
