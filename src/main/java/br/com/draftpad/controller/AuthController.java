package br.com.draftpad.controller;

import br.com.draftpad.service.auth.IAuthService;
import br.com.draftpad.service.auth.request.RequestAccountCredentials;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    IAuthService authService;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody RequestAccountCredentials requestAccountCredentials) {
        return authService.signin(requestAccountCredentials);
    }
}
