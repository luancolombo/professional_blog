package com.professionalblog.gamerblog.controllers;


import com.professionalblog.gamerblog.models.Role;
import com.professionalblog.gamerblog.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/roles")
public class RoleController {

    final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<Role>> findAllRoles() {
        List<Role> list = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        var role = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }
    @PostMapping
    public ResponseEntity<Role> newRole(@RequestBody Role obj) {
        obj = service.saveRole(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role obj) {
        obj = service.updateRole(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
