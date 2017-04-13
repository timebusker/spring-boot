package cn.timebusker.entity;

import java.io.Serializable;

/**
 * @author timebusker
 * 
 * 2017年4月7日
 * 
 * 示例Demo Entity
 */
public class DemoInfo implements Serializable {

	private static final long serialVersionUID = -1727537778826379384L;

	private String id;
	
	private String name;
	
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
