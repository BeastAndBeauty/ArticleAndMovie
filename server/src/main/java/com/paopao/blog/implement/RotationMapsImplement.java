package com.paopao.blog.implement;

import com.paopao.blog.entity.Articles;
import com.paopao.blog.entity.RotationMaps;
import com.paopao.blog.repository.ArticlesRepository;
import com.paopao.blog.repository.RotationMapsRepository;
import com.paopao.blog.service.RotaionMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/14 16:46
 * @description：
 */

@Service
public class RotationMapsImplement implements RotaionMapsService {

    @Autowired
    private RotationMapsRepository rotationMapsRepository;
    @Autowired
    private ArticlesRepository articlesRepository;


    @Override
    public String rotationMapsInit() {
        Articles article1 = articlesRepository.findById(1l).get();
        Articles article2 = articlesRepository.findById(2l).get();
        Articles article3 = articlesRepository.findById(3l).get();
        rotationMapsRepository.save(new RotationMaps(article1,"http://h5.paopaovampire.club/rotation-map/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20190614205022.png"));
        rotationMapsRepository.save(new RotationMaps(article2,"http://h5.paopaovampire.club/rotation-map/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20190614205047.png"));
        rotationMapsRepository.save(new RotationMaps(article3,"http://h5.paopaovampire.club/rotation-map/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20190614205053.png"));
        return null;
    }

    @Override
    public List<RotationMaps> getAllRotationMaps() {
        return rotationMapsRepository.findAll();
    }
}
