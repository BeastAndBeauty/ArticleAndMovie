package com.paopao.blog.repository;

import com.paopao.blog.entity.Articles;
import com.paopao.blog.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Long> {

    List<Comments> findByArticleIn(Articles article);
}
