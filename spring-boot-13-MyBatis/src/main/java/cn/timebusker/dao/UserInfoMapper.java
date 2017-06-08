package cn.timebusker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.timebusker.entity.UserInfo;

/**
 * 基于注解实现持久化操作
 *
 */
@Mapper
public interface UserInfoMapper {

	@Select("SELECT * FROM user_info WHERE username = #{name}")
	List<UserInfo> findByName(@Param("name") String name);

	@Select("SELECT * FROM user_info WHERE id = #{id}")
	List<UserInfo> findById(@Param("id") int id);

	@Insert("INSERT INTO user_info(Id,username,password,usertype,enabled,realname,email,tel) VALUES(#{id}, #{username},#{password}, #{usertype},#{enabled}, #{realname},#{email}, #{tel})")
	int insert(UserInfo ui);

	@Select("SELECT * FROM user_info WHERE 1=1 ")
	List<UserInfo> findAll();

	@Update("UPDATE user_info SET password=#{password} WHERE username=#{username}")
	void update(UserInfo ui);

	@Delete("DELETE FROM user_info WHERE id =#{id}")
	void delete(int id);
	
}