package com.foxtail.controller.test;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxtail.bean.ServiceManager;
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
		
		Map<String, Object> map = ServiceManager.jdbcTemplate.queryForMap("select * from dsg");
		 ServiceManager.jdbcTemplate.update("insert dfgre() values(?,?)", 1,2);
		return JsonResult.getSuccessResult();

	}
	
}
