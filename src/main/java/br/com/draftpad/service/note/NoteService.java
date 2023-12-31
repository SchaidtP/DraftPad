package br.com.draftpad.service.note;

import br.com.draftpad.domain.user.User;
import br.com.draftpad.service.note.request.RequestCreatingNote;
import br.com.draftpad.service.note.request.RequestEditNote;
import br.com.draftpad.service.note.response.ResponseGetNotes;
import br.com.draftpad.domain.note.Note;
import br.com.draftpad.repository.INoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NoteService implements INoteService{

    @Autowired
    private INoteRepository repository;

    @Override
    public ResponseEntity<?> createNote(RequestCreatingNote requestCreatingNote) {
        var note = new Note(requestCreatingNote.getTitle(), requestCreatingNote.getDescription());
        try {
         repository.save(note);
         return ResponseEntity.status(HttpStatus.CREATED).body("Note created successfully");
        } catch (Exception e) {
            String errorMessage = "Failed to create note: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @Override
    public ResponseEntity<?> getNotes() {
        try {
            var user = this.getAuthenticatedUser();
            List<Note> notes = repository.findAllByUserId(user.getId()).orElse(new ArrayList<>());
            if (notes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(getListResponseGetNotes(notes));
        } catch (Exception e) {
            String errorMessage = "Failed to get notes: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @Override
    public ResponseEntity<?> deleteNote(UUID id) {
        var note = repository.findById(id).orElse(null);
        var userName = this.getAuthenticatedUser().getUsername();
        if (note != null && Objects.equals(note.getUser().getUsername(), userName)){
            try {
                repository.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully");
            } catch (Exception e) {
                String errorMessage = "Failed to get notes: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
            }
        } else {
            String errorMessage = "Id: " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @Override
    public ResponseEntity<?> editNote(UUID id, RequestEditNote requestEditNote) {
        var note = repository.findById(id).orElse(null);
        var userName = this.getAuthenticatedUser().getUsername();
        if (note != null && Objects.equals(note.getUser().getUsername(), userName)){
            if (requestEditNote.getTitle() != null ){
                note.setTitle(requestEditNote.getTitle());
            }
            if (requestEditNote.getDescription() != null ){
                note.setDescription(requestEditNote.getDescription());
            }
            try {
                repository.save(note);
                return ResponseEntity.status(HttpStatus.OK).body("Note successfully edited");
            } catch (Exception e) {
                String errorMessage = "Failed to create note: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
            }
        } else {
            String errorMessage = "Id: " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    private List<ResponseGetNotes> getListResponseGetNotes(List<Note> notes) {
        return notes.stream()
                .map(note -> {
                    return new ResponseGetNotes(
                            note.getId(),
                            note.getTitle(),
                            note.getDescription(),
                            note.getPublicationDate()
                    );
                }).collect(Collectors.toList());
    }

    private User getAuthenticatedUser() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
