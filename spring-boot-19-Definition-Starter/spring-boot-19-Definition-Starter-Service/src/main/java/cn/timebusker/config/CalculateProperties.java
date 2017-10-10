package cn.timebusker.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 服务配置类
 */

@ConfigurationProperties("calculate")
public class CalculateProperties {

    private int scale;

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
