package com.timebusker.web;

import com.timebusker.entity.UserInfo;
import com.timebusker.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @DESC:UserController
 * @author:timebusker
 * @date:2018/7/5
 */

@Controller
public class UserController {

    @Autowired
    UserInfoService service;

    @RequestMapping("/entity")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        int sum = 12 + 13;
        UserInfo u = service.getUser();
        Map<String, UserInfo> hu = service.allUser();
        Collection<UserInfo> cu = service.allUser().values();
        List<UserInfo> lu = new ArrayList<>(cu);
        mv.addObject("sum", sum);
        mv.addObject("u", u);
        mv.addObject("hu", hu);
        mv.addObject("lu", lu);
        return mv;
    }
}
