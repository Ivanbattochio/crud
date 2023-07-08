package com.api.crud.controllers;

import com.api.crud.dtos.UserDTO;
import com.api.crud.models.UserModel;
import com.api.crud.services.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserDTO userDTO) {

        if (userService.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDTO));
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

}
