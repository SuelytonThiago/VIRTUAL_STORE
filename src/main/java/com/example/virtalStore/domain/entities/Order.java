package com.example.virtalStore.domain.entities;

import com.example.virtalStore.domain.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "tb_order")
public class Order implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
    private LocalDate instant;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients clients;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @OneToMany(mappedBy = "id.order")
    private List<OrderItems> orderItems = new ArrayList<>();

    public Double getTotal(){
        double total = 0.0;
        for(OrderItems x : this.orderItems){
            total = total + x.getSubTotal();
        }
        return total;
    }
}
