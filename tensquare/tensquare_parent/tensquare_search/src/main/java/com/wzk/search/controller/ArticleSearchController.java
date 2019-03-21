package com.wzk.search.controller;

import com.wzk.entity.PageResult;
import com.wzk.entity.Result;
import com.wzk.entity.StatusCode;
import com.wzk.search.pojo.Article;
import com.wzk.search.service.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleSearchController {
    @Autowired
    private ArticleSearchService articleSearchService;

    /**
     * 增加文章
     * @param article
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Article article) {
        articleSearchService.save(article);
        return new Result(true, StatusCode.OK,"增加成功");
    }

    /**
     * 根据标题和内容模糊搜索
     * @param keywords
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/search/{keywords}/{page}/{size}")
    public Result findByTitleOrContentLike(@PathVariable String keywords,@PathVariable Integer page,@PathVariable Integer size) {
        Page<Article> articlePage =  articleSearchService.findByTitleOrContentLike(keywords,page,size);
        return new Result(true, StatusCode.OK,"根据标题和内容模糊搜索成功",new PageResult<>(articlePage.getTotalElements(),articlePage.getContent()));
    }

}
