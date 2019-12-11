package com.paopao.blog.controller;

import com.paopao.blog.entity.Users;
import com.paopao.blog.service.UsersService;
import com.paopao.blog.utils.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/12 10:43
 * @description：
 */

@RestController
public class UsersController {

    @Resource
    UsersService usersService;

    //初始化数据库
    @PostMapping(value = "/blogService/action/initUsers")
    public Map init() {
        usersService.usersInit();
        return ResultUtil.success();
    }


    @PostMapping(value = "/blogService/action/getUsers")
    public Map getAllUsers() {
        return ResultUtil.success("UserList", usersService.getUserList());
    }

    @PostMapping(value = "/blogService/action/userAuthorizedLogin")
    public Map userAuthorizedLogin(@RequestBody Map<String, String> map) {
        String code = map.get("code");
        String nickName = map.get("nickName");
        String avatarUrl = map.get("avatarUrl");

        System.out.println("code:" + code + " nickName:" + nickName + " url:" + avatarUrl);
        String result = usersService.userAuthorizedLogin(code, nickName, avatarUrl);
        if (result.equals("F"))
            return ResultUtil.error();

        return ResultUtil.success("OpenId", result);
    }









}
