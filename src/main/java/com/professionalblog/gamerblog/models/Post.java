package com.professionalblog.gamerblog.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "TB_POSTS")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 30)
    private String title;
    @Column(nullable = false)
    private String urlImage;
    @Column(nullable = false, length = 30)
    private String author;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    @Lob
    private String text;

    public Post() {
    }
    public Post(Long id, String title, String urlImage, String author, LocalDateTime date, String text) {
        this.id = id;
        this.title = title;
        this.urlImage = urlImage;
        this.author = author;
        this.date = date;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
