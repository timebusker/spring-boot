package cn.timebusker.model.one2one;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 设置符合逐渐 可以同时使用两个@Id进行注释到成员上
 */
@Entity
@Table(name = "t_test")
public class test implements Serializable {

	private static final long serialVersionUID = 5260045025079981216L;

	@Id
	private long teid;

	@Id
	private long stid;
}
