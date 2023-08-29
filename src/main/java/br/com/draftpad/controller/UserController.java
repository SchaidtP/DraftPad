package br.com.draftpad.controller;

import br.com.draftpad.service.note.response.ResponseGetNotes;
import br.com.draftpad.service.user.IUserService;
import br.com.draftpad.service.user.request.RequestUser;
import br.com.draftpad.service.user.response.ResponseGetUsers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "User", description = "Endpoints for Managing User")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Adds a new User",
            description = "Adds a new User by passing in a Json representation of the user!",
            tags = {"User", "Authentication"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> createUser(@RequestBody RequestUser requestUser) {
        return service.createUser(requestUser);
    }

    @DeleteMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Deletes a User",
            description = "Deletes a User by passing in a Json representation of the user!",
            tags = {"User"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> deleteUser() {
        return service.deleteUser();
    }

    @PatchMapping(produces = {MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Edit User",
            description = "Modify the user's own data", tags = {"User"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> editUser(@RequestBody RequestUser requestUser) {
        return service.editUser(requestUser);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Finds all Users", description = "Finds all Users", tags = {"User"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200",
                            content = {
                                    @Content(mediaType = "Application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = ResponseGetUsers.class)))
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> getUsers() {
        return service.getUsers();
    }

    @PatchMapping(value = "permission/forUser/{id}",
            produces = {MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Modify permission for user",
            description = "Modify permission for user through ID", tags = {"User"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> userPermission(@PathVariable("id") UUID id) {
        return service.userPermission(id);
    }

    @PatchMapping(value = "permission/forModerator/{id}",
            produces = {MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Modify permission for moderator",
            description = "Modify permission for moderator through ID", tags = {"User"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> moderatorPermission(@PathVariable("id") UUID id) {
        return service.moderatorPermission(id);
    }
}
