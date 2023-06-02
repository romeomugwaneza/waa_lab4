package com.waa.lab4.domain.dto;
import com.waa.lab4.domain.Post;
import java.util.List;
public record UserDTO(long id, String name, List<Post> posts){}
