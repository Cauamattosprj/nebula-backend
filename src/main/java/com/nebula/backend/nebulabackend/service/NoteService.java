package com.nebula.backend.nebulabackend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nebula.backend.nebulabackend.model.Note;
import com.nebula.backend.nebulabackend.repository.FolderRepository;
import com.nebula.backend.nebulabackend.repository.NoteRepository;
import com.nebula.backend.nebulabackend.exception.DuplicateTitleException;
import com.nebula.backend.nebulabackend.exception.NotFoundException;

@Service
public class NoteService {
    private NoteRepository noteRepository;
    private FolderRepository folderRepository;

    public NoteService(NoteRepository noteRepository, FolderRepository folderRepository) {
        this.noteRepository = noteRepository;
        this.folderRepository = folderRepository;
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

    public Note updateNote(UUID id, Note updatedNoteData) {
        return noteRepository.findById(id).map(note -> {
            if (updatedNoteData.getTitle() != null && !updatedNoteData.getTitle().isEmpty()) {
                note.setTitle(updatedNoteData.getTitle());
            }
            if (updatedNoteData.getBody() != null && !updatedNoteData.getBody().isEmpty()) {
                note.setBody(updatedNoteData.getBody());
            }
            if (updatedNoteData.getFolder() != null && updatedNoteData.getFolder().getId() != null) {
                note.setFolder(updatedNoteData.getFolder());
            }

            return noteRepository.save(note);
        }).orElseThrow(() -> new NotFoundException(id));
    }

    public void deleteNoteById(UUID id) {
        if (!noteRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        noteRepository.deleteById(id);
    }
}