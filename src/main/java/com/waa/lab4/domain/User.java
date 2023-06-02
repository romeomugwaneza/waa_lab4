package com.waa.lab4.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;
    String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    List<Post> posts = new ArrayList<>();

    public void addPost(Post post){
        posts.add(post);
    }

    public Post getPost(long postId){

        return posts.stream()
                .filter(p-> Objects.equals(p.getId(), postId))
                .findFirst().orElseThrow();
    }
}
