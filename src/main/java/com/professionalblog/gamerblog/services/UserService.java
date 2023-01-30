package com.professionalblog.gamerblog.services;


import com.professionalblog.gamerblog.models.Users;
import com.professionalblog.gamerblog.repositories.UsersRepository;
import com.professionalblog.gamerblog.services.Exception.UserNotFoundException;
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
    public Users findById(Long id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(() -> new UserNotFoundException(id));
    }
    @Transactional
    public void deleteUser(Long id) {
            repository.deleteById(id);
    }
}

