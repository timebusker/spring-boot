package cn.timebusker.service.user.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.timebusker.annotation.timebuskerMethod;
import cn.timebusker.service.user.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	private final static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

	@Override
	@timebuskerMethod
	public String addUserInfo(String name) {
		logger.info("新增的用户姓名是：" + name);
		return name + "U";
	}
}