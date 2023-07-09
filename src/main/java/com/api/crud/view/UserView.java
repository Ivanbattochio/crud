package com.api.crud.view;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class UserView {
    private UUID id;
    private String name;
    private String email;
    private Double balance;
    private Instant birthDate;
    private Instant createdAt;
    private Instant updatedAt;
}
