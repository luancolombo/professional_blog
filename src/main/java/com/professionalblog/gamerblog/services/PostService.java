package com.professionalblog.gamerblog.services;

import com.professionalblog.gamerblog.models.Post;
import com.professionalblog.gamerblog.repositories.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public Post savePost(Post post) {
        return repository.save(post);
    }
    public List<Post> findAll() {
        return repository.findAll();
    }
    public Optional<Post> findById(Long id) {
        return repository.findById(id);
    }
    @Transactional
    public void deletePost(Post post) {
        repository.delete(post);
    }

}
