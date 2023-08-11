package com.example.virtalStore.rest.services;
import com.example.virtalStore.configs.security.JWTService;
import com.example.virtalStore.domain.entities.Address;
import com.example.virtalStore.domain.entities.Clients;
import com.example.virtalStore.domain.entities.Role;
import com.example.virtalStore.domain.enums.Roles;
import com.example.virtalStore.domain.repositories.AddressRepository;
import com.example.virtalStore.domain.repositories.ClientsRepository;
import com.example.virtalStore.domain.repositories.RoleRepository;
import com.example.virtalStore.rest.dto.AddressRequestDto;
import com.example.virtalStore.rest.dto.ClientRequestDto;
import com.example.virtalStore.rest.services.exceptions.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public void insertNewClient(ClientRequestDto clientDto){
        clientsRepository.findByEmail(clientDto.getEmail()).ifPresent(e ->{
            throw new CustomException("This email already exists");
        });

        Roles roles = Roles.roleNameOf("CLIENT");
        Role role = roleRepository.findByRoleName(roles).get();
        Clients clients = new Clients();
        clients.setCpf(clientDto.getCpf());
        clients.setPass(encoder.encode(clientDto.getPass()));
        clients.setName(clientDto.getName());
        clients.setEmail(clientDto.getEmail());
        clientsRepository.save(clients);
        clients.getRoles().add(role);
    }

    @Transactional
    public void insertNewAddress(AddressRequestDto addressDto, HttpServletRequest request){
        var auth = request.getHeader("Authorization");
        var token = auth.replace("Bearer ","");
        var email = jwtService.getSubject(token);
        var client = clientsRepository.findByEmail(email).get();

        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setState(addressDto.getState());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setZipCode(addressDto.getZipCode());
        address.setNeighborhood(addressDto.getNeighborhood());
        address.setPhoneNumber(addressDto.getPhoneNumber());
        addressRepository.save(address);

        client.getAddresses().add(address);
        clientsRepository.save(client);
    }
}
