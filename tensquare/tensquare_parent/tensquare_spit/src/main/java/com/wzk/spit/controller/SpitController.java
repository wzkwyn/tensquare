package com.wzk.spit.controller;

import com.wzk.entity.Result;
import com.wzk.entity.StatusCode;
import com.wzk.spit.pojo.Spit;
import com.wzk.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spit")
@CrossOrigin
public class SpitController {
    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询全部吐槽
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Spit> spitList = spitService.findAll();
        return new Result(true, StatusCode.OK,"查询全部吐槽成功",spitList);
    }

    /**
     * 根据id查询吐槽
     * @param spitId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/{spitId}")
    public Result findById(@PathVariable String spitId) {
        Spit spit = spitService.findById(spitId);
        return new Result(true, StatusCode.OK,"根据id查询吐槽成功",spit);
    }

    /**
     * 根据id删除吐槽
     * @param spitId
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE,value = "/{spitId}")
    public Result deleteById(@PathVariable String spitId) {
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK,"根据id删除吐槽成功");
    }

    /**
     * 添加吐槽
     * @param spit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result deleteById(@RequestBody Spit spit) {
        spitService.add(spit);
        return new Result(true, StatusCode.OK,"添加吐槽成功");
    }

    /**
     * 更新吐槽
     * @param spit
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT,value = "/{spitId}")
    public Result update(@RequestBody Spit spit,@PathVariable String spitId) {
        spit.set_id(spitId);
        spitService.add(spit);
        return new Result(true, StatusCode.OK,"修改吐槽成功");
    }

    /**
     * 根据上级ID查询吐槽列表
     * @param parentId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/comment/{parentId}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentId,@PathVariable Integer page,@PathVariable Integer size) {
        List<Spit> spitList = spitService.findByParentId(parentId,page,size);
        return new Result(true,StatusCode.OK,"根据parentId查询吐槽列表成功",spitList);
    }

    /**
     * 吐槽点赞
     * @param spitId
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT,value = "/thumbup/{spitId}")
    public Result thumbup(@PathVariable String spitId) {
        String userId = "1994";
        Object o = redisTemplate.opsForValue().get("thumbup_" + userId + "_" + spitId);
        //判断用户是否点过赞了
        if(o != null) {
            return new Result(false,StatusCode.ERROR,"您已经点过赞了，请勿重复点赞");
        }
        spitService.thumbup(spitId);
        //把点赞信息放到缓存中
        redisTemplate.opsForValue().set("thumbup_" + userId + "_" + spitId,"1");
        return new Result(true,StatusCode.OK,"吐槽点赞成功");
    }
}
