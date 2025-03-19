package com.nebula.backend.nebulabackend.dto;

import java.time.Instant;
import java.util.UUID;

import com.nebula.backend.nebulabackend.model.Note;

public class NoteDTO {
    private UUID id;
    private String title;
    private String body;
    private UUID userId;
    private FolderDTOWithoutNotes folder;
    private Instant createdAt;
    private boolean isDeleted;
    private Instant deletedAt;

    public NoteDTO(Note note) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.body = note.getBody();
        this.userId = note.getUserId();
        try {
            this.folder = new FolderDTOWithoutNotes(note.getFolder());
        } catch (NullPointerException e) {
            this.folder = null;
        }
        this.createdAt = note.getCreatedAt();
        this.isDeleted = note.isDeleted();
        this.deletedAt = note.getDeletedAt();
    }

    // getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public FolderDTOWithoutNotes getFolder() {
        return folder;
    }

    public void setFolder(FolderDTOWithoutNotes folder) {
        this.folder = folder;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    // getters and setters

}
