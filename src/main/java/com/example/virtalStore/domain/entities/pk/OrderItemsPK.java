package com.example.virtalStore.domain.entities.pk;

import com.example.virtalStore.domain.entities.Order;
import com.example.virtalStore.domain.entities.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemsPK implements Serializable {
    private static final long serialVersionUID =1L;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;




}
