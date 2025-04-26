package com.nebula.backend.nebulabackend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nebula.backend.nebulabackend.model.Folder;
import org.springframework.data.jpa.repository.Query;

public interface FolderRepository extends JpaRepository<Folder, UUID> {
    Optional<Folder> findByTitle(String title);

    @Query("SELECT folder.title FROM Folder folder")
    List<String> findAllTitles();
}
