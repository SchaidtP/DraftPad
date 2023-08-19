package br.com.draftpad.model.entity;

import jakarta.persistence.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime publicationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Note() {
    }

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
        this.publicationDate = LocalDateTime.now();
        this.user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
