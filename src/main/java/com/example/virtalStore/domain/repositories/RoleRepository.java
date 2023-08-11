package com.example.virtalStore.domain.repositories;

import com.example.virtalStore.domain.entities.Role;
import com.example.virtalStore.domain.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleName(Roles roles);
}
