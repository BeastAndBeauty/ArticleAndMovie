package com.paopao.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/12 18:53
 * @description：文章顶部轮播图
 */

@Entity
@Data
public class RotationMaps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "articleId")
    private Articles article;

    //轮播图片Url(可以用文章表里的图片替换)
    @Column(nullable = false)
    private String pictureUrl;

    public RotationMaps() {
    }

    public RotationMaps(Articles article, String pictureUrl) {
        this.article = article;
        this.pictureUrl = pictureUrl;
    }
}
