package com.wzk.article.controller;

import com.wzk.article.pojo.Comment;
import com.wzk.article.service.CommentService;
import com.wzk.entity.Result;
import com.wzk.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
        return new Result(true, StatusCode.OK,"添加评论成功");
    }

    /**
     * 根据文章ID查询评论列表
     * @param articleid
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/{articleid}/{page}/{size}")
    public Result add(@PathVariable String articleid,@PathVariable Integer page,@PathVariable Integer size) {
        List<Comment> commentList = commentService.findByArticleid(articleid,page,size);
        return new Result(true, StatusCode.OK,"根据文章ID查询评论列表成功",commentList);
    }

    /**
     * 根据ID删除评论
     * @param commentId
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE,value = "/{commentId}")
    public Result deleteById(@PathVariable String commentId) {
       commentService.deleteById(commentId);
        return new Result(true, StatusCode.OK,"根据id删除评论成功");
    }
}
