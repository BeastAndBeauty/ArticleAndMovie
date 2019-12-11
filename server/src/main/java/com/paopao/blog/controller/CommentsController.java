package com.paopao.blog.controller;

import com.paopao.blog.service.CommentsService;
import com.paopao.blog.utils.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/14 16:19
 * @description：
 */

@RestController
public class CommentsController {

    @Resource
    CommentsService commentsService;

    //初始化数据库
    @PostMapping(value = "/blogService/action/initComments")
    public Map initComments() {
        commentsService.commentsInit();
        return ResultUtil.success();
    }

    //获取所有评论
    @PostMapping(value = "/blogService/action/getAllComments")
    public Map getAllComments(@RequestBody Map<String, Object> map) {
        int page = (int) map.get("page");
        int size = (int) map.get("size");
        long articleId = Long.valueOf((String) map.get("articleId"));
        return ResultUtil.success("CommentsList", commentsService.getAllComments(articleId,page,size));
    }

    //发表评论
    @PostMapping(value = "/blogService/action/publishComment")
    public Map publishComment(@RequestBody Map<String, Object> map) {
        String openId = (String) map.get("openId");
        long articleId = Long.valueOf((String) map.get("articleId"));
        String content= (String) map.get("content");
        return ResultUtil.success(commentsService.publishComment(openId,articleId,content));
    }

    //删除评论
    @PostMapping(value = "/blogService/action/deleteComment")
    public Map deleteComment(@RequestBody Map<String, Long> map) {
        long commentsId = map.get("commentsId");
        long articleId = map.get("articleId");
        return ResultUtil.success(commentsService.deleteComment(commentsId,articleId));
    }

}
