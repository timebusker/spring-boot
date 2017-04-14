package cn.timebusker.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.timebusker.entity.DemoInfo;
import cn.timebusker.service.DemoInfoService;

@RestController
public class RESTfulAPIController {

	@Resource
	DemoInfoService service;

	@RequestMapping(value = {"/add/","/insert"} , method=RequestMethod.POST)
	public List<DemoInfo> addDemoInfo(@ModelAttribute DemoInfo demo) {
		// POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		return service.insertDemoInfo(demo);
	}

	@RequestMapping(value = "/{id}" , method=RequestMethod.GET)
	public DemoInfo findDemoInfo(@PathVariable String id) {
		//GET请求，用来获取信息
		//url中的id可通过@PathVariable绑定到函数的参数中
		return service.findDemoInfo(id);
	}

	@RequestMapping(value = "/update/{id}" , method=RequestMethod.PUT)
	public List<DemoInfo> updateDemoInfo(@PathVariable String id) {
		//PUT请求，用来更新信息
		DemoInfo demo = new DemoInfo();
		demo.setId(id);
		demo.setDescription("我是第"+id+"ST");
		demo.setName("TS__"+id);
		return service.updateDemoInfo(demo);
	}

	@RequestMapping(value = "/del/{id}" , method=RequestMethod.DELETE)
	public List<DemoInfo> deleteDemoInfo(@PathVariable String id) {
		//DELETE请求，用来删除信息
		DemoInfo demo = new DemoInfo();
		demo.setId(id);
		return service.deleteDemoInfo(demo);
	}
	
	@RequestMapping(value = {"/all","/"} , method=RequestMethod.GET)
	public List<DemoInfo> findAll() {
		//GET请求，用来获取信息
		return service.findAll();
	}
}
