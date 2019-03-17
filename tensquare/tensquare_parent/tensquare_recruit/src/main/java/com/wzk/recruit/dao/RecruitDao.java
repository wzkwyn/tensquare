package com.wzk.recruit.dao;/*
 @author 吴梓康
 @create 2019/3/16
*/

import com.wzk.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RecruitDao extends JpaRepository<Recruit,String>, JpaSpecificationExecutor<Recruit> {
    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String s);

    List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String s);
}
