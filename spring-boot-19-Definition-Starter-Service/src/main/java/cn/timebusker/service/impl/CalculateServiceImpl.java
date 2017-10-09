package cn.timebusker.service.impl;

import cn.timebusker.service.CalculateService;

/**
 * 算术运算类具体实现类
 */
public class CalculateServiceImpl implements CalculateService {

    /**
     * 这个从配置文件获取，就是默认的scale.
     */
    private int scale;

    public CalculateServiceImpl(int scale) {
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
    public double diarg(double arg1, double arg2) {
        return arg1 / arg2;
    }

    @Override
    public double mul(double arg1, double arg2) {
        return arg1 * arg2;
    }

    @Override
    public double setScale(double arg, int scale) {
        return 0;
    }

    @Override
    public double setScale(double arg) {
        return 0;
    }
}
