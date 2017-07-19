package cn.timebusker.model.one2many;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 公司表
 * 
 * @author Administrator
 */
@Entity
@Table(name = "t_company")
public class company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long comId;

	@Column(nullable = false, length = 32)
	private String comName;
	
	@OneToMany(mappedBy="company")
	private List<department> department;
}
