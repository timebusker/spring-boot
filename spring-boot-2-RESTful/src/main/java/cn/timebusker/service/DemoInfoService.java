package cn.timebusker.service;

import java.util.List;

import cn.timebusker.entity.DemoInfo;

/**
 * 
 * @author timebusker
 * 
 * 2017年4月7日
 * 
 * DemoInfoService 接口类
 */
public interface DemoInfoService {

	List<DemoInfo> insertDemoInfo(DemoInfo demo);
	
	DemoInfo findDemoInfo(String id);
	
	List<DemoInfo> updateDemoInfo(DemoInfo demo);
	
	List<DemoInfo> deleteDemoInfo(DemoInfo demo);
	
	List<DemoInfo> findAll();
}
