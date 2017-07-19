package cn.timebusker.repository.one2many;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.timebusker.model.one2many.position;

public interface positionRepository extends JpaRepository<position, Long> {

}
