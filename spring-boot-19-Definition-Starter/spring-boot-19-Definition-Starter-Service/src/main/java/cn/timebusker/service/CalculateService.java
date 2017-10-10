package cn.timebusker.service;

/**
 * 算术运算类
 */
public interface CalculateService {

    /**
     * 加法.
     *
     * @param a
     * @param b
     * @return
     */
    double add(double arg1, double arg2);

    /**
     * 减法
     *
     * @param a
     * @param b
     * @return
     */
    double sub(double arg1, double arg2);

    /**
     * 除法.
     *
     * @param a
     * @param b
     * @return
     */
    double div(double arg1, double arg2);

    /**
     * 乘法.
     *
     * @param a
     * @param b
     * @return
     */
    double mul(double arg1, double arg2);

    /**
     * 精确到小数点以后scale位，以后的数字四舍五入。
     *
     * @param arg1    数
     * @param scale 保留精度.
     * @return两个参数的商
     */
    double setScale(double arg, int scale);

    /**
     * 精确到小数点以后scale位，以后的数字四舍五入。
     *
     * @param arg1    数
     * @param scale 保留精度.
     * @return两个参数的商
     */
    double setScale(double arg);
}
