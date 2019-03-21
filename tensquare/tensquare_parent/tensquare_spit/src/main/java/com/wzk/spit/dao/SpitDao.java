package com.wzk.spit.dao;

import com.wzk.spit.pojo.Spit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpitDao extends MongoRepository<Spit,String>{
    /**
     * 根据上级ID查询吐槽列表
     * @param parentId
     * @return
     */
    List<Spit> findByParentid(String parentId, Pageable pageable);

    /**
     * 吐槽点赞
     * @param spitId
   *//* @Query("db.spit.update({'_id':?1},{'thumbup':{$inc:1}})")*//*
    void thumbup(String spitId);*/
}
