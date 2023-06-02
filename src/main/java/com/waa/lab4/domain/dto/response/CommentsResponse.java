package com.waa.lab4.domain.dto.response;

import com.waa.lab4.domain.Comment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class CommentsResponse {
    private List<Comment> comments = new ArrayList<>();
}
