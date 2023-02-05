package com.professionalblog.gamerblog.controllers;

import com.professionalblog.gamerblog.dtos.UsersDto;
import com.professionalblog.gamerblog.models.Users;
import com.professionalblog.gamerblog.services.Exception.DatabaseException;
import com.professionalblog.gamerblog.services.Exception.UserNotFoundException;
import com.professionalblog.gamerblog.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    final UserService service;

    public UsersController(UserService service) {
        this.service = service;
    }
    @Operation(summary = "Find all users", description = "Find all users")
    @GetMapping
    public ResponseEntity<List<Users>> findAllUsers() {
        List<Users> list = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @Operation(summary = "Find users by id", description = "Find users by id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        var users  = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
    @Operation(summary = "Create new user", description = "Create new user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> newUser(@RequestBody @Valid UsersDto usersDto) {
        var users = new Users();
        BeanUtils.copyProperties(usersDto, users);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveUser(users));
    }
    @Operation(summary = "Update user", description = "Update user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody @Valid UsersDto usersDto) {
        try {
            var users = service.findById(id);
            users.setName(usersDto.getName());
            users.setEmail(usersDto.getEmail());
            users.setRoles(usersDto.getRoles());
            users.setUsername(usersDto.getUsername());
            users.setPassword(usersDto.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(service.saveUser(users));
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException(id);
        }
    }
    @Operation(summary = "Delete user", description = "Delete user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id) {
        try {
            var user = service.findById(id);
            service.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
