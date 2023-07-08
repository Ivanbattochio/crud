package com.api.crud.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
public class UserDTO {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @Past
    private Instant birthDate;
    @NotBlank
    @Size(min = 6)
    private String plainPassword;
}
