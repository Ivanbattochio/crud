package com.api.crud.view;

import lombok.Data;

import java.time.Instant;

@Data
public class UserView {
    private String name;
    private String email;
    private Instant birthDate;
    private Instant createdAt;
    private Instant updatedAt;
}
