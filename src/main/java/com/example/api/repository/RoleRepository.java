package com.example.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.enums.RoleEnum;
import com.example.api.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
