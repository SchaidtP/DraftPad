package br.com.draftpad.note.controller;

import br.com.draftpad.note.model.request.RequestCreatingNote;
import br.com.draftpad.note.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {

    @Autowired
    private INoteService service;

    @PostMapping()
    public ResponseEntity<?> createNote(@RequestBody RequestCreatingNote requestCreatingNote) {
        return service.createNote(requestCreatingNote);
    }

    @GetMapping()
    public ResponseEntity<?> getNotes() {
        return service.getNotes();
    }
}
