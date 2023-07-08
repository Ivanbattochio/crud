package com.api.crud.services;

import com.api.crud.dtos.UserDTO;
import com.api.crud.view.UserView;

public interface UserService {

    UserView createUser(UserDTO user);

}
