package com.api.crud.services;

import com.api.crud.dtos.CreateUserDTO;
import com.api.crud.models.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserModel createUser(CreateUserDTO user);

    boolean existsByEmail(String email);

    Optional<UserModel> findById(UUID id);

    void deleteUser(UUID id);

    List<UserModel> findAll();
}
