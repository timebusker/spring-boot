package cn.timebusker.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {
	
	
	@RequestMapping(value = { "/**", "/" }, method = RequestMethod.GET)
	public Long createLogs() {
		return System.currentTimeMillis();
	}
}
