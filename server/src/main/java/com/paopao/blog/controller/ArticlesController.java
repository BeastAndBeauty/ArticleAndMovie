package com.paopao.blog.controller;

import com.paopao.blog.entity.Articles;
import com.paopao.blog.service.ArticlesService;
import com.paopao.blog.utils.QiNiuUtil;
import com.paopao.blog.utils.ResultUtil;
import com.paopao.blog.utils.Util;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/13 18:55
 * @description：
 */

@RestController
public class ArticlesController {

    @Resource
    ArticlesService articlesService;


    //初始化数据库
    @PostMapping(value = "/blogService/action/initArticle")
    public Map init() {
        articlesService.articleInit();
        return ResultUtil.success();
    }

    //获取所有文章
    @PostMapping(value = "/blogService/action/getAllArticles")
    public Map getAllArticles(@RequestBody Map<String, Object> map) {
        int page = (int) map.get("page");
        int size = (int) map.get("size");
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        PageRequest pageRequest=PageRequest.of(page, size, sort);
        return ResultUtil.success("ArticleList", articlesService.getAllArticles(pageRequest).getContent());
    }

    //获取指定用户发布的文章
    @PostMapping(value = "/blogService/action/getUserPublishArticles")
    public Map getUserPublishArticles(@RequestBody Map<String, Object> map){
        int page = (int) map.get("page");
        int size = (int) map.get("size");
        String openId= (String) map.get("openId");
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        PageRequest pageRequest=PageRequest.of(page, size, sort);
        return ResultUtil.success("UserPublishArticleList", articlesService.getUserPublishArticles(pageRequest,openId).getContent());
    }

    //发布文章
    @PostMapping(value = "/blogService/action/publishArticle")
    public Map publishArticle(@RequestParam("file") MultipartFile multipartFile,
                              @RequestParam("openId") String openId,
                              @RequestParam("title") String title,
                              @RequestParam("content") String content) {
        try {
            FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
            String uploadQiNiUResult = QiNiuUtil.uploadImageToQiNiu(fileInputStream);
            if (uploadQiNiUResult.equals("F"))
                return ResultUtil.error("上传失败");
            String insertArticleResult = articlesService.publishArticle(openId, title, content, uploadQiNiUResult);
            if (insertArticleResult.equals("失败"))
                return ResultUtil.error();
            return ResultUtil.success(insertArticleResult);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }

    //删除文章
    @PostMapping(value = "/blogService/action/deleteArticle")
    public Map deleteArticle(@RequestBody Map<String, Long> map) {
        return ResultUtil.success(articlesService.deleteArticle(map.get("id")));
    }

    //通过文章Id获取文章
    @PostMapping(value = "/blogService/action/getArticleById")
    public Map getArticleById(@RequestBody Map<String, Object> map) {
        String openId= (String) map.get("openId");
        long articleId= Long.valueOf((String)map.get("articleId"));
        return ResultUtil.success(articlesService.getArticleById(openId,articleId));
    }

    //搜索文章
    @PostMapping(value = "/blogService/action/searchArticle")
    public Map searchArticle(@RequestBody Map<String, Object> map) {
        int page = (int) map.get("page");
        int size = (int) map.get("size");
        String searchContent = "%" + (String) map.get("searchContent") + "%";

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return ResultUtil.success("ArticleList", articlesService.searchArticle(pageRequest, searchContent, searchContent).getContent());
    }


}
