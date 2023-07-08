package com.api.crud.services;

import com.api.crud.dtos.UserDTO;
import com.api.crud.view.UserView;
import com.api.crud.models.UserModel;
import com.api.crud.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserView createUser(UserDTO user) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        Instant now = Instant.now();

        userModel.setBalance(0.0);
        userModel.setEncodedPassword(passwordEncoder.encode(user.getPlainPassword()));
        userModel.setCreatedAt(now);
        userModel.setUpdatedAt(now);

        userRepository.save(userModel);

        UserView userView = new UserView();

        BeanUtils.copyProperties(userModel, userView);

        return userView;
    }
}
