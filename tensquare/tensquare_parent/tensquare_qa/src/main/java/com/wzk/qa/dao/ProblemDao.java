package com.wzk.qa.dao;

import com.wzk.qa.pojo.Problem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    /**
     * 最新问答
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("SELECT p FROM Problem p,Pl l WHERE p.id = l.problemid AND l.labelid = ?1 ORDER BY replytime desc ")
    List<Problem> newList(String labelId, Pageable pageable);

    /**
     * 热门回答
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("SELECT p FROM Problem p,Pl l WHERE p.id = l.problemid AND l.labelid = ?1 ORDER BY reply desc ")
    List<Problem> hotList(String labelId, Pageable pageable);

    /**
     * 等待回答列表
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("SELECT p FROM Problem p,Pl l WHERE p.id = l.problemid AND l.labelid = ?1 ORDER BY createtime desc ")
    List<Problem> waitlist(String labelId, Pageable pageable);
}
