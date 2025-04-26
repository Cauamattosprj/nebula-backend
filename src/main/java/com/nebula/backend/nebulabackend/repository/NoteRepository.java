package com.nebula.backend.nebulabackend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nebula.backend.nebulabackend.model.Note;
import org.springframework.data.jpa.repository.Query;

public interface NoteRepository extends JpaRepository<Note, UUID> {
    Optional<Note> findByTitle(String title);

    @Query("SELECT note.title from Note note")
    List<String> findAllTitles();
}
