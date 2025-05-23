package com.nebula.backend.nebulabackend.repository;

import com.nebula.backend.nebulabackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<User, UUID> {
}
