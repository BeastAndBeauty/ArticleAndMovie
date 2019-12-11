package com.paopao.blog.implement;

import com.paopao.blog.entity.Users;
import com.paopao.blog.repository.UsersRepository;
import com.paopao.blog.service.UsersService;
import com.paopao.blog.utils.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/11 11:57
 * @description：
 */

@Service
public class UserServiceImplement implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public String usersInit() {


//        usersRepository.save(new Users("openId111", "泡泡", "avatarUrl111", DateUtil.getNowDate(), DateUtil.getNowDate()));
//        usersRepository.save(new Users("openId222", "OffLine", "avatarUrl111", DateUtil.getNowDate(), DateUtil.getNowDate()));
//        usersRepository.save(new Users("openId333", "OnLine", "avatarUrl111", DateUtil.getNowDate(), DateUtil.getNowDate()));
//        usersRepository.save(new Users("openId444", "好日子", "avatarUrl111", DateUtil.getNowDate(), DateUtil.getNowDate()));
//        usersRepository.save(new Users("openId555", "LikeWhat", "avatarUrl111", DateUtil.getNowDate(), DateUtil.getNowDate()));

        for(Users user:DatabaseDataUtil.initUsersTable()){
            usersRepository.save(user);
        }

        return null;
    }


    @Override
    public List<Users> getUserList() {
        return usersRepository.findAll();
    }

    @Override
    public String userAuthorizedLogin(String code, String nickName, String avatarUrl) {
        String params = "appid=" + WXUtil.AppID + "&secret=" + WXUtil.AppSecret + "&js_code=" + code + "&grant_type=authorization_code";

        try {
            JSONObject jsonObject = JSONObject.fromObject(HttpUtil.get(WXUtil.wxLoginUrl, params));
            if (usersRepository.upDateNearlyLoginDate(DateUtil.getNowDate(), jsonObject.getString("openid")) == 0)
                usersRepository.save(new Users(jsonObject.getString("openid"), nickName, avatarUrl, DateUtil.getNowDate(), DateUtil.getNowDate()));
            return jsonObject.getString("openid");
        } catch (Exception e) {
            e.printStackTrace();
            return "F";
        }


    }


}
