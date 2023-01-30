package com.professionalblog.gamerblog.dtos;

import com.professionalblog.gamerblog.models.Post;
import com.professionalblog.gamerblog.models.Role;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class UsersDto {
    @NotBlank
    private String email;
    @NotBlank
    private String name;
    private List<Post> posts;
    List<Role> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
