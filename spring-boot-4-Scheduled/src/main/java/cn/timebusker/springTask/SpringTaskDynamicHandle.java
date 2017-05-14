package cn.timebusker.springTask;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 动态添加修改删除定时任务
 */
@RestController
public class SpringTaskDynamicHandle {

	@Autowired
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;

	private ScheduledFuture<?> future;
	
	@Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
       return new ThreadPoolTaskScheduler();
    }

	
	@RequestMapping("/start")
    public String startCron() {
       future = threadPoolTaskScheduler.schedule(new SpringTaskRunnable(), new CronTrigger("0/5 * * * * *"));
       return "0/5 * * * * *";
    }
 
    @RequestMapping("/stop")
    public String stopCron() {
       if (future != null) {
           future.cancel(true);
       }
       return "stop";
    }
 
    @RequestMapping("/change")
    public String startCron10() {
       stopCron();
       future = threadPoolTaskScheduler.schedule(new SpringTaskRunnable(), new CronTrigger("*/1 * * * * *"));
       return "*/1 * * * * *";
    }
	
	private class SpringTaskRunnable implements Runnable {
		@Override
		public void run() {
			System.out.println("SpringTaskDynamicHandle.SpringTaskRunnable.run()的具体业务逻辑代码：---- " + new Date());
		}
	}
}
