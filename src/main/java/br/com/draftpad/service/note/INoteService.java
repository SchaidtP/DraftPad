package br.com.draftpad.service.note;

import br.com.draftpad.service.note.request.RequestCreatingNote;
import br.com.draftpad.service.note.request.RequestEditNote;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface INoteService {
    ResponseEntity<?> createNote(RequestCreatingNote requestCreatingNote);

    ResponseEntity<?> getNotes();

    ResponseEntity<?> deleteNote(UUID id);

    ResponseEntity<?> editNote(UUID id, RequestEditNote requestEditNote);
}
