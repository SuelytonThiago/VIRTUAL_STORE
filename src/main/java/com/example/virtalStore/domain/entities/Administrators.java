package com.example.virtalStore.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "tb_administrators")
public class Administrators extends Users{

    public Administrators(){
        super();
    }

    public Administrators(Long id, String name, String cpf, String pass, String email) {
        super(id, name, cpf, pass, email);
    }
}
