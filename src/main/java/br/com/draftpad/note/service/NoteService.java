package br.com.draftpad.note.service;

import br.com.draftpad.note.model.entity.Note;
import br.com.draftpad.note.model.request.RequestCreatingNote;
import br.com.draftpad.note.model.response.ResponseGetNotes;
import br.com.draftpad.note.repository.INoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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
            List<Note> notes = repository.findAll();
            if (notes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(getListResponseGetNotes(notes));
        } catch (Exception e) {
            String errorMessage = "Failed to get notes: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
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
}
