package com.timebusker;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @DESC:WebKaptchaConfig:生成验证码配置
 * @author:timebusker
 * @date:2018/8/22
 */
@Configuration
public class WebKaptchaConfig {

    @Bean
    public DefaultKaptcha producer() {
        // http://blog.csdn.net/xiaokui_wingfly/article/details/41744887
        Properties properties = new Properties();
        // 图片边框，合法值：yes , no
        properties.put("kaptcha.border", "no");
        // kaptcha.image.width	图片宽	200
        // kaptcha.image.height	图片高	50
        // kaptcha.producer.impl	图片实现类	com.google.code.kaptcha.impl.DefaultKaptcha
        // kaptcha.textproducer.impl	文本实现类	com.google.code.kaptcha.text.impl.DefaultTextCreator
        // kaptcha.background.clear.from	背景颜色渐变，开始颜色	light grey
        // kaptcha.background.clear.to	背景颜色渐变，结束颜色	white
        properties.put("kaptcha.textproducer.font.color", "gray");
        properties.put("kaptcha.background.clear.from", "gray");
        properties.put("kaptcha.background.clear.to", "white");
        properties.put("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        properties.put("kaptcha.textproducer.char.space", "2");
        properties.put("kaptcha.textproducer.char.length", "4");
        properties.put("kaptcha.noise.color", "gray");
        properties.put("kaptcha.textproducer.char.string", "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
