package com.example.virtalStore.domain.repositories;

import com.example.virtalStore.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
