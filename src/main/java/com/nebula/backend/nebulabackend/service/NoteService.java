package com.nebula.backend.nebulabackend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.nebula.backend.nebulabackend.model.Note;
import com.nebula.backend.nebulabackend.repository.FolderRepository;
import com.nebula.backend.nebulabackend.repository.NoteRepository;
import com.nebula.backend.nebulabackend.dto.NoteDTO;
import com.nebula.backend.nebulabackend.dto.UpdateNoteRequest;
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

    public Stream<NoteDTO> getAllNotes() {
        return noteRepository.findAll().stream().map(NoteDTO::new);
    }

    public NoteDTO getNoteById(UUID id) {
        return noteRepository.findById(id).map(NoteDTO::new).orElseThrow(() -> new NotFoundException(id));
    }

    public Note updateNoteTitle(UUID id, String title) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        note.setTitle(title);

        return noteRepository.save(note);
    }

    public Note updateNoteBody(UUID id, String body) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        note.setBody(body);

        return noteRepository.save(note);
    }

    public Note updateNoteFolder(UUID id, UUID folderId) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        note.setFolder(folderRepository.findById(folderId).orElseThrow(() -> new NotFoundException(folderId)));

        return noteRepository.save(note);
    }

    public NoteDTO updateNoteHandler(UUID id, UpdateNoteRequest updateNoteRequest) {
        if (!updateNoteRequest.getTitle().isBlank() || !updateNoteRequest.getBody().isBlank()
                || !updateNoteRequest.getFolderId().toString().isBlank()) {
            if (!updateNoteRequest.getTitle().isBlank()) {
                String newTitle = updateNoteRequest.getTitle();
                updateNoteTitle(id, newTitle);
            }
            if (!updateNoteRequest.getBody().isBlank()) {
                String newBody = updateNoteRequest.getBody();
                updateNoteBody(id, newBody);
            }
            if (!updateNoteRequest.getFolderId().toString().isBlank()) {
                UUID newFolderId = updateNoteRequest.getFolderId();
                updateNoteFolder(id, newFolderId);
            }

            return noteRepository.findById(id).map(NoteDTO::new).orElseThrow(() -> new NotFoundException(id));
        } else
            throw new IllegalArgumentException("No valid field to update");
    }

    public void deleteNoteById(UUID id) {
        if (!noteRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        noteRepository.deleteById(id);
    }
}