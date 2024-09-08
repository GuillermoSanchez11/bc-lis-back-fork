package com.bclis.model.entity;

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

    @NotBlank
    @Size(max=30)
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Size(max=40)
    private String name;

    @NotBlank
    @Size(max=50)
    private String lastname;

    @Email
    @NotBlank
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
