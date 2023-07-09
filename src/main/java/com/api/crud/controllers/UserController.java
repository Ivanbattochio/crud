package com.api.crud.controllers;

import com.api.crud.dtos.CreateUserDTO;
import com.api.crud.dtos.UpdateUserDTO;
import com.api.crud.models.UserModel;
import com.api.crud.services.UserServiceImpl;
import com.api.crud.view.UserView;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid CreateUserDTO userDTO) {

        if (userService.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToView(userService.createUser(userDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) {

        Optional<UserModel> user = userService.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findUser(@PathVariable(value = "id") UUID id) {

        Optional<UserModel> user = userService.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping
    public ResponseEntity<List<UserView>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll().stream().map(this::convertToView).collect(Collectors.toList()));
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody @Valid UpdateUserDTO userDTO) {
        Optional<UserModel> optionalUser = userService.findById(userDTO.getId());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        UserModel userModel = optionalUser.get();
        modelMapper.map(userDTO, userModel);

        return ResponseEntity.status(HttpStatus.OK).body(convertToView(userService.updateUser(userModel)));
    }

    private UserView convertToView(UserModel user) {
        return modelMapper.map(user, UserView.class);
    }

}
