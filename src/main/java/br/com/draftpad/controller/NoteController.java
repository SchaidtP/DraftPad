package br.com.draftpad.controller;

import br.com.draftpad.service.note.request.RequestCreatingNote;
import br.com.draftpad.service.note.request.RequestEditNote;
import br.com.draftpad.service.note.response.ResponseGetNotes;
import br.com.draftpad.service.note.INoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Note", description = "Endpoints for Managing Note")
@RestController
@RequestMapping("/api/v1/note")
public class NoteController {

    @Autowired
    private INoteService service;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Adds a new Note",
            description = "Adds a new Note by passing in a Json representation of the note!",
            tags = {"Note"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> createNote(@RequestBody RequestCreatingNote requestCreatingNote) {
        return service.createNote(requestCreatingNote);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Finds all Notes", description = "Finds all Notes", tags = {"Note"},
               responses = {
                       @ApiResponse(description = "OK", responseCode = "200",
                            content = {
                               @Content(mediaType = "Application/json",
                                       array = @ArraySchema(schema = @Schema(implementation = ResponseGetNotes.class)))
                            }),
                       @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                       @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                       @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                       @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
               })
    public ResponseEntity<?> getNotes() {
        return service.getNotes();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Note",
            description = "Deletes a Note by passing in a Json representation of the note!",
            tags = {"Note"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> deleteNote(@PathVariable("id") UUID id) {
        return service.deleteNote(id);
    }

    @PatchMapping(
            value = "{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Disable a specific Note by your ID", description = "Disable a specific Note by your ID", tags = {"Note"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> editNote(@PathVariable("id") UUID id, @RequestBody RequestEditNote requestEditNote) {
        return  service.editNote(id, requestEditNote);
    }
}
