package br.com.draftpad.controller;

import br.com.draftpad.service.auth.IAuthService;
import br.com.draftpad.service.auth.request.RequestAccountCredentials;
import br.com.draftpad.service.auth.response.ResponseToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Endpoints for Managing Authentication")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    IAuthService authService;

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/signin",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "User Authentication",
            description = "Authenticate a user and return some of his data",
            tags = {"Authentication"},
            responses = {
                @ApiResponse(description = "OK", responseCode = "200",
                        content = @Content(schema = @Schema(implementation = ResponseToken.class))
                ),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity signin(@RequestBody RequestAccountCredentials requestAccountCredentials) {
        return authService.signin(requestAccountCredentials);
    }
}
