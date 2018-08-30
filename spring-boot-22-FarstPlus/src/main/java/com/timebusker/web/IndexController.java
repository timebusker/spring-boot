package com.timebusker.web;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.timebusker.exception.CommonException;
import com.timebusker.common.R;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @DESC:IndexController
 * @author:timebusker
 * @date:2018/8/22
 */
@Controller
public class IndexController extends AbstractController {

    @Autowired
    private Producer producer;

    private static final String USER_ACCOUNT = "admin";

    /**
     * 获取验证码
     *
     * @throws CommonException
     * @throws IOException
     */
    @RequestMapping("/authcode")
    public void captcha() throws CommonException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        // 输出验证码图片到页面
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(String username, String password, String captcha) throws IOException {
        String kaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
//        if(!captcha.equalsIgnoreCase(kaptcha)){
//            return R.error("验证码不正确");
//        }
        if (!USER_ACCOUNT.equals(username)) {
            return R.error("账户不正确");
        }
        if (!USER_ACCOUNT.equals(password)) {
            return R.error("密码不正确");
        }
        session.setAttribute("USER_ACCOUNT", USER_ACCOUNT);
        return R.ok().put("token", USER_ACCOUNT);
    }
}
