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
 * @date ：Created in 2019/6/12 17:47
 * @description：文章表
 */

@Entity
@Data
@JsonIgnoreProperties(value = {"collectionsList", "commentsList", "rotationMapsList"})
public class Articles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "userId")
    private Users user;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Collections> collectionsList;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comments> commentsList;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RotationMaps> rotationMapsList;

    //文章标题
    @Column(nullable = false)
    private String title;

    //文章内容
    @Lob
    @Column(nullable = false, columnDefinition = "longtext")
    private String content;

    //发布日期
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "MM-dd HH:mm", timezone = "GMT+8")
    private Date publishDate;

    //文章图片Url
    @Column(nullable = false)
    private String pictureUrl;

    //文章收藏数
    @Column(nullable = false)
    private long collectionCount;

    //文章评论数
    @Column(nullable = false)
    private long commentCount;

    public Articles() {}

    public Articles(String title, String content, Date publishDate, String pictureUrl, long collectionCount, long commentCount) {
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
        this.pictureUrl = pictureUrl;
        this.collectionCount = collectionCount;
        this.commentCount = commentCount;
    }

    public Articles(Users user, String title, String content, Date publishDate, String pictureUrl, long collectionCount, long commentCount) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
        this.pictureUrl = pictureUrl;
        this.collectionCount = collectionCount;
        this.commentCount = commentCount;
    }

}
