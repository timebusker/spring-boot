package cn.timebusker.service.impl;

import cn.timebusker.service.CalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * 算术运算类具体实现类
 */
public class CalculateServiceImpl implements CalculateService {

    public static final Logger LOG = LoggerFactory.getLogger(CalculateServiceImpl.class);

    /**
     * 这个从配置文件获取，就是默认的scale.
     */
    private int scale;

    public CalculateServiceImpl(int scale) {
        LOG.info("=========>>\t\t" + scale);
        this.scale = scale;
    }

    @Override
    public double add(double arg1, double arg2) {
        return arg1 + arg2;
    }

    @Override
    public double sub(double arg1, double arg2) {
        return arg1 - arg2;
    }

    @Override
    public double div(double arg1, double arg2) {
        return arg1 / arg2;
    }

    @Override
    public double mul(double arg1, double arg2) {
        return arg1 * arg2;
    }

    @Override
    public double setScale(double arg, int scale) {
        return new BigDecimal(arg).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Override
    public double setScale(double arg) {
        return new BigDecimal(arg).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
