package com.api.crud.services;

import com.api.crud.dtos.UserDTO;
import com.api.crud.models.UserModel;
import com.api.crud.repositories.UserRepository;
import com.api.crud.view.UserView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

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

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<UserModel> findById(UUID id) {
        return userRepository.findById(id);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
