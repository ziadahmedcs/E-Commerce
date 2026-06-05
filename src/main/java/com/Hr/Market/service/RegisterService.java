package com.Hr.Market.service;

import com.Hr.Market.Entity.Roles;
import com.Hr.Market.Entity.Users;
import com.Hr.Market.Repository.RoleRepository;
import com.Hr.Market.Repository.UserRepository;
import com.Hr.Market.dto.DtoRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users register(DtoRegister dtoRegister) {

            if (userRepository.existsByEmail(dtoRegister.getEmail())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Email already exists"
                );
            }
        String rr =  "ROLE_" + dtoRegister.getRole().toUpperCase();
        if (!roleRepository.existsByName(rr))
        {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "IT is Not Employee OR Admin"
            );
        }

        Roles role = roleRepository
                .findByName(
                        "ROLE_" + dtoRegister.getRole().toUpperCase()
                )
                .orElseThrow();
        Users users = new Users();
        users.setUsername(dtoRegister.getUsername());
        users.setEmail(dtoRegister.getEmail());
        users.setPassword(passwordEncoder.encode(dtoRegister.getPassword()));
        users.getRoles().add(role);
        userRepository.save(users);
        return users;
    }

    public List<Users>GetData ()
    {

           return userRepository.findAll() ;

    }
}