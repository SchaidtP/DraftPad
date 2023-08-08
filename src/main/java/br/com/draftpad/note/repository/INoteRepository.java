package br.com.draftpad.note.repository;

import br.com.draftpad.note.model.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface INoteRepository extends JpaRepository<Note, UUID> {
}
