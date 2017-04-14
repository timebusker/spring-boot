package cn.timebusker.conf;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author timebusker
 * 
 * 2017年4月7日
 * 
 * 自定义属性与加载:只需要使用@Component注解将类注册到容器内就可以方便使用
 */
@Component
public class DefinitionConfig implements Serializable{

	private static final long serialVersionUID = 7063410079055294317L;

	@Value("${cn.timebusker.name}")
	private String name;

	@Value("${cn.timebusker.title}")
	private String title;

	@Value("${cn.timebusker.desc}")
	private String desc;

	@Value("${cn.timebusker.value}")
	private String value;

	@Value("${cn.timebusker.number}")
	private Integer number;

	@Value("${cn.timebusker.bignumber}")
	private Long bignumber;

	@Value("${cn.timebusker.random1}")
	private Integer random1;

	@Value("${cn.timebusker.random2}")
	private Integer random2;

	@Override
	public String toString() {
		return "DefinitionConfig [name=" + name + ", title=" + title + ", desc=" + desc + ", value=" + value + ", number=" + number + ", bignumber=" + bignumber + ", random1=" + random1 + ", random2=" + random2 + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Long getBignumber() {
		return bignumber;
	}

	public void setBignumber(Long bignumber) {
		this.bignumber = bignumber;
	}

	public Integer getRandom1() {
		return random1;
	}

	public void setRandom1(Integer random1) {
		this.random1 = random1;
	}

	public Integer getRandom2() {
		return random2;
	}

	public void setRandom2(Integer random2) {
		this.random2 = random2;
	}

}
