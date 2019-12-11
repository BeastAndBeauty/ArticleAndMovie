package com.paopao.blog.repository;

import com.paopao.blog.entity.Articles;
import com.paopao.blog.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface ArticlesRepository extends JpaRepository<Articles, Long> {

    Page<Articles> findByTitleLikeOrContentLike(Pageable pageable, String titleLike, String contentLike);

    Page<Articles> findByUserIn(Pageable pageable, Users user);

    List<Articles> findByUserIn(Users user);

    @Transactional
    @Modifying
    @Query(value = "update articles set collectionCount=collectionCount+1 where id=?1", nativeQuery = true)
    Integer addCollectionCount(long articleId);

    @Transactional
    @Modifying
    @Query(value = "update articles set collectionCount=collectionCount-1 where id=?1", nativeQuery = true)
    Integer subtactCollectionCount(long articleId);

    @Transactional
    @Modifying
    @Query(value = "update articles set commentCount=commentCount+1 where id=?1", nativeQuery = true)
    Integer addCommentCount(long articleId);

    @Transactional
    @Modifying
    @Query(value = "update articles set commentCount=commentCount-1 where id=?1", nativeQuery = true)
    Integer subtactCommentCount(long articleId);


}
