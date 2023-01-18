package com.professionalblog.gamerblog.controllers;

import com.professionalblog.gamerblog.models.Post;
import com.professionalblog.gamerblog.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

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
        Optional<Post> postOptional = service.findById(id);
        if (!postOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(postOptional.get());
    }
    @PostMapping
    public ResponseEntity<Post> newPost(@RequestBody Post obj) {
        obj.setDate(LocalDate.now(ZoneId.of("UTC")));
        obj = service.savePost(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable(value = "id") Long id, @RequestBody Post obj){
        Optional<Post> postOptional = service.findById(id);
        if (!postOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found.");
        }
        var post = postOptional.get();
        post.setAuthor(obj.getAuthor());
        post.setTitle(obj.getTitle());
        post.setText(obj.getText());
        return ResponseEntity.status(HttpStatus.OK).body(service.savePost(post));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable(value = "id") Long id) {
        Optional<Post> postOptional = service.findById(id);
        if (!postOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found.");
        }
        service.deletePost(postOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Post deleted successfully.");
    }
}
