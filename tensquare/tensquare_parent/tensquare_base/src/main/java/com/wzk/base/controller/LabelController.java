package com.wzk.base.controller;

import com.wzk.base.pojo.Label;
import com.wzk.base.service.LabelService;
import com.wzk.entity.PageResult;
import com.wzk.entity.Result;
import com.wzk.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 查询所有标签
     * @return  查询结果
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Label> labelList = labelService.findAll();
        return new Result(true,StatusCode.OK,"查询成功",labelList);
    }

    /**
     * 根据Id查询标签
     * @param id   标签id
     * @return   标签
     */
    @RequestMapping(method = RequestMethod.GET ,value="/{id}")
    public Result findById(@PathVariable String id) {
        Label label = labelService.findById(id);
        return new Result(true,StatusCode.OK,"查询成功",label);
    }

    /**
     * 增加标签
     * @param label   标签
     * @return   操作结果
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK,"增加成功");

    }

    /**
     * 更新标签
     * @param label  标签
     * @param id   标签id
     * @return   操作结果
     */
    @RequestMapping(method = RequestMethod.PUT,value="/{id}")
    public Result update(@RequestBody Label label,@PathVariable String id) {
        label.setId(id);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 根据id删除标签
     * @param id   标签id
     * @return   操作结果
     */
    @RequestMapping(method = RequestMethod.DELETE,value="/{id}")
    public Result deleteById(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 查询推荐标签列表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value="toplist")
    public Result findTopList() {
        List<Label> topList = labelService.findTopList();
        return new Result(true,StatusCode.OK,"查询成功",topList);
    }

    /**
     * 查询有效标签列表
     * @return   标签列表
     */
    @RequestMapping(method = RequestMethod.GET,value = "list")
    public Result findList() {
        List<Label> list = labelService.findList();
        return new Result(true,StatusCode.OK,"查询成功",list);
    }


}
