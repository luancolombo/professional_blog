package com.professionalblog.gamerblog.services;


import com.professionalblog.gamerblog.models.Users;
import com.professionalblog.gamerblog.repositories.UsersRepository;
import jakarta.transaction.Transactional;
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
    public Optional<Users> findById(Long id) {
        return repository.findById(id);
    }
    @Transactional
    public void deleteUser(Users user) {
        repository.delete(user);
    }
    public Users updateUser(Long id, Users obj) {
        Users user = repository.getReferenceById(id);
        updateData(user, obj);
        return repository.save(user);
    }
    private void updateData(Users user, Users obj) {
        user.setName(obj.getName());
        user.setLast_name(obj.getLast_name());
        user.setEmail(obj.getEmail());
        user.setUsername(obj.getUsername());
        user.setPassword(obj.getPassword());
        user.setRoles(obj.getRoles());
    }
}
