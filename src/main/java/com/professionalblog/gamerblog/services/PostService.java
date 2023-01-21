package com.professionalblog.gamerblog.services;

import com.professionalblog.gamerblog.models.Post;
import com.professionalblog.gamerblog.repositories.PostRepository;
import com.professionalblog.gamerblog.services.Exception.DatabaseException;
import com.professionalblog.gamerblog.services.Exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public Post findById(Long id) {
        Optional<Post> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    @Transactional
    public void deletePost(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
