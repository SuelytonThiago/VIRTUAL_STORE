package com.example.virtalStore.domain.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
@Table(name = "tb_clients")
public class Clients extends Users{

    @ManyToMany
    @JoinTable(name = "tb_clients_addresses",
    joinColumns = @JoinColumn(name ="clients"),
    inverseJoinColumns = @JoinColumn(name = "addresses"))
    private List<Address> addresses = new ArrayList<>();

    public Clients(){
        super();
    }

    public Clients(Long id, String name, String cpf, String pass, String email) {
        super(id, name, cpf, pass, email);
    }
}
