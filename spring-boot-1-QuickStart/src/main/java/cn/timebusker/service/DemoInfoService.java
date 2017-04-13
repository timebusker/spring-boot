package cn.timebusker.service;

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

	DemoInfo insertDemoInfo(DemoInfo demo);
	
	DemoInfo findDemoInfo(DemoInfo demo);
	
	DemoInfo updateDemoInfo(DemoInfo demo);
	
	DemoInfo deleteDemoInfo(DemoInfo demo);
}
