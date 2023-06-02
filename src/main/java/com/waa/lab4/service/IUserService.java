package com.waa.lab4.service;

import com.waa.lab4.domain.Comment;
import com.waa.lab4.domain.Post;
import com.waa.lab4.domain.User;
import com.waa.lab4.domain.dto.request.CommentRequest;
import com.waa.lab4.domain.dto.request.PostRequest;
import com.waa.lab4.domain.dto.request.UserRequest;
import com.waa.lab4.domain.dto.response.CommentsResponse;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    void save(UserRequest userRequest);

    User findById(long userId);

    void addPost(long userId, PostRequest post);
    List<Post> getPosts(long userId);

    List<User> findUsersByPostsSizeGreaterThan(int numberOfPosts);

    void addComment(long userId, long postId, CommentRequest commentRequest);

    CommentsResponse getAllComments(long userId, long postId);

    void deleteUserById(long userId);

    List<Post> getPostsWithTitle(String title);

    Comment getCommentById(long userId, long postId, long commentId);

    void testException() throws ArithmeticException;
}
