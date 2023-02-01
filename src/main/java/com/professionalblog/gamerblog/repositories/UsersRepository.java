package com.professionalblog.gamerblog.repositories;

import com.professionalblog.gamerblog.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);
}
