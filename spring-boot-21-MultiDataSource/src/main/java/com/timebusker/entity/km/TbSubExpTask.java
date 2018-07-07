package com.timebusker.entity.km;

import javax.persistence.*;
import java.io.Serializable;

/**
 * TbSubExpTask entity
 */
@Entity
@Table(name = "TB_SUB_EXP_TASK")
public class TbSubExpTask implements Serializable {

    private static final long serialVersionUID = 1117632073865779512L;
    private String id;
    private String subId;
    private String batchId;
    private String onlineDbType;
    private String expStatus;
    private String addTime;
    private String exportTime;
    private String expTable;
    private Integer isDirect;

    /**
     * default constructor
     */
    public TbSubExpTask() {
    }

    /**
     * minimal constructor
     */
    public TbSubExpTask(String id) {
        this.id = id;
    }

    // Property accessors
    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 50)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "SUB_ID", length = 50)
    public String getSubId() {
        return this.subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    @Column(name = "BATCH_ID", length = 50)
    public String getBatchId() {
        return this.batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    @Column(name = "EXP_STATUS", length = 4000)
    public String getExpStatus() {
        return this.expStatus;
    }

    public void setExpStatus(String expStatus) {
        this.expStatus = expStatus;
    }

    @Column(name = "ADD_TIME", length = 20)
    public String getAddTime() {
        return this.addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @Column(name = "EXPORT_TIME", length = 20)
    public String getExportTime() {
        return this.exportTime;
    }

    public void setExportTime(String exportTime) {
        this.exportTime = exportTime;
    }

    @Column(name = "EXP_TABLE", length = 100)
    public String getExpTable() {
        return this.expTable;
    }

    public void setExpTable(String expTable) {
        this.expTable = expTable;
    }

    @Column(name = "ONLINE_DB_TYPE", length = 30)
    public String getOnlineDbType() {
        return onlineDbType;
    }

    public void setOnlineDbType(String onlineDbType) {
        this.onlineDbType = onlineDbType;
    }

    @Column(name = "IS_DIRECT", precision = 0)
    public Integer getIsDirect() {
        return isDirect;
    }

    public void setIsDirect(Integer isDirect) {
        this.isDirect = isDirect;
    }

}