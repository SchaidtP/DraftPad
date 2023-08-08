package br.com.draftpad.repository;

import br.com.draftpad.model.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface INoteRepository extends JpaRepository<Note, UUID> {
}
