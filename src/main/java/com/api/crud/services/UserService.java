package com.api.crud.services;

import com.api.crud.dtos.UserDTO;
import com.api.crud.models.UserModel;
import com.api.crud.view.UserView;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserView createUser(UserDTO user);

    boolean existsByEmail(String email);

    Optional<UserModel> findById(UUID id);

    void deleteUser(UUID id);
}
