package com.example.virtalStore.domain.repositories;

import com.example.virtalStore.domain.entities.Clients;
import com.example.virtalStore.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByClients(Clients client);
}
