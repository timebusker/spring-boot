package cn.timebusker.repository.one2many;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.timebusker.model.one2many.department;

public interface departmentRepository extends JpaRepository<department, Long> {

}
