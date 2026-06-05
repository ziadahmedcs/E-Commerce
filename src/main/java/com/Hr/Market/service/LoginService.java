package com.Hr.Market.service;

import com.Hr.Market.Entity.Users;
import com.Hr.Market.Repository.UserRepository;
import com.Hr.Market.dto.LoginDto;
import com.Hr.Market.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class LoginService {
    @Autowired
    private  AuthenticationManager authenticationManager ;
    @Autowired
    private JwtService jwtService ;

    @Autowired
    private  UserRepository userRepository ;

    public ResponseEntity<?> LoginUser (LoginDto loginDto)
    {
        Map<String , String> respose = new HashMap<>() ;
        Optional<Users> test =  userRepository.findByEmail(loginDto.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail() ,
                        loginDto.getPassword()
                )
        );
            Users users = test.get() ;
            List<String> roles ;
            roles = users.getRoles().stream().map(roles1 -> roles1.getName()).toList() ;
            respose.put("email" , loginDto.getEmail()) ;
            respose.put("token" , jwtService.generateToken(users.getEmail(),roles) ) ;
            respose.put("status" , HttpStatus.OK.toString() ) ;
        return new ResponseEntity<>(respose , HttpStatus.OK);
    }

    public Map<String,String> GetMe (String email)
    {
        Map <String , String> response = new HashMap<>() ;
        Users users = userRepository.findByEmail(email).get() ;
        response.put("username" ,users.getUsername()) ;
        return  response ;
    }
}
