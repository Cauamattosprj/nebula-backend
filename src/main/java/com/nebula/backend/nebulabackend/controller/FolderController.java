package com.nebula.backend.nebulabackend.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nebula.backend.nebulabackend.dto.FolderDTO;
import com.nebula.backend.nebulabackend.model.ApiResponse;
import com.nebula.backend.nebulabackend.model.Folder;
import com.nebula.backend.nebulabackend.service.FolderService;

@RestController
@RequestMapping("/api/folders")
@CrossOrigin("*")
public class FolderController {
    private FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping("/list")
    public ApiResponse<List<Folder>> getAllFolders() {
        List<Folder> folders = folderService.getAllFolders();
        return ApiResponse.success(folders);
    }

    @GetMapping("/list/{id}")
    public ApiResponse<Optional<Folder>> getFolderById(@PathVariable UUID id) {
        Optional<Folder> folder = folderService.getFolderById(id);
        return ApiResponse.success(folder);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<Folder> updateFolder(@PathVariable UUID id, @RequestBody Folder updatedFolder) {
        Folder folder = folderService.updateFolder(id, updatedFolder);
        return ApiResponse.success(folder);
    }

    @PostMapping("/create")
    public ApiResponse<FolderDTO> createFolder(@RequestBody Folder newFolder) {
        FolderDTO folderDTO = folderService.createFolder(newFolder);
        return ApiResponse.success(folderDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteFolderById(@PathVariable UUID id) {
        folderService.deleteFolderById(id);
        return ApiResponse.success(String.format("Folder with id %s deleted", id));
    }
}
