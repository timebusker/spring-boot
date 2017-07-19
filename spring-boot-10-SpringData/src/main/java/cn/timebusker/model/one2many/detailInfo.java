package cn.timebusker.model.one2many;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 员工详细信息表（一个员工一条详细）
 * 
 * @author Administrator
 */
@Entity
@Table(name = "t_detailInfo")
public class detailInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long detId;

	@Column(nullable = false, length = 32)
	private String detName;

	@OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "empId")
	private employee employee;

	
}
