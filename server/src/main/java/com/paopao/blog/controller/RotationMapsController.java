package com.paopao.blog.controller;

import com.paopao.blog.service.RotaionMapsService;
import com.paopao.blog.utils.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/14 16:55
 * @description：
 */

@RestController
public class RotationMapsController {

    @Resource
    RotaionMapsService rotaionMapsService;

    //初始化数据库
    @PostMapping(value = "/blogService/action/initRotationMaps")
    public Map initRotationMaps() {
        rotaionMapsService.rotationMapsInit();
        return ResultUtil.success();
    }

    //获取所有评论
    @PostMapping(value = "/blogService/action/getAllRotationMaps")
    public Map getAllRotationMaps() {
        return ResultUtil.success("RotationMaps",rotaionMapsService.getAllRotationMaps());
    }

}
