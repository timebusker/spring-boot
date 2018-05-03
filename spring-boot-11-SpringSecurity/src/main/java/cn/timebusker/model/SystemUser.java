package cn.timebusker.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * @DESC:${DESCRIPTION}
 * @author: timebusker
 * @date:2018/5/3
 */
public class SystemUser implements Serializable {
    private static final long serialVersionUID = 7094004598646216788L;
    private Integer userId;
    private String userName;
    private String userpPwd;
    private String telPhone;
    private Date updateDate;
    private Integer isAdmin;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserpPwd() {
        return userpPwd;
    }

    public void setUserpPwd(String userpPwd) {
        this.userpPwd = userpPwd;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }
}
