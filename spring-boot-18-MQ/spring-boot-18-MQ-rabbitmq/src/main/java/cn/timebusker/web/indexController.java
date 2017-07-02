package cn.timebusker.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {

	private final static Logger logger = LoggerFactory.getLogger(indexController.class);

	@RequestMapping(value = { "/**", "/" }, method = RequestMethod.GET)
	public Long createLogs() {
		logger.info("----------------------------------INFO");
		return System.currentTimeMillis();
	}
}