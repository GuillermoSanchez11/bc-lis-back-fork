package com.bclis.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CreateUserDTO {
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private String role;
}
