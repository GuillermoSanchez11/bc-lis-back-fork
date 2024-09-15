package com.bclis.controller;

import com.bclis.dto.request.CreateUserDTO;
import com.bclis.dto.request.LoginDTO;
import com.bclis.dto.response.AuthResponse;
import com.bclis.service.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsServiceImp userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(userDetailsService.login(loginDTO));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO createUserDTO) {
        userDetailsService.createUser(createUserDTO);

        return ResponseEntity.ok("User created");
    }
}
