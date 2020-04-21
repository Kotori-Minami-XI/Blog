package com.Kotori.domain;

import java.util.List;

public class PageBean<T> {
    private Integer currentPage;
    private Integer pageSize;
    private Integer index;
    private Integer totalCount;
    private Integer totalPage;
    private List<T> list;

    public void setCurrentPage(Integer currentPage) {
        // Default currentPage
        if (null == this.currentPage){
            this.currentPage = 1;
        }
        this.currentPage = currentPage;
    }

    public void setPageSize(Integer pageSize) {
        // Default pageSize
        if (null == this.pageSize){
            this.pageSize = 5;
        }
        this.pageSize = pageSize;
    }

    public Integer getIndex() {
        return (this.currentPage - 1) * this.pageSize;
    }

    public Integer getTotalPage() {
        double ceil = Math.ceil(totalCount * 1.0 / this.pageSize);
        return (int)ceil;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", index=" + index +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}

