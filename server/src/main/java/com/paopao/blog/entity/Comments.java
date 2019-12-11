package com.paopao.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/12 18:43
 * @description：文章评论表
 */

@Entity
@Data
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "userId")
    private Users user;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "articleId")
    private Articles article;

    //评论日期
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date publishDate;

    //评论内容
    @Lob
    @Column(nullable = false, columnDefinition = "longtext")
    private String content;

    public Comments() {
    }

    public Comments(Users user, Articles article, Date publishDate, String content) {
        this.user = user;
        this.article = article;
        this.publishDate = publishDate;
        this.content = content;
    }
}

