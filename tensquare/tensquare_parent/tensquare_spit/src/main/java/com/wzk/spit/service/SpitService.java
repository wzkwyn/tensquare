package com.wzk.spit.service;

import com.wzk.spit.dao.SpitDao;
import com.wzk.spit.pojo.Spit;
import com.wzk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 获取所有吐槽
     * @return
     */
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    /**
     * 根据id获取吐槽
     * @return
     */
    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }

    /**
     * 添加吐槽
     * @return
     */
    public void add(Spit spit) {
        spit.set_id(idWorker.nextId()+"");
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        spitDao.save(spit);
        haveParentId(spit);
    }

    /**
     * 更新吐槽
     * @return
     */
    public void update(Spit spit) {
        spitDao.save(spit);
    }

    /**
     * 删除吐槽
     * @return
     */
    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    /**
     * 根据上级ID查询吐槽列表
     * @param parentId
     * @return
     */
    public List<Spit> findByParentId(String parentId,Integer page,Integer size) {
        return spitDao.findByParentid(parentId, PageRequest.of(page-1,size));
    }

    /**
     * 吐槽点赞
     * @param spitId
     */
    public void thumbup(String spitId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update = new Update();
        update.inc("thumbup",1);
        //更新符合条件的第一个文档
        mongoTemplate.updateFirst(query,update,"spit");
    }

    /**
     * 上级吐槽回复数+1
     * @param spit
     * @return
     */
    private void haveParentId(Spit spit) {
        String parentid = spit.getParentid();
        if(!StringUtils.isEmpty(parentid)) {
            haveParentId(spitDao.findById(parentid).get());
            //上级吐槽回复数加一
            Query query = new Query();
            Update update = new Update();
            query.addCriteria(Criteria.where("_id").is(parentid));
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
    }
}
