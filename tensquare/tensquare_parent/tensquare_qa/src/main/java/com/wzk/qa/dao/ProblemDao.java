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
    @Query("SELECT p FROM Problem p,Pl l WHERE p.id = l.problemid AND l.labelid = ?1 ORDER BY replytime desc ")
    List<Problem> newList(String labelId, Pageable pageable);
}
