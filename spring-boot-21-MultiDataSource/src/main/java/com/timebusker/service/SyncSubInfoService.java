package com.timebusker.service;

import com.timebusker.entity.km.SubInfoPo;
import com.timebusker.repository.km.*;
import com.timebusker.repository.qj.QjSubInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @DESC:SyncSubInfoService
 * @author:timebusker
 * @date:2018/7/7
 */
@Service
public class SyncSubInfoService {

    private final static Long AFTER_TIME = 1800000L;

    @Autowired
    private QjSubInfoRepository qjSubInfoRepository;

    @Autowired
    private KmSubExportRepository kmSubExportRepository;

    @Autowired
    private KmSubExpTaskRepository kmSubExpTaskRepository;

    @Autowired
    private KmSubInfoRepository kmSubInfoRepository;

    public SubInfoPo getKmSubInfo(String subId) {
        SubInfoPo sub = kmSubInfoRepository.getKmSubInfo(subId);
        return sub;
    }

    public int getLastSubExpTask(String subId, String batchId) {
        int cnt = kmSubExpTaskRepository.getLastSubExpTask(subId, batchId);
        return cnt;
    }

    public int getSubExportToHiveCnt(String subId) {
        int cnt = kmSubExportRepository.getSubExportToHiveCnt(subId);
        return cnt;
    }

    public List<String> getSubExportToHive() {
        List<String> list = kmSubExportRepository.getSubExportToHive();
        return list;
    }

    public void saveOrUpdateSubInfo(com.timebusker.entity.qj.SubInfoPo sub) {
        qjSubInfoRepository.save(sub);
    }

    private static Date getLastTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long lt = System.currentTimeMillis() - AFTER_TIME;
        Date date = new Date(lt);
        System.out.println(format.format(date));
        System.out.println(date);
        return date;
    }
}
