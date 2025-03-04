package com.nebula.backend.nebulabackend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nebula.backend.nebulabackend.model.Note;
import com.nebula.backend.nebulabackend.repository.NoteRepository;

@Service
public class NoteService {
    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(UUID id) {
        return noteRepository.findById(id);
    }

    public Note updateNote(Note noteUpdates) {
        return noteRepository.findById(noteUpdates.getId()).map(note -> {
            if (noteUpdates.getTitle() != null && !noteUpdates.getTitle().isEmpty()) {
                note.setTitle(noteUpdates.getTitle());
            }
            if (noteUpdates.getBody() != null && !noteUpdates.getBody().isEmpty()) {
                note.setBody(noteUpdates.getBody());
            }

            return noteRepository.save(note);
        }).orElseThrow(() -> new RuntimeException("Nota não encontrada!"));
    }

    public void deleteNoteById(UUID id) {
        noteRepository.deleteById(id);
    }
}
