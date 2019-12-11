package com.paopao.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/12 18:31
 * @description：文章收藏表
 */

@Entity
@Data
@JsonIgnoreProperties(value = {"user"})
public class Collections {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "userId")
    private Users user;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "articleId")
    private Articles article;

    //发布日期
    @Column(nullable = false)
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date collectDate;

    public Collections() {
    }

    public Collections(Users user, Articles article, Date collectDate) {
        this.user = user;
        this.article = article;
        this.collectDate = collectDate;
    }
}
