package com.waa.lab4.repository;

import com.waa.lab4.domain.Comment;
import com.waa.lab4.domain.Post;
import com.waa.lab4.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE SIZE(u.posts) > :minNumberOfPosts")
    List<User> findByPostsSizeGreaterThan(@Param("minNumberOfPosts") int minNumberOfPosts);

    @Query("SELECT p FROM User u JOIN u.posts p WHERE p.title LIKE %:title%")
    List<Post> findPostsByTitle(@Param("title") String title);

    @Query("SELECT c from User u JOIN u.posts p join p.comments c WHERE u.id = :userId AND p.id = :postId AND c.id = :commentId")
    Comment findCommentById(
            @Param("userId") long userId,
            @Param("postId") long postId,
            @Param("commentId") long commentId);

    @Query("select u from User u join u.posts p where p.title LIKE %:title%")
    List<User> findUsersWithPostTitle(@Param("title") String title);

}
