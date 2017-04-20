package cn.timebusker.web;


import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.timebusker.util.LoggingUtil;


@RestController
public class LogsController {
	
	private static final Logger logger = Logger.getLogger(LogsController.class);
	
	@RequestMapping(value = { "/**", "/" }, method = RequestMethod.GET)
	public Long createLogs() {
		logger.info("----------------------------------INFO");
		logger.debug("----------------------------------DEBUG");
		logger.error("----------------------------------ERROR");
		LoggingUtil.creatLogging();
		return System.currentTimeMillis();
	}
}
