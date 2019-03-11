package com.wzk.base.service;

import com.wzk.base.dao.LabelDao;
import com.wzk.base.pojo.Label;
import com.wzk.entity.PageResult;
import com.wzk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有标签
     * @return  标签列表
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 根据Id查询标签
     * @param id  标签Id
     * @return  标签
     */
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    /**
     * 添加标签
     * @param label 标签
     * 使用twitter的IdWorker产生一个不可重复的id
     */
    public void add(Label label) {
        label.setId(String.valueOf(idWorker.nextId()));
        labelDao.save(label);
    }

    /**
     * 更新标签
     * @param label   标签
     */
    public void update(Label label) {
        labelDao.save(label);
    }

    /**
     * 根据Id删除标签
     * @param id   标签Id
     */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    /**
     * 查询推荐标签列表
     * @return
     */
    public List<Label> findTopList() {
        return labelDao.findAllByRecommendIs("1");
    }

    /**
     * 查询有效列表
     * @return    标签列表
     */
    public List<Label> findList() {
        return labelDao.findAllByStateIs("1");
    }


}
