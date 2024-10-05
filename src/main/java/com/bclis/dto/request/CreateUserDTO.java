package com.bclis.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) para la creación de un nuevo usuario.
 * Este objeto se utiliza para transportar datos entre capas de la aplicación,
 * especialmente entre la capa de presentación y la capa de servicio.
 */
@Setter
@Getter
@Builder
@Schema(description = "DTO para la creación de un nuevo usuario.")
public class CreateUserDTO {

    @Schema(description = "Nombre de usuario del nuevo usuario. Debe ser único.",
            example = "jdoe",
            required = true)
    private String username;

    @Schema(description = "Contraseña del nuevo usuario. Debe cumplir con los requisitos de seguridad definidos.",
            example = "P@ssw0rd",
            required = true)
    private String password;

    @Schema(description = "Nombre real del usuario.",
            example = "John",
            required = false)
    private String name;

    @Schema(description = "Apellido del usuario.",
            example = "Doe",
            required = false)
    private String lastname;

    @Schema(description = "Dirección de correo electrónico del nuevo usuario. Debe ser única en el sistema.",
            example = "jdoe@example.com",
            required = true)
    private String email;

    @Schema(description = "Rol del usuario en el sistema. Ejemplo: 'ADMIN', 'USER'.",
            example = "USER",
            required = true)
    private String role;
}
