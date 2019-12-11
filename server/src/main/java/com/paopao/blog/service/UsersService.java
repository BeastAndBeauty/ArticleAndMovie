package com.paopao.blog.service;

import com.paopao.blog.entity.Users;

import java.util.List;

public interface UsersService {

    List<Users> getUserList();

    String userAuthorizedLogin(String code, String nickName, String avatarUrl);

    String usersInit();
}
