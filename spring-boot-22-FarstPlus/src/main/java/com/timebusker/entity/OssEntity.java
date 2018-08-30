package com.timebusker.entity;

import java.util.Date;

/**
 * @DESC:OssEntity:OSS对象存储文件实体
 * @author:timebusker
 * @date:2018/8/30
 */
public class OssEntity {

    private String id;
    //URL地址
    private String url;
    //创建时间
    private Date createDate;

    /**
     * 设置：
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public String getId() {
        return id;
    }

    /**
     * 设置：URL地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：URL地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }
}
