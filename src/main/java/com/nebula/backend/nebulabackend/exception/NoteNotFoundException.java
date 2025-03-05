package com.nebula.backend.nebulabackend.exception;

import java.util.UUID;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(UUID id) {
        super(String.format("Note with id %s not found", id));
    }
}