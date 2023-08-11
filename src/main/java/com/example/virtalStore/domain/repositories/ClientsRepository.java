package com.example.virtalStore.domain.repositories;

import com.example.virtalStore.domain.entities.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientsRepository extends JpaRepository<Clients,Long> {

    Optional<Clients> findByEmail(String email);
}
