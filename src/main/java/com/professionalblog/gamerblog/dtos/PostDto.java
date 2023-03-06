package com.professionalblog.gamerblog.dtos;

import jakarta.validation.constraints.NotBlank;

public class PostDto {
    @NotBlank
    private String title;
    @NotBlank
    private String urlImage;
    @NotBlank
    private String author;
    @NotBlank
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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
}
