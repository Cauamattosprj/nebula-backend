package com.nebula.backend.nebulabackend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nebula.backend.nebulabackend.model.Note;
import com.nebula.backend.nebulabackend.repository.NoteRepository;
import com.nebula.backend.nebulabackend.exception.DuplicateTitleException;
import com.nebula.backend.nebulabackend.exception.NotFoundException;

@Service
public class NoteService {
    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(Note newNote) {
        Optional<Note> existingNote = noteRepository.findByTitle(newNote.getTitle());

        if (!existingNote.isEmpty()) {
            throw new DuplicateTitleException(newNote.getTitle());
        }

        return noteRepository.save(newNote);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(UUID id) {
        return noteRepository.findById(id);
    }

    public Note updateNote(UUID id, Note noteUpdates) {
        return noteRepository.findById(id).map(note -> {
            if (noteUpdates.getTitle() != null && !noteUpdates.getTitle().isEmpty()) {
                note.setTitle(noteUpdates.getTitle());
            }
            if (noteUpdates.getBody() != null && !noteUpdates.getBody().isEmpty()) {
                note.setBody(noteUpdates.getBody());
            }
            if (noteUpdates.getFolder() != null && noteUpdates.getFolder().getId() != null) {
                note.setFolder(noteUpdates.getFolder());
            }

            return noteRepository.save(note);
        }).orElseThrow(() -> new RuntimeException("Nota não encontrada!"));
    }

    public void deleteNoteById(UUID id) {
        if (!noteRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        noteRepository.deleteById(id);
    }
}