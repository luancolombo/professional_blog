package com.professionalblog.gamerblog.services;


import com.professionalblog.gamerblog.models.Users;
import com.professionalblog.gamerblog.repositories.UsersRepository;
import com.professionalblog.gamerblog.services.Exception.DatabaseException;
import com.professionalblog.gamerblog.services.Exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final UsersRepository repository;

    public UserService(UsersRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public Users saveUser(Users user) {
            return repository.save(user);
    }
    public List<Users> findAll() {
        return repository.findAll();
    }
    public Users findById(Long id) {
        Optional<Users> users = repository.findById(id);
        return users.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    @Transactional
    public void deleteUser(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    public Users updateUser(Long id, Users obj) {
        try {
            Users user = repository.getReferenceById(id);
            updateData(user, obj);
            return repository.save(user);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    private void updateData(Users user, Users obj) {
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
        user.setRoles(obj.getRoles());
    }
}
