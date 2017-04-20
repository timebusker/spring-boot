package cn.timebusker.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LogsController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogsController.class);
	
	@RequestMapping(value = { "/**", "/" }, method = RequestMethod.GET)
	public Long findAll() {
		logger.debug("this is debug:\t"+System.currentTimeMillis());
		logger.info("this is debug:\t"+System.currentTimeMillis());
		return System.currentTimeMillis();
	}
}
