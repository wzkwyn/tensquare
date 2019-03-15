package com.wzk.base.dao;

import com.wzk.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {
    /**
     * 查找推荐标签列表
     * @param recommend  是否推荐
     * @return  标签列表
     */
    List<Label> findAllByRecommendIs(String recommend);

    /**
     * 查找有效标签列表
     * @param state  标签状态
     * @return    标签列表
     */
    List<Label> findAllByStateIs(String state);



}
