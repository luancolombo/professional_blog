package com.professionalblog.gamerblog.controllers;

import com.professionalblog.gamerblog.dtos.UsersDto;
import com.professionalblog.gamerblog.models.Users;
import com.professionalblog.gamerblog.services.Exception.DatabaseException;
import com.professionalblog.gamerblog.services.Exception.UserNotFoundException;
import com.professionalblog.gamerblog.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    final UserService service;

    public UsersController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Users>> findAllUsers() {
        List<Users> list = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        var users  = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
    @PostMapping
    public ResponseEntity<Object> newUser(@RequestBody @Valid UsersDto usersDto) {
        var users = new Users();
        BeanUtils.copyProperties(usersDto, users);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveUser(users));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody @Valid UsersDto usersDto) {
        try {
            var users = service.findById(id);
            users.setName(usersDto.getName());
            users.setEmail(usersDto.getEmail());
            users.setRoles(usersDto.getRoles());
            return ResponseEntity.status(HttpStatus.OK).body(service.saveUser(users));
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException(id);
        }
    }
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
