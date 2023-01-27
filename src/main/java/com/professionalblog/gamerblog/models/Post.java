package com.professionalblog.gamerblog.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "TB_POSTS")
public class Post implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 30)
    private String title;
    @Column(nullable = false, length = 30)
    private String author;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    @Lob
    private String text;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users user;

    public Post() {
    }
    public Post(Long id, String title, String author, LocalDateTime date, String text, Users user) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.text = text;
        this.user = user;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
