package br.com.draftpad.controller;

import br.com.draftpad.service.user.IUserService;
import br.com.draftpad.service.user.request.RequestUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "User", description = "Endpoints for Managing User")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody RequestUser requestUser) {
        return service.createUser(requestUser);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteUser() {
        return service.deleteUser();
    }

    @PatchMapping()
    public ResponseEntity<?> editUser(@RequestBody RequestUser requestUser) {
        return service.editUser(requestUser);
    }

    @GetMapping()
    public ResponseEntity<?> getUsers() {
        return service.getUsers();
    }

    @PatchMapping(value = "permission/forUser/{id}")
    public ResponseEntity<?> userPermission(@PathVariable("id") UUID id) {
        return service.userPermission(id);
    }

    @PatchMapping(value = "permission/forModerator/{id}")
    public ResponseEntity<?> moderatorPermission(@PathVariable("id") UUID id) {
        return service.moderatorPermission(id);
    }
}
