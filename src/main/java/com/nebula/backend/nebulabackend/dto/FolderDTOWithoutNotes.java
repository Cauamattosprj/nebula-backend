package com.nebula.backend.nebulabackend.dto;

import java.time.Instant;
import java.util.UUID;

import com.nebula.backend.nebulabackend.model.Folder;

public class FolderDTOWithoutNotes {
    private UUID id;
    private String title;
    private Instant createdAt;
    private boolean isDeleted;
    private Instant deletedAt;

    public FolderDTOWithoutNotes(Folder folder) {
        this.id = folder.getId();
        this.title = folder.getTitle();
        this.createdAt = folder.getCreatedAt();
        this.isDeleted = folder.isDeleted();
        this.deletedAt = folder.getDeletedAt();
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

}
