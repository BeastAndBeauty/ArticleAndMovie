package com.paopao.blog.service;

import com.paopao.blog.entity.Articles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ArticlesService {

    String articleInit();

    Page<Articles> getAllArticles(Pageable pageable);

    Page<Articles> getUserPublishArticles(Pageable pageable,String openId);

    String publishArticle(String openId, String title, String content, String pictureUrl);

    String deleteArticle(long articleId);

    Map getArticleById(String openId, long articleId);

    Page<Articles> searchArticle(Pageable pageable,String titleLike,String contentLike);

    int getUserPublishArticleSize(String openId);

}
