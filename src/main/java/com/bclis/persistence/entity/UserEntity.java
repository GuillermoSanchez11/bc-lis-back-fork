package com.bclis.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable = false)
    private String username;

    @Column(unique=true, nullable = false)
    private String password;

    @Size(max=40)
    private String name;

    @Size(max=50)
    private String lastname;

    @Email
    @Column(unique=true, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
