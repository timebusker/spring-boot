package cn.timebusker.util;

import org.apache.log4j.Logger;

public class LoggingUtil {

	private static final Logger logger = Logger.getLogger(LoggingUtil.class);
	
	public static void creatLogging() {
		logger.info("----------------------------------INFO");
		logger.debug("----------------------------------DEBUG");
		logger.error("----------------------------------ERROR");
	}
}
