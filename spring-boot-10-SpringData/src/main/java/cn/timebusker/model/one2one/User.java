package cn.timebusker.model.one2one;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
/**
 * 指明该类是一个实体Bean，可以通过jpa与数据库表建立映射关系
 * 每个成员代表数据库字段名称
 * 实体Bean的每个实例代表数据表中的一行数据
 */
@Entity
/**
 * 为被@Entity注释的类所要映射带数据库表
 * 其中@Table.name()用来指定映射表的表名。如果缺省@Table注释，系统默认采用类名作为映射表的表名
 */
@Table(name = "t_user")
public class User implements Serializable {

	private static final long serialVersionUID = 905654767215634L;

	public User(String userName) {
		super();
		this.userName = userName;
	}
	
	/**
	 * @Id:指定表的主键
	 * @GeneratedValue:定义了标识字段生成方式
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	/**
	 * @Column:定义了将成员属性映射到关系表中的字段名称
	 */
	@Column(name = "USER_NAME", length = 100)
	private String userName;

	@OneToOne(cascade = CascadeType.PERSIST)
	@PrimaryKeyJoinColumn
	private Address address;

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}