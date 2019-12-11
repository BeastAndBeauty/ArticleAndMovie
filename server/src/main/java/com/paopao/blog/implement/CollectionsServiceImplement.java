package com.paopao.blog.implement;

import com.paopao.blog.entity.Articles;
import com.paopao.blog.entity.Collections;
import com.paopao.blog.entity.Users;
import com.paopao.blog.repository.ArticlesRepository;
import com.paopao.blog.repository.CollectionsRepository;
import com.paopao.blog.repository.UsersRepository;
import com.paopao.blog.service.CollectionsService;
import com.paopao.blog.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/14 12:39
 * @description：
 */

@Service
public class CollectionsServiceImplement implements CollectionsService {

    @Autowired
    private ArticlesRepository articlesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CollectionsRepository collectionsRepository;

    @Override
    public String collectionsInit() {
        for (long i = 1; i < 6; i++) {
            collectionsRepository.save(new Collections(usersRepository.findById(1l).get(), articlesRepository.findById(i).get(), DateUtil.getNowDate()));
        }
        return null;
    }

    @Override
    public List<Collections> getAllCollects(String openId, int page, int size) {
        List<Collections> collectionsList = collectionsRepository.findAllByUserIn(usersRepository.findByOpenId(openId));
        //倒序
        java.util.Collections.reverse(collectionsList);
        List<Collections> resultList = new ArrayList<>();
        try {
            for (int j = 0; j < size; j++)
                resultList.add(collectionsList.get(page * size + j));
        } catch (Exception e) {
        }
        return resultList;
    }

    @Override
    public String collectArticle(String openId, long articleId) {
        Users user = usersRepository.findByOpenId(openId);
        Articles article = articlesRepository.findById(articleId).get();
        collectionsRepository.save(new Collections(user, article, DateUtil.getNowDate()));
        articlesRepository.addCollectionCount(articleId);
        return "成功";
    }

    @Override
    public String cancelCollectArticle(String openId, long articleId) {
        collectionsRepository.cancelCollectArticle(articleId, openId);
        articlesRepository.subtactCollectionCount(articleId);
        return "成功";
    }

    @Override
    public String judgeUserCollectThisArticle(String openId, long articleId) {
        if (collectionsRepository.findByUserInAndArticleIn(usersRepository.findByOpenId(openId), articlesRepository.findById(articleId).get()) == null)
            return "否";
        return "是";
    }

    @Override
    public int getUserCollectionSize(String openId) {
        return collectionsRepository.findAllByUserIn(usersRepository.findByOpenId(openId)).size();
    }
}
