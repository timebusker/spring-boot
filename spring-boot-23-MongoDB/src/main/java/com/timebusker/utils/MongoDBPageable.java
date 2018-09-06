package com.timebusker.utils;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * @DESC:MongoDBPageable:分页实现类
 * @author:timebusker
 * @date:2018/9/7
 */
public class MongoDBPageable implements Pageable, Serializable {

    private Integer pagenum = 1;
    private Integer pagesize = 10;
    private Sort sort = null;

    public MongoDBPageable() {
    }

    public MongoDBPageable(Integer pagenum) {
        this.pagenum = pagenum;
    }

    public MongoDBPageable(Integer pagenum, Integer pagesize) {
        this.pagenum = pagenum;
        this.pagesize = pagesize;
    }

    public MongoDBPageable(Integer pagenum, Integer pagesize, Sort sort) {
        this.pagenum = pagenum;
        this.pagesize = pagesize;
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return getPagesize();
    }

    @Override
    public int getOffset() {
        return (getPagenum() - 1) * getPageSize();
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    public Integer getPagenum() {
        return pagenum;
    }

    public void setPagenum(Integer pagenum) {
        this.pagenum = pagenum;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
