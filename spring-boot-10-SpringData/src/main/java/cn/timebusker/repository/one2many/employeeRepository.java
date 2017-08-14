package cn.timebusker.repository.one2many;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.timebusker.model.one2many.employee;
import cn.timebusker.repository.BasicRepository;

public interface employeeRepository extends BasicRepository<employee, Long> {

	@Modifying(clearAutomatically = true) 
	@Query("update employee set empName =?1 "			
			+ "where empId =?2")
	void updateName(@Param(value = "name")String name,
			@Param(value = "id")long id);
}
