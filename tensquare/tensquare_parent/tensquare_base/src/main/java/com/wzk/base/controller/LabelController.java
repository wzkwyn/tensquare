package com.wzk.base.controller;

import com.wzk.base.pojo.Label;
import com.wzk.base.service.LabelService;
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

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Label> labelList = labelService.findAll();
        return new Result(true,StatusCode.OK,"查询成功",labelList);
    }
    @RequestMapping(method = RequestMethod.GET ,value="/{id}")
    public Result findById(@PathVariable String id) {
        Label label = labelService.findById(id);
        return new Result(true,StatusCode.OK,"查询成功",label);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK,"增加成功");

    }

    @RequestMapping(method = RequestMethod.PUT,value="/{id}")
    public Result update(@RequestBody Label label,@PathVariable String id) {
        label.setId(id);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/{id}")
    public Result deleteById(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
