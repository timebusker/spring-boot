package com.timebusker.entity.km;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * TbSubExportT entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_SUB_EXPORT")
public class TbSubExport implements Serializable {


	private static final long serialVersionUID = -6410050652407144204L;
	private String id;
	private String subId;
	private String onlineDbType;
	private Integer exportEnable;
	private Integer combileWay;
	private String hiveTable;
	private String exportTable;
	private String exportPath;
	private String hiveCols;
	private String hiveWhere;
	private String mppDiscol;
	private Integer mppExpmod;
	private String orclOption;
	private Integer orclDirect;
	private Integer orclSqlldrNums;
	private Integer icpMapNum;
	private String  partitionCol;
	private Integer icpShardNum;
	private String  icpIndexs;
	private Integer icpExportOrc;
	private Integer isDirect;
	private String isBigTable;


	@Id
	@Column(name = "ID", nullable = false, length = 36)
	public String getId() {
		return id;
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

	@Column(name = "ONLINE_DB_TYPE", length = 30)
	public String getOnlineDbType() {
		return this.onlineDbType;
	}

	public void setOnlineDbType(String onlineDbType) {
		this.onlineDbType = onlineDbType;
	}

	@Column(name = "EXPORT_ENABLE")
	public Integer getExportEnable() {
		return this.exportEnable;
	}

	public void setExportEnable(Integer exportEnable) {
		this.exportEnable = exportEnable;
	}

	@Column(name = "COMBILE_WAY")
	public Integer getCombileWay() {
		return combileWay;
	}

	public void setCombileWay(Integer combileWay) {
		this.combileWay = combileWay;
	}

	@Column(name = "HIVE_TABLE", length = 30)
	public String getHiveTable() {
		return this.hiveTable;
	}

	public void setHiveTable(String hiveTable) {
		this.hiveTable = hiveTable;
	}

	@Column(name = "EXPORT_TABLE", length = 30)
	public String getExportTable() {
		return this.exportTable;
	}

	public void setExportTable(String exportTable) {
		this.exportTable = exportTable;
	}

	@Column(name = "EXPORT_PATH", length = 50)
	public String getExportPath() {
		return this.exportPath;
	}

	public void setExportPath(String exportPath) {
		this.exportPath = exportPath;
	}

	@Column(name = "HIVE_COLS", length = 300)
	public String getHiveCols() {
		return this.hiveCols;
	}

	public void setHiveCols(String hiveCols) {
		this.hiveCols = hiveCols;
	}

	@Column(name = "HIVE_WHERE", length = 300)
	public String getHiveWhere() {
		return this.hiveWhere;
	}

	public void setHiveWhere(String hiveWhere) {
		this.hiveWhere = hiveWhere;
	}

	@Column(name = "MPP_DISCOL", length = 100)
	public String getMppDiscol() {
		return this.mppDiscol;
	}

	public void setMppDiscol(String mppDiscol) {
		this.mppDiscol = mppDiscol;
	}

	@Column(name = "MPP_EXPMOD")
	public Integer getMppExpmod() {
		return this.mppExpmod;
	}

	public void setMppExpmod(Integer mppExpmod) {
		this.mppExpmod = mppExpmod;
	}

	@Column(name = "ORCL_OPTION", length = 100)
	public String getOrclOption() {
		return this.orclOption;
	}

	public void setOrclOption(String orclOption) {
		this.orclOption = orclOption;
	}

	@Column(name = "ORCL_DIRECT")
	public Integer getOrclDirect() {
		return this.orclDirect;
	}

	public void setOrclDirect(Integer orclDirect) {
		this.orclDirect = orclDirect;
	}

	@Column(name = "ORCL_SQLLDR_NUMS")
	public Integer getOrclSqlldrNums() {
		return this.orclSqlldrNums;
	}

	public void setOrclSqlldrNums(Integer orclSqlldrNums) {
		this.orclSqlldrNums = orclSqlldrNums;
	}


	@Column(name = "ICP_MAP_NUM")
	public Integer getIcpMapNum() {
		return this.icpMapNum;
	}

	public void setIcpMapNum(Integer icpMapNum) {
		this.icpMapNum = icpMapNum;
	}

	@Column(name = "PARTITION_COL", length = 20)
	public String getPartitionCol() {
		return partitionCol;
	}

	public void setPartitionCol(String partitionCol) {
		this.partitionCol = partitionCol;
	}


	@Column(name = "ICP_SHARD_NUM")
	public Integer getIcpShardNum() {
		return icpShardNum;
	}

	public void setIcpShardNum(Integer icpShardNum) {
		this.icpShardNum = icpShardNum;
	}

	@Column(name = "ICP_INDEXS", length = 100)
	public String getIcpIndexs() {
		return icpIndexs;
	}

	public void setIcpIndexs(String icpIndexs) {
		this.icpIndexs = icpIndexs;
	}


	@Column(name = "IS_DIRECT", precision = 0)
	public Integer getIsDirect() {
		return isDirect;
	}

	public void setIsDirect(Integer isDirect) {
		this.isDirect = isDirect;

	}

	@Column(name = "IS_BIG_TABLE", precision = 0)
	public String getIsBigTable() {
		return isBigTable;
	}

	public void setIsBigTable(String isBigTable) {
		this.isBigTable = isBigTable;
	}


	@Column(name = "ICP_EXPORT_ORC")
	public Integer getIcpExportOrc() {
		return icpExportOrc;
	}

	public void setIcpExportOrc(Integer icpExportOrc) {
		this.icpExportOrc = icpExportOrc;
	}
}