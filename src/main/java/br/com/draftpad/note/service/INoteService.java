package br.com.draftpad.note.service;

import br.com.draftpad.note.model.request.RequestCreatingNote;
import org.springframework.http.ResponseEntity;

public interface INoteService {
    ResponseEntity<?> createNote(RequestCreatingNote requestCreatingNote);

    ResponseEntity<?> getNotes();
}
