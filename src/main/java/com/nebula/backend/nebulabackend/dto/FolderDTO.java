package com.nebula.backend.nebulabackend.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.nebula.backend.nebulabackend.model.Folder;

public class FolderDTO {
    private UUID id;
    private String title;
    private List<NoteDTO> notes = new ArrayList<>();
    private Instant createdAt;
    private boolean isDeleted;
    private Instant deletedAt;

    public FolderDTO(Folder folder) {
        this.id = folder.getId();
        this.title = folder.getTitle();
        if (folder.getNotes() != null) {
            this.notes = folder.getNotes().stream().map(NoteDTO::new).toList();
        }
        this.createdAt = folder.getCreatedAt();
        this.isDeleted = folder.isDeleted();
        if (folder.getDeletedAt() != null) {
            this.deletedAt = folder.getDeletedAt();
        }
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

    public List<NoteDTO> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteDTO> notes) {
        this.notes = notes;
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
