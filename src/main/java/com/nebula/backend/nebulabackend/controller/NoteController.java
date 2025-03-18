package com.nebula.backend.nebulabackend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.nebula.backend.nebulabackend.dto.NoteDTO;
import com.nebula.backend.nebulabackend.dto.UpdateNoteRequest;
import com.nebula.backend.nebulabackend.model.ApiResponse;
import com.nebula.backend.nebulabackend.model.Note;
import com.nebula.backend.nebulabackend.service.FolderService;
import com.nebula.backend.nebulabackend.service.NoteService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin("*")
public class NoteController {
    private NoteService noteService;
    private FolderService folderService;

    public NoteController(NoteService noteService, FolderService folderService) {
        this.noteService = noteService;
        this.folderService = folderService;
    }

    @GetMapping("/list")
    public ApiResponse<List<Note>> getAllNotes() {
        return ApiResponse.success(noteService.getAllNotes());
    }

    @GetMapping("/list/{id}")
    public Optional<Note> getNoteById(@PathVariable UUID id) {
        return noteService.getNoteById(id);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<NoteDTO> updateNote(@PathVariable UUID id, @RequestBody UpdateNoteRequest updateNoteRequest) {
        try {
            NoteDTO note = noteService.updateNoteHandler(id, updateNoteRequest);
            return ApiResponse.success(note);
        } catch (Exception e) {
            return ApiResponse.error("Error updating note", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/create")
    public ApiResponse<Note> createNote(@RequestBody Note newNote) {
        Note note = noteService.createNote(newNote);
        return ApiResponse.success(note);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteNote(@PathVariable UUID id) {
        noteService.deleteNoteById(id);
        return ApiResponse.success(String.format("Note with id %s deleted", id));
    }
}
