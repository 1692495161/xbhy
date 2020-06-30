package com.cjj.entity;

/**
 * @author cjj
 * @date 2020/6/22
 * @description
 */
public class Muen {
    private Integer id;
    private Integer pId;
    private Integer type;
    private String name;
    private String url;
    private Integer orderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Muen{" +
                "id=" + id +
                ", pId=" + pId +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
