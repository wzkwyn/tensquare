package com.wzk.recruit.service;
/*
 @author 吴梓康
 @create 2019/3/16
*/

import com.wzk.recruit.dao.RecruitDao;
import com.wzk.recruit.pojo.Recruit;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RecruitService {
    @Autowired
    private RecruitDao recruitDao;
    @Autowired
    private IdWorker idWorker;
    /**
     * 查询所有招聘信息
     * @return
     */
    public List<Recruit> findAll() {
        return recruitDao.findAll();
    }

    /**
     * 根据ID查询招聘信息
     * @param recruitId
     * @return
     */
    public Recruit findById(String recruitId) {
        return recruitDao.findById(recruitId).get();
    }
    /**
     * 增加招聘信息
     * @param recruit
     * @return
     */
    public void add(Recruit recruit) {
        //设置主键
        recruit.setId(String.valueOf(idWorker.nextId()));
        //设置创建时间
        //recruit.setCreatetime(new Date());
        recruitDao.save(recruit);
    }

    /**
     * 更新招聘信息
     * @param recruit
     * @return
     */
    public void update(Recruit recruit) {
        recruitDao.save(recruit);
    }

    /**
     * 根据ID删除招聘信息
     * @param recruitId
     * @return
     */
    public void deleteById(String recruitId) {
        recruitDao.deleteById(recruitId);
    }

    /**
     * 根据条件查询招聘信息列表
     * @param searchMap
     * @return
     */
    public List<Recruit> search(Map<String, String> searchMap) {
        Specification<Recruit> specification = getSpecification(searchMap);
        return recruitDao.findAll(specification);
    }

    /**
     * 根据条件查询招聘信息列表+分页
     * @param searchMap
     * @return
     */
    public Page<Recruit> search(Map<String, String> searchMap, Integer page, Integer size) {
        Specification<Recruit> specification = getSpecification(searchMap);
        return recruitDao.findAll(specification,PageRequest.of(page-1,size));
    }

    /**
     * 组装查询条件
     * @param searchMap
     * @return
     */
    private Specification<Recruit> getSpecification(Map<String,String> searchMap) {
         Specification<Recruit> specification = new Specification<Recruit>() {
             @Override
             public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                 String jobname = searchMap.get("jobname");
                 String salary = searchMap.get("salary");
                 String condition = searchMap.get("condition");
                 String education = searchMap.get("education");
                 String type = searchMap.get("type");
                 String address = searchMap.get("address");
                 String eid = searchMap.get("eid");
                 String createtime = searchMap.get("createtime");
                 String state = searchMap.get("state");
                 String url = searchMap.get("url");
                 String label = searchMap.get("label");
                 String content1 = searchMap.get("content1");
                 String content2 = searchMap.get("content2");

                 List<Predicate> predicateList = new ArrayList<>();
                 if(!StringUtils.isEmpty(jobname)) {
                     Predicate predicate = criteriaBuilder.like(root.get("jobname").as(String.class), "%" + jobname + "%");
                     predicateList.add(predicate);
                 }
                 if(!StringUtils.isEmpty(salary)) {
                     String[] salaryArray = salary.split("-");
                     Predicate predicate = criteriaBuilder.between(root.get("salary").as(String.class), salaryArray[0], salaryArray[1]);
                     predicateList.add(predicate);
                 }
                 if(!StringUtils.isEmpty(condition)) {
                     Predicate predicate = criteriaBuilder.equal(root.get("condition").as(String.class), condition);
                     predicateList.add(predicate);
                 }
                 if(!StringUtils.isEmpty(education)) {
                     Predicate predicate = criteriaBuilder.equal(root.get("education").as(String.class), education);
                     predicateList.add(predicate);
                 }
                 if(!StringUtils.isEmpty(type)) {
                     Predicate predicate = criteriaBuilder.equal(root.get("type").as(String.class), type);
                     predicateList.add(predicate);
                 }
                 if(!StringUtils.isEmpty(address)) {
                     Predicate predicate = criteriaBuilder.equal(root.get("address").as(String.class), address);
                     predicateList.add(predicate);
                 }
                 if(!StringUtils.isEmpty(eid)) {
                     Predicate predicate = criteriaBuilder.equal(root.get("eid").as(String.class), eid);
                     predicateList.add(predicate);
                 }
                 if(!StringUtils.isEmpty(createtime)) {
                     Date date = new Date(createtime);
                     Predicate predicate = criteriaBuilder.greaterThan(root.get("createtime").as(Date.class), date);
                     predicateList.add(predicate);
                 }
                 if(!StringUtils.isEmpty(state)) {
                     Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), state);
                     predicateList.add(predicate);
                 }
                 if(!StringUtils.isEmpty(url)) {
                     Predicate predicate = criteriaBuilder.equal(root.get("url").as(String.class), url);
                     predicateList.add(predicate);
                 }
                 if(!StringUtils.isEmpty(label)) {
                     Predicate predicate = criteriaBuilder.equal(root.get("label").as(String.class), label);
                     predicateList.add(predicate);
                 }
                 if(!StringUtils.isEmpty(content1)) {
                     Predicate predicate = criteriaBuilder.equal(root.get("content1").as(String.class), content1);
                     predicateList.add(predicate);
                 }
                 if(!StringUtils.isEmpty(content2)) {
                     Predicate predicate = criteriaBuilder.equal(root.get("content2").as(String.class), content2);
                     predicateList.add(predicate);
                 }

                 Predicate predicate = criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));

                 return predicate;
             }
         };
         return specification;
    }

    /**
     * 获取推荐职位
     * @return
     */
    public List<Recruit> getRecommend() {
       return recruitDao.findTop4ByStateOrderByCreatetimeDesc("2");

    }
    /**
     * 获取最新职位
     * @return
     */
    public List<Recruit> getnewList() {
        return recruitDao.findTop12ByStateNotOrderByCreatetimeDesc("0");
    }
}
