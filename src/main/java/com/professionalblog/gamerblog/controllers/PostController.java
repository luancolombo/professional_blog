package com.professionalblog.gamerblog.controllers;

import com.professionalblog.gamerblog.dtos.PostDto;
import com.professionalblog.gamerblog.models.Post;
import com.professionalblog.gamerblog.services.Exception.DatabaseException;
import com.professionalblog.gamerblog.services.Exception.PostNotFoundException;
import com.professionalblog.gamerblog.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {

    final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }
    @CrossOrigin(origins = {"http://localhost:8080", "https://professionalblog-production.up.railway.app"})
    @Operation(summary = "Find all Posts", description = "Find all Posts")
    @GetMapping
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> list = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        var post = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }
    @PostMapping
    public ResponseEntity<Object> newPost(@RequestBody @Valid PostDto postDto) {
        var post = new Post();
        BeanUtils.copyProperties(postDto, post);
        post.setDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePost(post));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable Long id, @RequestBody @Valid PostDto postDto){
        try {
            var post = service.findById(id);
            post.setAuthor(postDto.getAuthor());
            post.setTitle(postDto.getTitle());
            post.setText(postDto.getText());
            return ResponseEntity.status(HttpStatus.OK).body(service.savePost(post));
        }catch (EntityNotFoundException e) {
            throw new PostNotFoundException(id);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable(value = "id") Long id) {
        try {
            var post = service.findById(id);
            service.deletePost(id);
            return ResponseEntity.status(HttpStatus.OK).body("Post deleted successfully.");
        } catch (EmptyResultDataAccessException e) {
            throw new PostNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
