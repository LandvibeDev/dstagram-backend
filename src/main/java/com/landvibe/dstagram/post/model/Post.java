package com.landvibe.dstagram.post.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private String imageUrl;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime updated;

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = LocalDateTime.now();
    }

    // TODO add user

    public Post() {
    }

    public Post(int id, String title, String contents, String imageUrl, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.created = created;
        this.updated = updated;
    }
}
