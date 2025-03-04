package com.nebula.backend.nebulabackend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nebula.backend.nebulabackend.model.Folder;

public interface FolderRepository extends JpaRepository<Folder, UUID> {

}
