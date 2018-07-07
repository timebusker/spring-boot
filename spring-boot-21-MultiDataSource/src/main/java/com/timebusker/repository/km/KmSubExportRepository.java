package com.timebusker.repository.km;

import com.timebusker.entity.km.TbSubExport;
import com.timebusker.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @DESC:SubExpTaskRepository
 * @author:timebusker
 * @date:2018/7/7
 */
public interface KmSubExportRepository extends BaseRepository<TbSubExport, String> {

    @Query("select subId from TbSubExport t where onlineDbType='HIVE-HIVE' and exportEnable=1 group by subId")
    List<String> getSubExportToHive();

    @Query("select count(t) from TbSubExport t where onlineDbType='HIVE-HIVE' and exportEnable=1 and subId=?1")
    int getSubExportToHiveCnt(String subId);
}
