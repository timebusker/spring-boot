package cn.timebusker.config;

import cn.timebusker.service.CalculateService;
import cn.timebusker.service.impl.CalculateServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类
 *
 * @ConditionalOnClass，当classpath下发现该类的情况下进行自动配置。
 * @ConditionalOnMissingBean，当Spring Context中不存在该Bean时。
 * @ConditionalOnProperty(prefix = "calculate ",value = "enabled",havingValue = "true")，当配置文件中calculate.enabled=true时。
 */

@Configuration
@ConditionalOnClass(CalculateServiceImpl.class)
@EnableConfigurationProperties(CalculateProperties.class)
public class CalculateAutoConfigure {

    public static final Logger LOG = LoggerFactory.getLogger(CalculateAutoConfigure.class);

    @Autowired
    private CalculateProperties calculateProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "calculate", value = "enabled", havingValue = "true")
    public CalculateService calculateService() {
        LOG.info("=========>>\t\t" + calculateProperties.getScale());
        return new CalculateServiceImpl(calculateProperties.getScale());
    }
}
