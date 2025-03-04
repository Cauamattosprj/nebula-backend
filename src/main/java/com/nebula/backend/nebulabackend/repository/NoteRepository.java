package com.nebula.backend.nebulabackend.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nebula.backend.nebulabackend.model.Note;

public interface NoteRepository extends JpaRepository<Note, UUID> {
    
}
