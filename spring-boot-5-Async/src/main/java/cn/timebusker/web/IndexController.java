package cn.timebusker.web;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.timebusker.service.ArithmeticService;

@RestController
public class IndexController {

	private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private ArithmeticService arithmeticService;

	@SuppressWarnings("static-access")
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public void index() {
		long start = System.currentTimeMillis();
		try {
			logger.info("--------------------------------------------\n");
			logger.info("每个任务执行的时间是：" + arithmeticService.DoTime + "（毫秒）");

			Future<Long> task = arithmeticService.subByAsync();

			arithmeticService.subByVoid();

			long sync = arithmeticService.subBySync();

			while (true) {
				if (task.isDone()) {
					long async = task.get();
					logger.info("异步任务执行的时间是：" + async + "（毫秒）");
					// logger.info("注解任务执行的时间是： -- （毫秒）");
					logger.info("同步任务执行的时间是：" + sync + "（毫秒）");
					break;
				}
			}
			logger.info("--------------------------------------------\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("\t........请求响应时间为：" + (end - start) + "（毫秒）");
	}

	/**
	 * 自定义实现线程异步
	 */
	@RequestMapping(value = { "/mine", "/m*" }, method = RequestMethod.GET)
	public void mineAsync() {
		for (int i = 0; i < 100; i++) {
			try {
				arithmeticService.doMineAsync(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
