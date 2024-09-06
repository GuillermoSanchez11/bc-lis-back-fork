package com.bclis.controller;

import com.bclis.model.entity.RoleEntity;
import com.bclis.model.entity.UserEntity;
import com.bclis.model.enums.EnumRole;
import com.bclis.repository.RoleRepository;
import com.bclis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        UserEntity userEntity = userRepository.findByUsername("vale")
                .orElseThrow(() -> new RuntimeException("Username not found"));
        return ResponseEntity.ok(userEntity);
    }

    @GetMapping("/create")
    public String create(){

        RoleEntity roleEntity =RoleEntity.builder()
                .name(EnumRole.valueOf(EnumRole.ADMIN.name()))
                .build();

        roleRepository.save(roleEntity);

        UserEntity userEntity = UserEntity.builder()
                .username("vale")
                .name("Valentina")
                .lastname("Rincon")
                .password("1234")
                .email("val@mail.com")
                .role(roleEntity)
                .build();

        userRepository.save(userEntity);

        return "Biennnnn";
    }
}
