package com.timebusker.entity.qj;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TB_SUB_INFO")
public class SubInfoPo implements Serializable{

    @Id
    @Column(name = "SUB_ID", nullable = false, length = 50)
    private String subId;

    @Column(name = "REF_SUB_ID", nullable = false, length = 50)
    private String refSubId;

    @Column(name = "SUB_NAME", nullable = true, length = 50)
    private String subName;

    @Column(name = "REF_SUB_NAME", nullable = true, length = 50)
    private String refSubName;

    @Column(name = "SUB_ENABLE", nullable = false)
    private int subEnable;

    @Column(name = "SUB_EXEC_CLASS", nullable = false, length = 100)
    private String subExeClass;

    @Column(name = "SUB_RESULT_TABLE", nullable = true, length = 100)
    private String subResultTable;

    @Column(name = "SUB_DETAIL_TABLE", nullable = true, length = 100)
    private String subDetailTable;

    @Column(name = "SUB_EXEC_TIME", nullable = true, length = 50)
    private String subExecTime;

    @Column(name = "REPEAT_INTERVAL", nullable = true)
    private int repeatInterval;

    @Column(name = "LAST_SUCCESS_BATCH", nullable = true, length = 50)
    private String lastSuccessBatch;

    @Column(name = "LAST_SUCCESS_DATE", nullable = true)
    private Date lastSuccessDate;

    @Column(name = "LAST_SUCCESS_ROWS", nullable = true)
    private long lastSuccessRows;

    @Column(name = "EXEC_STATUS", nullable = true, length = 50)
    private String execStatus;

    @Column(name = "MEMO", nullable = true, length = 500)
    private String memo;

    @Column(name = "IS_RECOVER_EXE", nullable = true)
    private int isRecoverExe;

    @Transient
    private String status;

    @Column(name = "SUB_TYPE", nullable = true, length = 50)
    private String subType;

    @Column(name = "SUB_AREANAME", nullable = true, length = 50)
    private String subAreaname;

    @Column(name = "SUB_LEVEL", nullable = true)
    private int subLevel;

    @Column(name = "DATA_SOURCE", nullable = true, length = 50)
    private String dataSource;

    @Column(name = "ZT_TYPE", nullable = true, length = 50)
    private String ztType;

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getSubAreaname() {
        return subAreaname;
    }

    public void setSubAreaname(String subAreaname) {
        this.subAreaname = subAreaname;
    }

    public int getSubLevel() {
        return subLevel;
    }

    public void setSubLevel(int subLevel) {
        this.subLevel = subLevel;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getRefSubId() {
        return refSubId;
    }

    public void setRefSubId(String refSubId) {
        this.refSubId = refSubId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getRefSubName() {
        return refSubName;
    }

    public void setRefSubName(String refSubName) {
        this.refSubName = refSubName;
    }

    public int getSubEnable() {
        return subEnable;
    }

    public void setSubEnable(int subEnable) {
        this.subEnable = subEnable;
    }

    public String getSubExeClass() {
        return subExeClass;
    }

    public void setSubExeClass(String subExeClass) {
        this.subExeClass = subExeClass;
    }

    public String getSubResultTable() {
        return subResultTable;
    }

    public void setSubResultTable(String subResultTable) {
        this.subResultTable = subResultTable;
    }

    public String getSubDetailTable() {
        return subDetailTable;
    }

    public void setSubDetailTable(String subDetailTable) {
        this.subDetailTable = subDetailTable;
    }

    public String getSubExecTime() {
        return subExecTime;
    }

    public void setSubExecTime(String subExecTime) {
        this.subExecTime = subExecTime;
    }

    public int getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(int repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public String getLastSuccessBatch() {
        return lastSuccessBatch;
    }

    public void setLastSuccessBatch(String lastSuccessBatch) {
        this.lastSuccessBatch = lastSuccessBatch;
    }

    public Date getLastSuccessDate() {
        return lastSuccessDate;
    }

    public void setLastSuccessDate(Date lastSuccessDate) {
        this.lastSuccessDate = lastSuccessDate;
    }

    public long getLastSuccessRows() {
        return lastSuccessRows;
    }

    public void setLastSuccessRows(long lastSuccessRows) {
        this.lastSuccessRows = lastSuccessRows;
    }

    public String getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(String execStatus) {
        this.execStatus = execStatus;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getIsRecoverExe() {
        return isRecoverExe;
    }

    public void setIsRecoverExe(int isRecoverExe) {
        this.isRecoverExe = isRecoverExe;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getZtType() {
        return ztType;
    }

    public void setZtType(String yskType) {
        this.ztType = yskType;
    }

    @Override
    public String toString() {
        return "SubInfoPo [subId=" + subId + ", refSubId=" + refSubId + ", subName=" + subName + ", refSubName=" + refSubName + ", subEnable=" + subEnable
                + ", subExeClass=" + subExeClass + ", subResultTable=" + subResultTable + ", subDetailTable=" + subDetailTable + ", subExecTime=" + subExecTime
                + ", repeatInterval=" + repeatInterval + ", lastSuccessBatch=" + lastSuccessBatch + ", lastSuccessDate=" + lastSuccessDate
                + ", lastSuccessRows=" + lastSuccessRows + ", execStatus=" + execStatus + ", memo=" + memo + ", status=" + status + "]";
    }

    public SubInfoPo() {

    }
}
