package com.timebusker.web;

import com.timebusker.method.SortMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @DESC:FreemarkerController
 * @author:timebusker
 * @date:2018/6/24
 */
@Controller
public class FreemarkerController {

    @RequestMapping(value = "/free0")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("username", "你好！Freemarker!");
        return mv;
    }

    //freemarker取值，插值
    @RequestMapping("/free1")
    public ModelAndView free1() {
        ModelAndView mv1 = new ModelAndView();
        mv1.addObject("intVar", 100);
        mv1.addObject("LongVar", 10000000000000000L);
        mv1.addObject("doubleVar", 3.141592675d);
        mv1.addObject("StringVar", "我是freemarker,是字符串！");
        mv1.addObject("booleanVar", true);
        mv1.addObject("dateVar1", new Date());
        mv1.addObject("nullVar1", null);
        mv1.addObject("nullVar", "我是空");
        return mv1;
    }

    @RequestMapping("/free2")
    public ModelAndView free2() {
        ModelAndView mv2 = new ModelAndView();
        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("JavaScript");
        list.add("python");
        list.add("php");
        list.add("Html");
        mv2.addObject("programList", list);
        return mv2;
    }

    @RequestMapping(value = "/free3")
    public ModelAndView free3() {
        ModelAndView mv3 = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Java", "你好Java");
        map.put("address", "北京");
        map.put("身高", 172);
        map.put("money", 100.5);
        mv3.addObject("map", map);
        return mv3;
    }

    @RequestMapping("/free4")
    public ModelAndView free4() {
        ModelAndView mv4 = new ModelAndView();
        mv4.addObject("sort_int", new SortMethod());
        return mv4;
    }

    @RequestMapping("/free5")
    public ModelAndView free5() {
        ModelAndView mv5 = new ModelAndView("free5");
        return mv5;
    }

    @RequestMapping(value = "/free6")
    public ModelAndView free6() {
        ModelAndView mv6 = new ModelAndView();
        return mv6;
    }

    @RequestMapping(value = "/free7")
    public ModelAndView free7() {
        ModelAndView mv7 = new ModelAndView();
        return mv7;
    }
}
