package com.timebusker.repository.km;

import com.timebusker.entity.km.SubInfoPo;
import com.timebusker.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @DESC:SubInfoRepository
 * @author:timebusker
 * @date:2018/7/7
 */
public interface KmSubInfoRepository extends BaseRepository<SubInfoPo, String> {

    @Query("select t from SubInfoPo t where subEnable=1 and execStatus='OK' and subId=?1")
    SubInfoPo getKmSubInfo(String subId);
}
