package com.nebula.backend.nebulabackend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.nebula.backend.nebulabackend.dto.ApiResponse;
import com.nebula.backend.nebulabackend.dto.NoteDTO;
import com.nebula.backend.nebulabackend.dto.UpdateNoteRequest;
import com.nebula.backend.nebulabackend.model.Note;
import com.nebula.backend.nebulabackend.service.NoteService;

import java.util.UUID;
import java.util.stream.Stream;

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
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list/all")
    public ApiResponse<Stream<NoteDTO>> getAllNotes() {
        return ApiResponse.success(noteService.getAllNotes());
    }

    @GetMapping("/list/notes-without-folders")
    public ApiResponse<Stream<NoteDTO>> getAllNotesWithoutFolders() {
        return ApiResponse.success(noteService.getAllNotesWithoutFolder());
    }

    @GetMapping("/list/{id}")
    public ApiResponse<NoteDTO> getNoteById(@PathVariable UUID id) {
        return ApiResponse.success(noteService.getNoteById(id));
    }

    @PutMapping("/update/{id}")
    public ApiResponse<NoteDTO> updateNote(@PathVariable UUID id, @RequestBody UpdateNoteRequest updateNoteRequest) {
            NoteDTO note = noteService.updateNoteHandler(id, updateNoteRequest);
            return ApiResponse.success(note);
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
