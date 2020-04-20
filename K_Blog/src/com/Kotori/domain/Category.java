package com.Kotori.domain;

public class Category {
    private Integer cid;
    private String cname;
    private String parentid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    @Override
    public String toString() {
        return "catagory{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", parentid='" + parentid + '\'' +
                '}';
    }
}
