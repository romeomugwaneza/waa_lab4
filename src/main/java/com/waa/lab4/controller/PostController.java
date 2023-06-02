package com.waa.lab4.controller;

import com.waa.lab4.domain.dto.response.PostsResponse;
import com.waa.lab4.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/{title}")
    public ResponseEntity<?> getPostsWithTitle(@PathVariable("title") String title){
        PostsResponse posts = new PostsResponse();
        posts.setPosts(postService.findPostsByTitle(title));
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/posts/users/{title}")
    public ResponseEntity<?> getUsersWithPostTitle(@PathVariable("title") String title){

        return ResponseEntity.ok().body(postService.getUsersWithPostTitle(title));

    }
}
