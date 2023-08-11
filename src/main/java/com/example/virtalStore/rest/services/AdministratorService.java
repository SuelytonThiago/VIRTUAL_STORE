package com.example.virtalStore.rest.services;

import com.example.virtalStore.domain.entities.Administrators;
import com.example.virtalStore.domain.entities.Role;
import com.example.virtalStore.domain.enums.Roles;
import com.example.virtalStore.domain.repositories.AdministratorRepository;
import com.example.virtalStore.domain.repositories.RoleRepository;
import com.example.virtalStore.rest.dto.AdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void insertNewAdmin(AdminDto adminDto){

        Roles roles = Roles.roleNameOf("ADMIN");
        Role role = roleRepository.findByRoleName(roles).get();

        Administrators administrators = new Administrators();
        administrators.setName(adminDto.getName());
        administrators.setCpf(adminDto.getCpf());
        administrators.setEmail(adminDto.getEmail());
        administrators.setPass(encoder.encode(adminDto.getPass()));
        administratorRepository.save(administrators);
        administrators.getRoles().add(role);
    }
}
