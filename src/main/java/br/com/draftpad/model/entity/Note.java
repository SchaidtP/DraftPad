package br.com.draftpad.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Note {

    @Id
    @Column(unique = true)
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime publicationDate;

    public Note() {
    }

    public Note(String title, String description) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.publicationDate = LocalDateTime.now();
    }

    public Note(String title, String description, LocalDateTime publicationDate) {
        this.title = title;
        this.description = description;
        this.publicationDate = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }
}
