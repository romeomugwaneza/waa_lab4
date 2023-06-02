package com.waa.lab4.service;

import com.waa.lab4.domain.Post;
import com.waa.lab4.domain.User;
import com.waa.lab4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostService implements IPostService{
    private final UserRepository userRepository;

    @Autowired
    public PostService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> findPostsByTitle(String title) {
        return userRepository.findPostsByTitle(title);
    }

    @Override
    public List<User> getUsersWithPostTitle(String title) {
        return userRepository.findUsersWithPostTitle(title);
    }
}
