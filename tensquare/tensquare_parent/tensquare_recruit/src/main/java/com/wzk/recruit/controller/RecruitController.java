package com.wzk.recruit.controller;
/*
 @author 吴梓康
 @create 2019/3/16
*/

import com.wzk.entity.PageResult;
import com.wzk.entity.Result;
import com.wzk.entity.StatusCode;
import com.wzk.recruit.pojo.Recruit;
import com.wzk.recruit.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("recruit")
public class RecruitController {
    @Autowired
    private RecruitService recruitService;

    /**
     * 查询全部招聘信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Recruit> recruitList =  recruitService.findAll();
        return new Result(true, StatusCode.OK,"查询全部列表成功",recruitList);
    }

    /**
     * 根据ID查询招聘信息
     * @param recruitId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "{recruitId}")
    public Result findById(@PathVariable String recruitId) {
        Recruit recruit =  recruitService.findById(recruitId);
        return new Result(true, StatusCode.OK,"根据ID查询招聘信息成功",recruit);
    }

    /**
     * 增加招聘信息
     * @param recruit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Recruit recruit) {
        recruitService.add(recruit);
        return new Result(true, StatusCode.OK,"增加招聘信息成功");
    }

    /**
     * 更新招聘信息
     * @param recruit
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT,value = "/{recruitId}")
    public Result update(@RequestBody Recruit recruit,@PathVariable String recruitId) {
        recruit.setId(recruitId);
        recruitService.update(recruit);
        return new Result(true, StatusCode.OK,"更新招聘信息成功");
    }

    /**
     * 根据ID删除招聘信息
     * @param recruitId
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE,value = "{recruitId}")
    public Result deleteById(@PathVariable String recruitId) {
        recruitService.deleteById(recruitId);
        return new Result(true, StatusCode.OK,"根据ID删除招聘信息成功");
    }

    /**
     * 根据条件查询招聘信息列表
     * @param searchMap
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "search")
    public Result search(@RequestBody Map<String,String> searchMap) {
        List<Recruit> recruitList = recruitService.search(searchMap);
        return new Result(true,StatusCode.OK,"根据条件查询招聘信息列表成功",recruitList);
    }

    /**
     * 搜索+分页
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/search/{page}/{size}")
    public Result search(@RequestBody Map<String,String> searchMap,@PathVariable Integer page,@PathVariable Integer size) {
        Page<Recruit> recruitPage = recruitService.search(searchMap,page,size);
        return new Result(true,StatusCode.OK,"根据条件查询招聘信息列表成功",new PageResult<Recruit>(recruitPage.getTotalElements(),recruitPage.getContent()));
    }

    /**
     * 获取推荐职位
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "search/recommend")
    public Result getRecommend() {
        List<Recruit> recruitList = recruitService.getRecommend();
        return new Result(true,StatusCode.OK,"获取推荐职位成功",recruitList);
    }

    /**
     * 获取最新职位
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "search/newlist")
    public Result getNewList() {
        List<Recruit> recruitList = recruitService.getnewList();
        return new Result(true,StatusCode.OK,"获取最新职位成功",recruitList);
    }

}
