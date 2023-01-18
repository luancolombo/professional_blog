package com.professionalblog.gamerblog.repositories;

import com.professionalblog.gamerblog.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
