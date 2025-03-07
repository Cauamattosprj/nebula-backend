package com.nebula.backend.nebulabackend.exception;

public class DuplicateTitleException extends RuntimeException {
    public DuplicateTitleException(String title) {
        super("A entity with this title already exists: " + title);
    }
}
