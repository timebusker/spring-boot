package cn.timebusker.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.timebusker.service.RedisService;

@RestController
public class indexController {

	private final static Logger logger = LoggerFactory.getLogger(indexController.class);
	/**
	 * 线程安全控制
	 */
	private static Integer key = 0;

	private static List<String> keys = Collections.synchronizedList(new ArrayList<String>());

	@Autowired
	RedisService redisService;

	@RequestMapping(value = { "/*", "/" }, method = RequestMethod.GET)
	public Object index() throws Exception {
		logger.info("this is Redis Sample!");
		return "this is Redis Sample!";
	}

	@RequestMapping(value = { "/addstr" }, method = RequestMethod.GET)
	public Object addString() throws Exception {
		String k = getKey();
		logger.info("\n\n\t" + k);
		return redisService.addString(k);
	}

	@RequestMapping(value = { "/addliststr" }, method = RequestMethod.GET)
	public Object addListString() throws Exception {
		String k = getKey();
		logger.info("\n\n\t" + k);
		return redisService.addListString(k);
	}

	@RequestMapping(value = { "/getstr/{key}" }, method = RequestMethod.GET)
	public Object getString(@PathVariable String key) throws Exception {
		return redisService.getString(key);
	}

	@RequestMapping(value = { "/getstr" }, method = RequestMethod.GET)
	public Object getString() throws Exception {
		return redisService.getString(keys);
	}

	@RequestMapping(value = { "/updatestr/{key}" }, method = RequestMethod.GET)
	public Object updateString(@PathVariable String key) throws Exception {
		return redisService.updateString(getKey(), "我爱我家");
	}

	@RequestMapping(value = { "/deletetr/{key}" }, method = RequestMethod.GET)
	public Object deleteString(@PathVariable String key) throws Exception {
		return redisService.deleteString("1");
	}

	@RequestMapping(value = { "/addobj" }, method = RequestMethod.GET)
	public Object addObject() throws Exception {
		return redisService.addObject(getKey());
	}

	public synchronized String getKey() {
		String k = (++key) + "";
		keys.add(k);
		return k;
	}
}
