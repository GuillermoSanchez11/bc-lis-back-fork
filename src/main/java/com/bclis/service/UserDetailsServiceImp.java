package com.bclis.service;

import com.bclis.dto.request.CreateUserDTO;
import com.bclis.persistence.entity.RoleEntity;
import com.bclis.persistence.entity.UserEntity;
import com.bclis.persistence.entity.enums.EnumRole;
import com.bclis.persistence.repository.RoleRepository;
import com.bclis.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        Collection<? extends GrantedAuthority> authorities =
                Collections.singleton(new SimpleGrantedAuthority("ROLE_".concat(userEntity.getRole().getRoleName().name())));

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }

    public void createUser(CreateUserDTO createUserDTO) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        RoleEntity roleEntity = roleRepository
                .findByRoleName(EnumRole.valueOf(createUserDTO.getRole()))
                .orElseThrow(() -> new RuntimeException("Role not found"));

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .name(createUserDTO.getName())
                .lastname(createUserDTO.getLastname())
                .email(createUserDTO.getEmail())
                .role(roleEntity)
                .build();

        userRepository.save(userEntity);
    }
}
