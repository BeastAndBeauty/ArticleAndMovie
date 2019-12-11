package com.paopao.blog.repository;

import com.paopao.blog.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/11 11:24
 * @description：
 */

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Transactional
    @Modifying
    @Query(value = "update users set nearlyLoginDate=?1 where openId=?2",nativeQuery = true)
    Integer upDateNearlyLoginDate( Date date,String openId);

    Users findByOpenId(String openid);

}
