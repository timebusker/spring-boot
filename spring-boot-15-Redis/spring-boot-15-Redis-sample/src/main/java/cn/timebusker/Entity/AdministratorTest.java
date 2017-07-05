package cn.timebusker.Entity;

import java.io.Serializable;

/**
 * 实现序列化接口，让类可以序列化 Serializable空接口，实现后可让类参与序列化和反序列化
 * 
 * @author Administrator
 *
 */
public class AdministratorTest implements Serializable {

	private static final long serialVersionUID = 6811769805354960545L;

	private String username;
	private String age;
	private String total;
	private String firstname;

	public AdministratorTest(String username, String age, String total, String firstname) {
		super();
		this.username = username;
		this.age = age;
		this.total = total;
		this.firstname = firstname;
	}

	public AdministratorTest() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String toString() {
		return "AdministratorTest [username:" + username + ",age:" + age + ",total:" + total + ",firstname:" + firstname + "]";
	}
}
