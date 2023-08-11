package com.example.virtalStore.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_address")
public class Address implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String state;
    private String neighborhood;
    private String houseNumber;
    private String phoneNumber;
    private String zipCode;

    public Address(String street, String state, String neighborhood, String houseNumber, String phoneNumber, String zipCode) {
        this.street = street;
        this.state = state;
        this.neighborhood = neighborhood;
        this.houseNumber = houseNumber;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
    }
}
