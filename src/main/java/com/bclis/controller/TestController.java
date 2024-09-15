package com.bclis.controller;

import com.bclis.persistence.entity.RoleEntity;
import com.bclis.persistence.entity.UserEntity;
import com.bclis.persistence.entity.enums.EnumRole;
import com.bclis.persistence.repository.RoleRepository;
import com.bclis.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/hello1")
    public ResponseEntity<?> hello(){
//        UserEntity userEntity = userRepository.findByUsername("vale")
//                .orElseThrow(() -> new RuntimeException("Username not found"));
//        return ResponseEntity.ok(userEntity);
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/create")
    public String create(){

        RoleEntity roleEntity =RoleEntity.builder()
                .roleName(EnumRole.valueOf(EnumRole.ADMIN.name()))
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
