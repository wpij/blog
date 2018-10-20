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
    private Long postid;
    private String title;
    private String content;

    @Embedded
    private AuditEntity auditEntity = new AuditEntity();

    @OneToMany(mappedBy = "post")
    private Set<PostComment> comments = new HashSet<>();




}
