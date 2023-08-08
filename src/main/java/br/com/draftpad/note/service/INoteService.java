package br.com.draftpad.note.service;

import br.com.draftpad.note.model.request.RequestCreatingNote;
import br.com.draftpad.note.model.request.RequestEditNote;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface INoteService {
    ResponseEntity<?> createNote(RequestCreatingNote requestCreatingNote);

    ResponseEntity<?> getNotes();

    ResponseEntity<?> deleteNote(UUID id);

    ResponseEntity<?> editNote(UUID id, RequestEditNote requestEditNote);
}
