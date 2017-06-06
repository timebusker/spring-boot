package cn.timebusker.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 实现HealthIndicators接口，自定义监控检测项，并将类注入容器中
 * 
 * spring boot 已经实现DiskSpaceHealthIndicator（低磁盘空间检测）、
 * DataSourceHealthIndicator（检查是否能从DataSource获取连接）等
 */
@Component
public class MineHealthIndicators implements HealthIndicator {

	private int errorCode = 0;
	
	@Override
	public Health health() {
		if (checkDataSource() != 0) {
			return Health.down().withDetail("Error Code", errorCode).build();
		}
		return Health.up().build();
	}

	private int checkDataSource() {
		return errorCode;
	}
}
