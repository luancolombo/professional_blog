package com.professionalblog.gamerblog.controllers;

import com.professionalblog.gamerblog.dtos.PostDto;
import com.professionalblog.gamerblog.models.Post;
import com.professionalblog.gamerblog.services.Exception.ResourceNotFoundException;
import com.professionalblog.gamerblog.services.PostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> list = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        var postOptional = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(postOptional);
    }
    @PostMapping
    public ResponseEntity<Object> newPost(@RequestBody @Valid PostDto postDto) {
        var postModel = new Post();
        BeanUtils.copyProperties(postDto, postModel);
        postModel.setDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePost(postModel));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable(value = "id") Long id, @RequestBody @Valid PostDto postDto){
        try {
            var postOptional = service.findById(id);
            postOptional.setAuthor(postDto.getAuthor());
            postOptional.setTitle(postDto.getTitle());
            postOptional.setText(postDto.getText());
            return ResponseEntity.status(HttpStatus.OK).body(service.savePost(postOptional));
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable(value = "id") Long id) {
        var postOptional = service.findById(id);
        service.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).body("Post deleted successfully.");
    }
}
