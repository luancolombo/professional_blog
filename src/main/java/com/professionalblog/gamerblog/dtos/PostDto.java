package com.professionalblog.gamerblog.dtos;

import com.professionalblog.gamerblog.models.Users;
import jakarta.validation.constraints.NotBlank;

public class PostDto {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String text;

    private Users user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }
}
