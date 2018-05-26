package com.foxtail.controller.test;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxtail.service.sys.SysResService;
import com.ladmin.JsonResult;

import test.test;

@RequestMapping("test")
@Controller()
public class TestController {
	
	
	@Autowired
	private SysResService sysResService;
	
	
	@RequestMapping("test")
	private Object test() {
		
		
		
		return JsonResult.getSuccessResult();

	}
}
