package com.example.virtalStore.domain.repositories;

import com.example.virtalStore.domain.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}
