package com.nebula.backend.nebulabackend.service;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.nebula.backend.nebulabackend.model.Note;
import com.nebula.backend.nebulabackend.repository.FolderRepository;
import com.nebula.backend.nebulabackend.repository.NoteRepository;
import com.nebula.backend.nebulabackend.dto.NoteDTO;
import com.nebula.backend.nebulabackend.dto.UpdateNoteRequest;
import com.nebula.backend.nebulabackend.exception.NotFoundException;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final FolderRepository folderRepository;

    public NoteService(NoteRepository noteRepository, FolderRepository folderRepository) {
        this.noteRepository = noteRepository;
        this.folderRepository = folderRepository;
    }

    public Note createNote(Note newNote) {
        String originalTitle = newNote.getTitle();
        int existingNoteCounter = 2;

        while (noteRepository.findByTitle(newNote.getTitle()).isPresent()) {
            newNote.setTitle(originalTitle + " " + existingNoteCounter);
            existingNoteCounter++;
        }

        return noteRepository.save(newNote);
    }

    public Stream<NoteDTO> getAllNotes() {
        return noteRepository.findAll().stream().map(NoteDTO::new);
    }

    public Stream<NoteDTO> getAllNotesWithoutFolder() {
        return noteRepository.findAll().stream().filter(folder -> folder.getFolder() == null).map(NoteDTO::new);
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
        boolean hasTitle = updateNoteRequest.getTitle() != null && !updateNoteRequest.getTitle().isBlank();
        boolean hasBody = updateNoteRequest.getBody() != null && !updateNoteRequest.getBody().isBlank();
        boolean hasFolder = updateNoteRequest.getFolderId() != null;

        if (hasTitle || hasBody || hasFolder) {
            if (hasTitle) {
                updateNoteTitle(id, updateNoteRequest.getTitle());
            }
            if (hasBody) {
                updateNoteBody(id, updateNoteRequest.getBody());
            }
            if (hasFolder) {
                updateNoteFolder(id, updateNoteRequest.getFolderId());
            }

            return noteRepository.findById(id).map(NoteDTO::new).orElseThrow(() -> new NotFoundException(id));
        } else {
            throw new IllegalArgumentException("No valid field to update");
        }
    }

    public void deleteNoteById(UUID id) {
        if (!noteRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        noteRepository.deleteById(id);
    }
}