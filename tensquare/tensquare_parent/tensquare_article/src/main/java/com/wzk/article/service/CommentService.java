package com.wzk.article.service;

import com.wzk.article.dao.CommentDao;
import com.wzk.article.pojo.Comment;
import com.wzk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private IdWorker idWorker;

    /**
     * 新增评论
     * @param comment
     */
    public void add(Comment comment) {
        comment.set_id(idWorker.nextId()+"");
        commentDao.save(comment);
    }

    /**
     * 根据文章ID查询评论列表
     * @param articleid
     * @param page
     * @param size
     * @return
     */
    public List<Comment> findByArticleid(String articleid,Integer page,Integer size) {
        return commentDao.findByArticleid(articleid, PageRequest.of(page-1,size));
    }
    /**
     * 根据ID删除评论
     * @param commentId
     * @return
     */
    public void deleteById(String commentId) {
        commentDao.deleteById(commentId);
    }
}
