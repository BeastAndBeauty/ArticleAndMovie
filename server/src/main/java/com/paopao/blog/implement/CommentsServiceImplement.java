package com.paopao.blog.implement;

import com.paopao.blog.entity.Articles;
import com.paopao.blog.entity.Collections;
import com.paopao.blog.entity.Comments;
import com.paopao.blog.entity.Users;
import com.paopao.blog.repository.ArticlesRepository;
import com.paopao.blog.repository.CollectionsRepository;
import com.paopao.blog.repository.CommentsRepository;
import com.paopao.blog.repository.UsersRepository;
import com.paopao.blog.service.CommentsService;
import com.paopao.blog.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/14 15:35
 * @description：
 */

@Service
public class CommentsServiceImplement implements CommentsService {


    @Autowired
    private ArticlesRepository articlesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public String commentsInit() {
        for (long i = 1; i < 6; i++) {
            commentsRepository.save(new Comments(usersRepository.findById(1l).get(), articlesRepository.findById(i).get(), DateUtil.getNowDate(), "打卡:" + i));
        }
        return null;
    }

    @Override
    public List<Comments> getAllComments(long articleId, int page, int size) {
        List<Comments> commentsList = commentsRepository.findByArticleIn(articlesRepository.findById(articleId).get());
        //倒序
        java.util.Collections.reverse(commentsList);
        List<Comments> resultList = new ArrayList<>();
        try {
            for (int j = 0; j < size; j++) {
                resultList.add(commentsList.get(page * size + j));
            }
        } catch (Exception e) {
        }

        return resultList;
    }

    @Override
    public String publishComment(String openId, long articleId, String content) {
        Users user = usersRepository.findByOpenId(openId);
        Articles article = articlesRepository.findById(articleId).get();
        commentsRepository.save(new Comments(user, article, DateUtil.getNowDate(), content));
        articlesRepository.addCommentCount(articleId);
        return "成功";
    }

    @Override
    public String deleteComment(long commentsId, long articleId) {
        commentsRepository.deleteById(commentsId);
        articlesRepository.subtactCommentCount(articleId);
        return "成功";
    }
}
