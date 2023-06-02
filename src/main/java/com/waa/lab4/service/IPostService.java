package com.waa.lab4.service;

import com.waa.lab4.domain.Post;
import com.waa.lab4.domain.User;

import java.util.List;

public interface IPostService {
    List<Post> findPostsByTitle(String title);

    List<User> getUsersWithPostTitle(String title);
}
