package com.wzk.base.service;

import com.wzk.base.dao.LabelDao;
import com.wzk.base.pojo.Label;
import com.wzk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    public List<Label> search(Map<String,String> searchMap) {
        Specification spec = getSpecification(searchMap);
        List labeList = labelDao.findAll(spec);
       return labeList;
    }

    /**
     * 条件查询 + 分页
     * @param searchMap
     * @return
     */
    public Page<Label> search(Map<String,String> searchMap,Integer page,Integer size) {
        Specification spec = getSpecification(searchMap);
        Page<Label> labelPage = labelDao.findAll(spec,PageRequest.of(page-1,size));
        return labelPage;
    }

    /**
     * 构建标签特殊查询条件
     * @param searchMap
     * @return
     */
    private Specification getSpecification(Map<String, String> searchMap) {
        return new Specification() {
                @Override
                public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                    String labelname = searchMap.get("labelname");
                    String state = searchMap.get("state");
                    String count = searchMap.get("count");
                    String recommend = searchMap.get("recommend");
                    List<Predicate> predicateList = new ArrayList<>();

                    if(!StringUtils.isEmpty(labelname)) {
                        Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), labelname);
                        predicateList.add(predicate);
                    }
                    if(!StringUtils.isEmpty(state)) {
                        Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), state);
                        predicateList.add(predicate);
                    }
                    if(!StringUtils.isEmpty(count)) {
                        Predicate predicate = criteriaBuilder.equal(root.get("count").as(String.class), count);
                        predicateList.add(predicate);
                    }
                    if(!StringUtils.isEmpty(recommend)) {
                        Predicate predicate = criteriaBuilder.equal(root.get("recommend").as(String.class), recommend);
                        predicateList.add(predicate);
                    }

                    Predicate predicate = criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));

                    return predicate;
                }
            };
    }

}
