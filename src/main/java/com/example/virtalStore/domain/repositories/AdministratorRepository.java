package com.example.virtalStore.domain.repositories;

import com.example.virtalStore.domain.entities.Administrators;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrators,Long> {
}
