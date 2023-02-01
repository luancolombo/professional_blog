package com.professionalblog.gamerblog.Configs.security;

import com.professionalblog.gamerblog.models.Users;
import com.professionalblog.gamerblog.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    final UsersRepository repository;

    public UserDetailsServiceImpl(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return new User(users.getUsername(), users.getPassword(), true, true, true,true, users.getAuthorities());
    }
}

