package cn.timebusker.model.one2many;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 部门表
 * 
 * @author Administrator
 */
@Entity
@Table(name = "t_department")
public class department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long depId;

	@Column(nullable = false, length = 32)
	private String depName;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "comId")
	private company company;
	
	@OneToMany(mappedBy="department")
	private List<employee> employee;
}
