package com.api.crud.services;

import com.api.crud.dtos.CreateUserDTO;
import com.api.crud.models.UserModel;
import com.api.crud.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserModel createUser(CreateUserDTO user) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        Instant now = Instant.now();

        userModel.setBalance(0.0);
        userModel.setEncodedPassword(passwordEncoder.encode(user.getPlainPassword()));
        userModel.setCreatedAt(now);
        userModel.setUpdatedAt(now);

        return userRepository.save(userModel);
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

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel updateUser(UserModel userModel) {
        userModel.setUpdatedAt(Instant.now());
        return userRepository.save(userModel);
    }
}
