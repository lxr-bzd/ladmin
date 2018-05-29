package com.foxtail.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.foxtail.common.DataGridResult;
import com.foxtail.common.JsonResult;
import com.foxtail.common.base.BaseController;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;
import com.foxtail.model.sys.SysRes;
import com.foxtail.model.sys.SysRoleRes;
import com.foxtail.service.sys.SysResService;
import com.foxtail.service.sys.SysRoleService;
import com.foxtail.vo.tree.TreeNode;

@Controller
@RequestMapping("test/test/test") 
public class TestController extends BaseController {
	
	private final static Logger log= Logger.getLogger(TestController.class);
	
	
	
	@RequestMapping
	public String toMain(String sysModule){
		return getMainJsp(sysModule);
	}


}