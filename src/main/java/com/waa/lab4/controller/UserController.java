package com.waa.lab4.controller;

import com.waa.lab4.aop.ExecutionTime;
import com.waa.lab4.aop.LogException;
import com.waa.lab4.domain.dto.request.CommentRequest;
import com.waa.lab4.domain.dto.request.PostRequest;
import com.waa.lab4.domain.dto.request.UserRequest;
import com.waa.lab4.domain.dto.response.PostsResponse;
import com.waa.lab4.domain.dto.response.UsersResponse;
import com.waa.lab4.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/exception")
    @LogException
    public void testException() throws ArithmeticException{
        userService.testException();
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(value = "min_posts", required = false) Integer min_posts){

        UsersResponse usersResponse = new UsersResponse();

        if (min_posts != null){

            usersResponse.setUsers(userService.findUsersByPostsSizeGreaterThan(min_posts));
            return ResponseEntity.ok().body(usersResponse);

        }

        usersResponse.setUsers(userService.findAll());

        return ResponseEntity.ok().body(usersResponse);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ExecutionTime
    public void save(@RequestBody UserRequest userRequest){

        userService.save(userRequest);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") long userId){
        try {

            return ResponseEntity.ok().body(userService.findById(userId));

        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable("userId") long userId){
        userService.deleteUserById(userId);
    }

    @PostMapping("/{userId}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPost(
            @PathVariable("userId") long userId,
            @RequestBody PostRequest post){

        userService.addPost(userId, post);

    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<?> getPost(@PathVariable("userId") long userId){

        try {
            PostsResponse postsResponse = new PostsResponse();
            postsResponse.setPosts(userService.getPosts(userId));
            return ResponseEntity.ok().body(postsResponse);
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping("/{userId}/posts/{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(
            @PathVariable("userId") long userId,
            @PathVariable("postId") long postId,
            @RequestBody CommentRequest commentRequest){
        try{
            userService.addComment(userId, postId, commentRequest);
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/{userId}/posts/{postId}/comments")
    public ResponseEntity<?> getAllComments(
            @PathVariable("userId") long userId,
            @PathVariable("postId") long postId
    ){
        return ResponseEntity.ok().body(userService.getAllComments(userId, postId));
    }

    @GetMapping("/{userId}/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> getCommentById(
            @PathVariable("userId") long userId,
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId
    ){
        return ResponseEntity.ok().body(userService.getCommentById(userId, postId, commentId));
    }

}
