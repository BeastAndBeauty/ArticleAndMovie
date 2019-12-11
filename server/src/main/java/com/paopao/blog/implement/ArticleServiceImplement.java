package com.paopao.blog.implement;

import com.paopao.blog.entity.Articles;
import com.paopao.blog.entity.Users;
import com.paopao.blog.repository.ArticlesRepository;
import com.paopao.blog.repository.CollectionsRepository;
import com.paopao.blog.repository.UsersRepository;
import com.paopao.blog.service.ArticlesService;
import com.paopao.blog.utils.DatabaseDataUtil;
import com.paopao.blog.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/12 20:09
 * @description：
 */

@Service
public class ArticleServiceImplement implements ArticlesService {

    @Autowired
    private ArticlesRepository articlesRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CollectionsRepository collectionsRepository;

    @Override
    public String articleInit() {
        long usersCount = usersRepository.count();
        System.out.println("usersCount=" + usersCount);
        List<Articles> articles = DatabaseDataUtil.initArticlesTable();
        for (int i = 0; i < articles.size(); i++) {
            Articles article = articles.get(i);
            articlesRepository.save(new Articles(usersRepository.findById((long) ((int) (i % usersCount) + 1)).get(),
                    article.getTitle(),
                    article.getContent(),
                    article.getPublishDate(),
                    article.getPictureUrl(), article.getCollectionCount(), article.getCommentCount()));
        }
        return null;
    }

    @Override
    public Page<Articles> getAllArticles(Pageable pageable) {
        return articlesRepository.findAll(pageable);
    }

    @Override
    public Page<Articles> getUserPublishArticles(Pageable pageable, String openId) {
        return articlesRepository.findByUserIn(pageable, usersRepository.findByOpenId(openId));
    }

    @Override
    public String publishArticle(String openId, String title, String content, String pictureUrl) {
        if (usersRepository.findByOpenId(openId) == null)
            return "失败";
        else {
            Users user = usersRepository.findByOpenId(openId);
            articlesRepository.save(new Articles(user, title, content, DateUtil.getNowDate(), pictureUrl, 0, 0));
            return "成功";
        }
    }

    @Override
    public String deleteArticle(long articleId) {
        articlesRepository.deleteById(articleId);
        return "成功";
    }

    @Override
    public Map getArticleById(String openId, long articleId) {
        Map<String,Object> map=new HashMap<>();
        map.put("Article",articlesRepository.findById(articleId).get());

        if (collectionsRepository.findByUserInAndArticleIn(usersRepository.findByOpenId(openId), articlesRepository.findById(articleId).get()) == null)
            map.put("IsCollected",false);
        else
            map.put("IsCollected",true);

        return map;
    }

    @Override
    public Page<Articles> searchArticle(Pageable pageable, String titleLike, String contentLike) {
        return articlesRepository.findByTitleLikeOrContentLike(pageable, titleLike, contentLike);
    }

    @Override
    public int getUserPublishArticleSize(String openId) {
        return articlesRepository.findByUserIn(usersRepository.findByOpenId(openId)).size();
    }


}
