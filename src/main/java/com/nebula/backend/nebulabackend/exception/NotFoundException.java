package com.nebula.backend.nebulabackend.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super(String.format("Entity with id %s not found", id));
    }
}