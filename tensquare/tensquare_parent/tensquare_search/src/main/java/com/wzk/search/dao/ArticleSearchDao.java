package com.wzk.search.dao;

import com.wzk.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {
    Page<Article> findByTitleOrContentLike(String keywords1,String keywords2, Pageable pageable);
}
