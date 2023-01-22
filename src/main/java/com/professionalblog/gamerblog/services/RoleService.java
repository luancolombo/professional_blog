package com.professionalblog.gamerblog.services;

import com.professionalblog.gamerblog.models.Role;
import com.professionalblog.gamerblog.repositories.RoleRepository;
import com.professionalblog.gamerblog.services.Exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Role saveRole(Role role) {
        return repository.save(role);
    }
    public List<Role> findAll() {
        return repository.findAll();
    }
    public Role findById(Long id) {
        Optional<Role> roles = repository.findById(id);
        return roles.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    @Transactional
    public void deleteRole(Role role) {
        repository.delete(role);
    }
    public Role updateRole(Long id, Role obj) {
        Role role = repository.getReferenceById(id);
        updateData(role, obj);
        return repository.save(role);
    }
    private void updateData(Role role, Role obj) {
        role.setRoleName(obj.getRoleName());
    }
}
