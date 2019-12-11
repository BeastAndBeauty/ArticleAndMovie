package com.paopao.blog.repository;

import com.paopao.blog.entity.Articles;
import com.paopao.blog.entity.Collections;
import com.paopao.blog.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface CollectionsRepository extends JpaRepository<Collections,Long> {

    List<Collections> findAllByUserIn(Users user);

    Collections findByUserInAndArticleIn(Users user, Articles article);

    @Transactional
    @Modifying
    @Query(value = "delete from collections where articleId = (select id from articles where id=?1) and userId = (select id from users where openId=?2)",nativeQuery = true)
    Integer cancelCollectArticle(long articleId,String openId);

}
