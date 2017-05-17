package cn.timebusker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MineAsyncService extends Thread{
	
	private final static Logger logger = LoggerFactory.getLogger(MineAsyncService.class);

	public static final int DoTime = 5000;

	/**
	 * 常规异步设计--实现runnable接口
	 * 
	 * @throws Exception
	 */
	public void async() throws Exception {
		Runnable task1 = new Runnable() {
			@Override
			public void run() {
				try {
					logger.info("\t----开始执行任务一.......");
					Thread.sleep(DoTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Runnable task2 = new Runnable() {
			@Override
			public void run() {
				try {
					logger.info("\t----开始执行任务二.......");
					Thread.sleep(DoTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		task1.run();
		task2.run();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
}
