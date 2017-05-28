package cn.timebusker.service;

import java.util.List;

import cn.timebusker.entity.User;

public interface UserService {

	/**
	 * 使用ID查询用户
	 * 
	 * @param id
	 * @return
	 */
	public User findUserById(long id);

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	public List<User> findAllUser();

	
	/**
	 * 更新或新增用户信息
	 * 
	 * @param user
	 */
	public void saveAndFlush(User user);

	/**
	 * 通过ID删除一个用户
	 * 
	 * @param id
	 */
	public void deleteUserById(long id);
}
