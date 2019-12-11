package com.paopao.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/11 10:37
 * @description：用户表
 */

@Entity
@Data
//返回数据中忽略这些字段
//同时可以在一对多（一对一和多对多暂未测试)查询的时候防止进入死循环
@JsonIgnoreProperties(value = { "articlesList","collectionsList","commentsList"})
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Articles> articlesList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Collections> collectionsList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comments> commentsList;

    //用户唯一标识
    @Column(nullable = false, unique = true)
    private String openId;

    //用户名
    @Column(nullable = false)
    private String nickName;

    //用户头像Url
    @Column(nullable = false)
    private String avatarUrl;

    //首次登录
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date firstLoginDate;

    //最近登录
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date nearlyLoginDate;

    public Users() {}

    public Users(String openId, String nickName, String avatarUrl, Date firstLoginDate, Date nearlyLoginDate) {
        this.openId = openId;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.firstLoginDate = firstLoginDate;
        this.nearlyLoginDate = nearlyLoginDate;
    }

}
