package com.example.blog.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Post {
    //id, title, content,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;



    @Embedded
    private AuditEntity auditEntity = new AuditEntity();

    @OneToMany(mappedBy = "post", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<PostComment> comments = new HashSet<>();


    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }


    public void addComment(PostComment postComment) {
        comments.add(postComment);
        postComment.setPost(this);
    }
}
