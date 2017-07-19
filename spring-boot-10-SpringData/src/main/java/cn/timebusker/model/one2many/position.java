package cn.timebusker.model.one2many;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 职级表（一个员工可对应着多个职级）
 * 
 * @author Administrator
 */
@Entity
@Table(name = "t_position")
public class position {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long posId;

	@Column(nullable = false, length = 32)
	private String posName;
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="position")
	private List<employee> employee;
}
