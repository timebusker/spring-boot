package cn.timebusker.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LogsController {
	
	@RequestMapping(value = { "/**", "/" }, method = RequestMethod.GET)
	public Long findAll() {
		
		return System.currentTimeMillis();
	}
}
