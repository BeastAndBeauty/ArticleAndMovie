package com.paopao.blog.service;

import com.paopao.blog.entity.Collections;
import com.paopao.blog.entity.Comments;

import java.util.List;

public interface CommentsService {

    String commentsInit();

    List<Comments> getAllComments(long articleId, int page, int size);

    String publishComment(String openId,long articleId,String content);

    String deleteComment(long commentsId, long articleId);

}
