package com.Hr.Market.Controller;

import com.Hr.Market.Entity.Users;
import com.Hr.Market.dto.DtoRegister;
import com.Hr.Market.dto.LoginDto;
import com.Hr.Market.service.LoginService;
import com.Hr.Market.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthControlller {
    @Autowired
    private RegisterService registerService ;
    @Autowired
    private LoginService loginService ;
    @PostMapping("/Register")
    public Users Register (@Valid @RequestBody DtoRegister dtoRegister)
    {
       return registerService.register(dtoRegister) ;
    }
    @GetMapping("/Data")
    public List<Users> Data ()
    {
        return registerService.GetData() ;
    }
    @PostMapping("/login")
    public ResponseEntity<?> Login (@Valid @RequestBody LoginDto loginDto)
    {
        return loginService.LoginUser(loginDto);
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        Map <String,String> response = loginService.GetMe(authentication.getName()) ;
        response.put("Email" ,authentication.getName()) ;
        response.put("Authorities" ,authentication.getAuthorities().toString()) ;
        return  new ResponseEntity<>(response, HttpStatus.OK) ;
    }


}
