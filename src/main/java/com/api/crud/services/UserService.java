package com.api.crud.services;

import com.api.crud.dtos.CreateUserDTO;
import com.api.crud.models.UserModel;
import com.api.crud.view.UserView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserView createUser(CreateUserDTO user);

    boolean existsByEmail(String email);

    Optional<UserModel> findById(UUID id);

    void deleteUser(UUID id);

    List<UserModel> findAll();
}
