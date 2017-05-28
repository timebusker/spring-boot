package cn.timebusker.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.timebusker.entity.User;
import cn.timebusker.service.UserService;

@RestController
@RequestMapping("/u")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService service;

	/**
	 * 使用ID查询用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findOneUser(@PathVariable long id) {
		logger.info("传入的参数 ID : " + id);
		User u = service.findUserById(id);
		return u;
	}

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<User> findAllUser() {
		logger.info("查询所有用户信息");
		List<User> list = service.findAllUser();
		return list;
	}

	/**
	 * 更新或者新增用户信息
	 * 
	 * @param user
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public User saveAndFlushUser(@ModelAttribute User user) {
		logger.info("传入的参数 user : " + JSON.toJSONString(user));
		service.saveAndFlush(user);
		return user;
	}

	/**
	 * 通过ID删除一个用户
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public User deleteUser(@PathVariable long id) {
		User user = service.findUserById(id);
		logger.info("查询到的user信息是: " + JSON.toJSONString(user));
		service.deleteUserById(id);
		return user;
	}
}
