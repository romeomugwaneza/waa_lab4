package com.waa.lab4.service;


import com.waa.lab4.domain.Comment;
import com.waa.lab4.domain.Post;
import com.waa.lab4.domain.User;
import com.waa.lab4.domain.dto.request.CommentRequest;
import com.waa.lab4.domain.dto.request.PostRequest;
import com.waa.lab4.domain.dto.request.UserRequest;
import com.waa.lab4.domain.dto.response.CommentsResponse;
import com.waa.lab4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> findAll() {

        return userRepository.findAll();

    }

    @Override
    public void save(UserRequest userRequest) {

        User newUser = new User();

        newUser.setName(userRequest.name());

        userRepository.save(newUser);

    }

    @Override
    public User findById(long userId) throws NoSuchElementException{

        return userRepository.findById(userId).orElseThrow();

    }

    @Override
    public void addPost(long userId, PostRequest postRequest) throws NoSuchElementException {



        User userFound = userRepository.findById(userId).orElseThrow();

        Post postToAdd = new Post();

        postToAdd.setTitle(postRequest.title());
        postToAdd.setContent(postRequest.content());

        userFound.addPost(postToAdd);

        userRepository.save(userFound);

    }

    @Override
    public List<Post> getPosts(long userId) throws NoSuchElementException {

        User userFound = userRepository.findById(userId).orElseThrow();

        return userFound.getPosts();
    }

    @Override
    public List<User> findUsersByPostsSizeGreaterThan(int numberOfPosts) {

        return userRepository.findByPostsSizeGreaterThan(numberOfPosts);

    }

    @Override
    public void addComment(long userId, long postId, CommentRequest commentRequest) {

        Comment newComment = new Comment();
        newComment.setCommentBody(commentRequest.commentBody());

        User user = userRepository.findById(userId).orElseThrow();

        Post post = user.getPost(postId);

        post.addComment(newComment);

        userRepository.save(user);
    }

    @Override
    public CommentsResponse getAllComments(long userId, long postId) {
        User user = userRepository.findById(userId).orElseThrow();

        CommentsResponse commentsResponse = new CommentsResponse();

        commentsResponse.setComments(user.getPost(postId).getComments());

        return commentsResponse;
    }

    @Override
    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<Post> getPostsWithTitle(String title) {
        return userRepository.findPostsByTitle(title);
    }

    @Override
    public Comment getCommentById(long userId, long postId, long commentId) {
        return userRepository.findCommentById(userId, postId, commentId);
    }

    @Override
    public void testException() throws ArithmeticException {
        double result = 10/0;
    }


}
