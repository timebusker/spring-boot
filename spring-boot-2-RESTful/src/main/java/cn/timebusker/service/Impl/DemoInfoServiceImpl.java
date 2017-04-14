package cn.timebusker.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.timebusker.dao.DemoInfoDAO;
import cn.timebusker.entity.DemoInfo;
import cn.timebusker.service.DemoInfoService;

@Service
public class DemoInfoServiceImpl implements DemoInfoService {

	@Resource
	DemoInfoDAO dao;

	@Override
	public List<DemoInfo> insertDemoInfo(DemoInfo demo) {
		// TODO Auto-generated method stub
		return dao.insertDemoInfo(demo);
	}

	@Override
	public DemoInfo findDemoInfo(String id) {
		// TODO Auto-generated method stub
		return dao.findDemoInfo(id);
	}

	@Override
	public List<DemoInfo> updateDemoInfo(DemoInfo demo) {
		// TODO Auto-generated method stub
		return dao.updateDemoInfo(demo);
	}

	@Override
	public List<DemoInfo> deleteDemoInfo(DemoInfo demo) {
		// TODO Auto-generated method stub
		return dao.deleteDemoInfo(demo);
	}

	@Override
	public List<DemoInfo> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
