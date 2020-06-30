package com.cjj.entity;

import java.util.List;

/**
 * @author cjj
 * @date 2020/6/28
 * @description
 */
public class Page<T> {
    //总页数
    private Integer pageCount;
    //总记录数
    private Integer count;
    //当前页
    private Integer pageCurrent=1;
    //每页显示的记录数
    private Integer size=5;

    private List<T> data;

    public Integer getPageCount() {
        return this.count % this.size == 0 ? this.count / this.size : this.count / this.size + 1;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(Integer pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
