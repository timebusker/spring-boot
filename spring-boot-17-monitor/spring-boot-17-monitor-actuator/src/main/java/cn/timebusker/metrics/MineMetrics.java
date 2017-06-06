package cn.timebusker.metrics;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.stereotype.Component;

/**
 * 实现PublicMetrics接口，做自定义开发
 * 
 * @author Administrator
 *
 */
@Component
public class MineMetrics implements PublicMetrics {

	/**
	 * 手动注入CrudRepository
	 */
//	private Collection<CrudRepository> repositories;
//
//	public DbCountMetrics(Collection<CrudRepository> repositories) {
//		this.repositories = repositories;
//	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection<Metric<?>> metrics() {
		List<Metric<?>> metrics = new LinkedList<>();
		for (int i=0;i<10;i++) {
	         String name = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
	         String metricName = "counter.datasource." + name;
	         metrics.add(new Metric(metricName, name.length()));
	     }
	     return metrics;
	}

}
