package com.waa.lab4.domain.dto.response;

import com.waa.lab4.domain.Post;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PostsResponse {
    private List<Post> posts = new ArrayList<>();
}
