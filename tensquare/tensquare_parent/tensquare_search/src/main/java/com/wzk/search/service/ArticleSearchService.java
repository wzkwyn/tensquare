package com.wzk.search.service;

import com.wzk.search.dao.ArticleSearchDao;
import com.wzk.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ArticleSearchService {
    @Autowired
    private ArticleSearchDao articleSearchDao;

    /**
     * 增加文章
     * @param article
     */
    public void save(Article article) {
        articleSearchDao.save(article);
    }

    /**
     * 搜索文章
     */
    public Page<Article> findByTitleOrContentLike(String keywords,Integer page,Integer size) {
        return articleSearchDao.findByTitleOrContentLike(keywords,keywords, PageRequest.of(page-1,size));
    }
}
