package com.professionalblog.gamerblog.repositories;

import com.professionalblog.gamerblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
