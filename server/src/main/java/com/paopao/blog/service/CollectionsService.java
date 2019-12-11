package com.paopao.blog.service;

import com.paopao.blog.entity.Articles;
import com.paopao.blog.entity.Collections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CollectionsService {

    String collectionsInit();

    List<Collections> getAllCollects(String openId, int page, int size);

    String collectArticle(String openId,long articleId);

    String cancelCollectArticle(String openId,long collectsId);

    String judgeUserCollectThisArticle(String openId,long articleId);

    int getUserCollectionSize(String openId);

}
