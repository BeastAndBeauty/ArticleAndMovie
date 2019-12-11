package com.paopao.blog.controller;

import com.paopao.blog.service.ArticlesService;
import com.paopao.blog.service.CollectionsService;
import com.paopao.blog.utils.ResultUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/14 13:46
 * @description：
 */

@RestController
public class CollectionsController {

    @Resource
    CollectionsService collectionsService;
    @Resource
    ArticlesService articlesService;

    //初始化数据库
    @PostMapping(value = "/blogService/action/initCollections")
    public Map initCollections() {
        collectionsService.collectionsInit();
        return ResultUtil.success();
    }

    //获取所有文章
    @PostMapping(value = "/blogService/action/getAllCollections")
    public Map getAllCollections(@RequestBody Map<String, Object> map) {
        int page = (int) map.get("page");
        int size = (int) map.get("size");
        String openId = (String) map.get("openId");
        return ResultUtil.success("CollectionList", collectionsService.getAllCollects(openId, page, size));
    }


    //收藏文章
    @PostMapping(value = "/blogService/action/collectArticle")
    public Map collectArticle(@RequestBody Map<String, Object> map) {
        String openId = (String) map.get("openId");
        long articleId = Long.valueOf((String) map.get("articleId"));
        return ResultUtil.success(collectionsService.collectArticle(openId, articleId));
    }

    //取消收藏文章
    @PostMapping(value = "/blogService/action/cancelCollectArticle")
    public Map cancelCollectArticle(@RequestBody Map<String, Object> map) {
        long articleId = Long.valueOf((String) map.get("articleId"));
        String openId = (String) map.get("openId");
        return ResultUtil.success(collectionsService.cancelCollectArticle(openId, articleId));
    }

    //判断用户是否收藏该文章
    @PostMapping(value = "/blogService/action/judgeUserCollectThisArticle")
    public Map judgeUserCollectThisArticle(@RequestBody Map<String, Object> map) {
        long articleId = Long.valueOf((String) map.get("articleId"));
        String openId = (String) map.get("openId");
        return ResultUtil.success(collectionsService.judgeUserCollectThisArticle(openId, articleId));
    }

    //获取用户收藏数和发布文章数
    @PostMapping(value = "/blogService/action/getUserCollectionAndArticleCount")
    public Map getUserCollectionAndArticleCount(@RequestBody Map<String, Object> map) {
        String openId = (String) map.get("openId");
        Map<String,Integer> m=new HashMap<>();
        m.put("CollectionCount",collectionsService.getUserCollectionSize(openId));
        m.put("PublishCount",articlesService.getUserPublishArticleSize(openId));
        return ResultUtil.success(m);
    }

}
