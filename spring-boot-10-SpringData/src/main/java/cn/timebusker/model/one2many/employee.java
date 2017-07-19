package cn.timebusker.model.one2many;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 员工表
 * 
 * @author Administrator
 */
@Entity
@Table(name = "t_employee")
public class employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empId;

	@Column(nullable = false, length = 32)
	private String empName;

	//mappedBy = "employee" 指定了，这个实体是被关系维护端的那个属性所维护
	//当我们设置了，mappedBy后，使关系成为双向的时候
	//在本例中，detailInfo实体是被 employee实体中的外键“detailInfo”所维护。
	@OneToOne(cascade = CascadeType.ALL, optional = false, mappedBy = "employee")
	private detailInfo detailInfo;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="depId")
	private department department;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="t_em_pos", inverseJoinColumns =  @JoinColumn (name =  "em_id" ),
			joinColumns =  @JoinColumn (name =  "pos_id" ))
	
//	@JoinTable(name="em_pos",indexes={@Index(columnList = "em_id"),@Index(columnList = "pos_id")},
//			inverseJoinColumns =  @JoinColumn (name =  "em_id" ),
//			joinColumns =  @JoinColumn (name =  "pos_id" ))	
	private List<position> position;
	
	
}
