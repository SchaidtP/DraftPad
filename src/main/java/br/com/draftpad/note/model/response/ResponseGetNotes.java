package br.com.draftpad.note.model.response;

import java.time.LocalDateTime;
import java.util.UUID;

public class ResponseGetNotes {
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime publicationDate;

    public ResponseGetNotes(UUID id, String title, String description, LocalDateTime publicationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
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
