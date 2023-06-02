package com.waa.lab4.domain.dto.response;

import com.waa.lab4.domain.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class UsersResponse {
    private List<User> users = new ArrayList<>();
}
