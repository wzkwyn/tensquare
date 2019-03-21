package com.wzk.article.dao;

import com.wzk.article.pojo.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;



import java.util.List;

public interface CommentDao extends MongoRepository<Comment,String> {
    List<Comment> findByArticleid(String articleid, Pageable pageable);
}
