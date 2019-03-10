package com.wzk.entity;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {
    private Long tatal;  //总数据
    private List<T> rows; //页面数据

    public PageResult() {
    }

    public PageResult(Long tatal, List<T> rows) {
        this.tatal = tatal;
        this.rows = rows;
    }

    public Long getTatal() {
        return tatal;
    }

    public void setTatal(Long tatal) {
        this.tatal = tatal;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
