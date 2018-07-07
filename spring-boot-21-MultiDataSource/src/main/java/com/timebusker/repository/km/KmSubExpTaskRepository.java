package com.timebusker.repository.km;

import com.timebusker.entity.km.TbSubExpTask;
import com.timebusker.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @DESC:SubExpTaskRepository
 * @author:timebusker
 * @date:2018/7/7
 */
public interface KmSubExpTaskRepository extends BaseRepository<TbSubExpTask, String> {

    @Query("select count(t) from TbSubExpTask t where expStatus='OK' and subId=?1 and batchId=?2")
    int getLastSubExpTask(String subId,String batchId);

}
