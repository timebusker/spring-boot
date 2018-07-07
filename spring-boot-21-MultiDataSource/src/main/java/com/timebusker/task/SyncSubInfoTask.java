package com.timebusker.task;

import com.alibaba.fastjson.JSON;
import com.timebusker.entity.km.SubInfoPo;
import com.timebusker.service.SyncSubInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

/**
 * @DESC:SyncSubInfoTask
 * @author:timebusker
 * @date:2018/7/7
 */
@Configuration
@EnableScheduling
public class SyncSubInfoTask {

    private static final Logger log = LoggerFactory.getLogger(SyncSubInfoTask.class);

    private static final Map<String, String> submap = new HashMap<>();

    @Autowired
    private SyncSubInfoService syncSubInfoService;

    @Scheduled(cron = "0/20 * * * * *")
    public void task1() {
        log.info("开始同步数据！");
        // 查询需导出数据到HIVE的列表
        List<String> list = syncSubInfoService.getSubExportToHive();
        log.info("需要同步专题有：" + JSON.toJSONString(list));
        for (String subid : list) {
            // 查询当前成功批次
            SubInfoPo subkm = syncSubInfoService.getKmSubInfo(subid);
            if (null != submap.get(subkm.getSubId()) && subkm.getLastSuccessBatch().equals(submap.get(subkm.getSubId()))) {
                continue;
            } else {
                if (null != submap.get(subkm.getSubId())) {
                    submap.replace(subkm.getSubId(), subkm.getLastSuccessBatch());
                } else {
                    submap.put(subkm.getSubId(), subkm.getLastSuccessBatch());
                }
            }
            int task = syncSubInfoService.getLastSubExpTask(subkm.getSubId(), subkm.getLastSuccessBatch());
            int exp = syncSubInfoService.getSubExportToHiveCnt(subkm.getSubId());
            // 全部导出成功
            if (task == exp) {
                log.info("当前同专题：" + subkm.getSubName() + "\t" + subkm.getSubId() + "\t" + subkm.getLastSuccessBatch());
                com.timebusker.entity.qj.SubInfoPo sub = new com.timebusker.entity.qj.SubInfoPo();
                sub.setDataSource(subkm.getDataSource());
                sub.setExecStatus(subkm.getExecStatus());
                sub.setIsRecoverExe(subkm.getIsRecoverExe());
                sub.setLastSuccessBatch(subkm.getLastSuccessBatch());
                sub.setLastSuccessDate(subkm.getLastSuccessDate());
                sub.setLastSuccessRows(subkm.getLastSuccessRows());
                sub.setMemo(subkm.getMemo());
                sub.setRefSubId(subkm.getRefSubId());
                sub.setRefSubName(subkm.getRefSubName());
                sub.setRepeatInterval(subkm.getRepeatInterval());
                sub.setStatus(subkm.getStatus());
                sub.setSubAreaname(subkm.getSubAreaname());
                sub.setSubDetailTable(subkm.getSubDetailTable());
                sub.setSubEnable(0);
                sub.setSubExeClass(subkm.getSubExeClass());
                sub.setSubExecTime(subkm.getSubExecTime());
                sub.setSubId(subkm.getSubId());
                sub.setSubLevel(subkm.getSubLevel());
                sub.setSubName(subkm.getSubName());
                sub.setSubResultTable(subkm.getSubResultTable());
                sub.setSubType(subkm.getSubType());
                sub.setZtType(subkm.getZtType());
                syncSubInfoService.saveOrUpdateSubInfo(sub);
                log.info("同步成功：" + subkm.getSubName() + "\t" + subkm.getSubId() + "\t" + subkm.getLastSuccessBatch());
            }
        }
    }

//    public static void main(String[] args) {
//        Map<String, String> smap = new HashMap<>();
//        smap.put("1", "1");
//        smap.put("2", "2");
//        System.out.println(smap.get("3"));
//        smap.replace("1", "a");
//        smap.put("11", "11");
//        System.out.println(JSON.toJSONString(smap));
//    }
}
