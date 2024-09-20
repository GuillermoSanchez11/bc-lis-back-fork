package com.bclis.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDTO {
    private String token;
    private String message;
    private String username;
    private String name;
    private String lastname;
    private String role;
}
