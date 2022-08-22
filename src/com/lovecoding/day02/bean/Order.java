package com.lovecoding.day02.bean;

import java.io.Serializable;

public class Order implements Serializable {
    private Long oid;
    private String oname;
    private String createTime;

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", oname='" + oname + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
