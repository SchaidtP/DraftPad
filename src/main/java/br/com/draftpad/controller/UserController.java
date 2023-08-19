package br.com.draftpad.controller;

import br.com.draftpad.service.user.IUserService;
import br.com.draftpad.service.user.request.RequestCreatingUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "Endpoints for Managing User")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping()
    public ResponseEntity<?> createUser(RequestCreatingUser requestCreatingUser) {
        return service.createUser(requestCreatingUser);
    }
}
