package com.api.crud.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class UpdateUserDTO {
    @Id
    private UUID id;
    @NotBlank
    private String name;
    @Past
    private Instant birthDate;
    @Column(nullable = false)
    private Double balance;
}
