package com.professionalblog.gamerblog.controllers;

import com.professionalblog.gamerblog.models.Users;
import com.professionalblog.gamerblog.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
        Optional<Users> userOptional = service.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
    }
    @PostMapping
    public ResponseEntity<Users> newUser(@RequestBody Users obj) {
        obj = service.saveUser(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users obj) {
        obj = service.updateUser(id, obj);
        return ResponseEntity.ok().body(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id) {
        Optional<Users> userOptional = service.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        service.deleteUser(userOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }

}
