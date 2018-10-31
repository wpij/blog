package com.example.blog.controller;


import com.example.blog.model.entities.Post;
        import com.example.blog.model.entities.PostComment;
        import com.example.blog.repositories.PostRepository;
        import javafx.geometry.Pos;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

        import javax.transaction.Transactional;
        import java.util.Optional;

    @Controller
    public class PostController {
        @Autowired
        PostRepository postRepository;

        @GetMapping("/post/{postId}")
        public String post(@PathVariable Long postId, Model model) {
            Optional<Post> postOptional = postRepository.findById(postId);

            postOptional.ifPresent(post -> {
                model.addAttribute("post", post);
            });

            return "post";
        }

        @PostMapping("/post/addComment")
        public String addComment(@RequestParam String commentBody, @RequestParam Long postId) {
            PostComment postComment = new PostComment();
            postComment.setComment(commentBody);

            Optional<Post> postOptional = postRepository.findById(postId);

            postOptional.ifPresent(post -> {
                post.addComment(postComment);
                postRepository.save(post);
            });

            return "redirect:/post/" + postId;
        }

    }