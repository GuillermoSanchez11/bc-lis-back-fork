package com.bclis.controller;

import com.bclis.dto.request.CreateUserDTO;
import com.bclis.dto.request.LoginDTO;
import com.bclis.dto.response.AuthResponseDTO;
import com.bclis.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(userDetailsService.login(loginDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDTO createUserDTO) {
        userDetailsService.createUser(createUserDTO);
        return ResponseEntity.ok("User created");
    }
}
