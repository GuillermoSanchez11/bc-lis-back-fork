package com.bclis.controller;

import com.bclis.dto.request.CreateUserDTO;
import com.bclis.service.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsServiceImp userDetailsService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO createUserDTO) {
        userDetailsService.createUser(createUserDTO);

        return ResponseEntity.ok("User created");
    }
}
