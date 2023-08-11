package com.example.virtalStore.configs;

import com.example.virtalStore.domain.entities.Administrators;
import com.example.virtalStore.domain.entities.Clients;
import com.example.virtalStore.domain.entities.Role;
import com.example.virtalStore.domain.enums.Roles;
import com.example.virtalStore.domain.repositories.AdministratorRepository;
import com.example.virtalStore.domain.repositories.ClientsRepository;
import com.example.virtalStore.domain.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
public class Config implements CommandLineRunner {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    public void run(String... args) throws Exception {
        Administrators administrators =
                new Administrators(null,
                        "adm",
                        "36315881009",
                        encoder.encode("senha123"),
                        "adm@example.com");
        administratorRepository.save(administrators);

        Clients clients =
                new Clients(null,
                        "ana",
                        "36315881009",
                        encoder.encode("senha123"),
                        "ana@example.com");
        clientsRepository.save(clients);

        Role adm = new Role(null, Roles.ROLE_ADMIN);
        Role client = new Role(null, Roles.ROLE_CLIENT);
        roleRepository.saveAll(Arrays.asList(adm,client));

        clients.getRoles().add(client);
        administrators.getRoles().add(adm);
        administratorRepository.save(administrators);
        clientsRepository.save(clients);

    }
}
