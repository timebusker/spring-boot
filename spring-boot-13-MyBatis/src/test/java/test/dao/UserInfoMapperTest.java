package test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.timebusker.App;
import cn.timebusker.dao.UserInfoMapper;
import cn.timebusker.entity.UserInfo;

import com.alibaba.fastjson.JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class UserInfoMapperTest {

	@Autowired
	UserInfoMapper dao;

	@Test
	public void findAll() throws Exception {
		List<UserInfo> lui = dao.findAll();
		System.out.println("\n\n\n\n\n");
		System.out.println("输出结果：" + JSON.toJSONString(lui.get(0)));
		System.out.println("输出结果：" + JSON.toJSONString(lui));
		System.out.println("\n\n\n\n\n");
	}

	@Test
	public void queryById() throws Exception {
		List<UserInfo> lui = dao.queryById();
		System.out.println("\n\n\n\n\n");
		System.out.println("输出结果：" + JSON.toJSONString(lui.get(0)));
		System.out.println("输出结果：" + JSON.toJSONString(lui));
		System.out.println("\n\n\n\n\n");
	}

	@Test
	public void findByName() throws Exception {
		List<UserInfo> lui = dao.findByName("test2");
		System.out.println("\n\n\n\n\n");
		System.out.println("输出结果：" + lui.get(0).getPassword());
		System.out.println("\n\n\n\n\n");
	}

	@Test
	@Rollback
	public void insert() throws Exception {
		UserInfo ui = new UserInfo();
		ui.setId(1);
		ui.setUsername("余姣姣");
		System.out.println("\n\n\n\n\n");
		System.out.println("输出结果：" + dao.insert(ui));
		System.out.println("\n\n\n\n\n");
	}

	@Test
	public void insertByMap() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", 13);
		map.put("username", "姣姣");
		map.put("password", "password");
		System.out.println("\n\n\n\n\n");
		System.out.println("输出结果：" + dao.insertByMap(map));
		System.out.println("\n\n\n\n\n");
	}

	@Test
	public void update() throws Exception {
		List<UserInfo> lui = dao.findById(2);
		lui.get(0).setPassword("wewrewz");
		dao.update(lui.get(0));
		lui = dao.findById(2);
		System.out.println("\n\n\n\n\n");
		System.out.println("输出结果：" + JSON.toJSONString(lui));
		System.out.println("\n\n\n\n\n");
	}

	@Test
	public void delete() throws Exception {
		dao.delete(1);
		System.out.println("\n\n\n\n\n");
		System.out.println("输出结果：" + JSON.toJSONString(dao.findById(1)));
		System.out.println("\n\n\n\n\n");
	}
}