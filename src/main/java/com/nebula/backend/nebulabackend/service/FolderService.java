package com.nebula.backend.nebulabackend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.nebula.backend.nebulabackend.utils.namingconflict.NameConflictResolver;
import org.springframework.stereotype.Service;

import com.nebula.backend.nebulabackend.dto.FolderDTO;
import com.nebula.backend.nebulabackend.exception.DuplicateTitleException;
import com.nebula.backend.nebulabackend.exception.NotFoundException;
import com.nebula.backend.nebulabackend.model.Folder;
import com.nebula.backend.nebulabackend.repository.FolderRepository;

@Service
public class FolderService {
    private FolderRepository folderRepository;

    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public FolderDTO createFolder(Folder newFolder) {
        List<String> existingTitles = folderRepository.findAllTitles();

        String finalTitle = NameConflictResolver.resolveConflict(newFolder.getTitle(), existingTitles);
        newFolder.setTitle(finalTitle);

        Folder savedFolder = folderRepository.save(newFolder);

        return new FolderDTO(savedFolder);
    }

    public List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }

    public Optional<Folder> getFolderById(UUID id) {
        if (!folderRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        return folderRepository.findById(id);
    }

    public Folder updateFolder(UUID id, Folder folderUpdates) {
        return folderRepository.findById(id).map(folder -> {
            if (folderUpdates.getTitle() != null && !folderUpdates.getTitle().isEmpty()) {
                folder.setTitle(folderUpdates.getTitle());
            }

            return folderRepository.save(folder);
        }).orElseThrow(() -> new NotFoundException(id));
    }

    public void deleteFolderById(UUID id) {
        if (!folderRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        folderRepository.deleteById(id);
    }
}
