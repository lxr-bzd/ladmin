package com.foxtail.controller.sys;


import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.foxtail.common.DataGridResult;
import com.foxtail.common.base.BaseController;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;
import com.foxtail.model.sys.SysUser;
import com.foxtail.model.sys.SysUserRole;
import com.foxtail.service.sys.SysUserService;
import com.ladmin.JsonResult;

@Controller
@RequestMapping("sys/auth/user") 
public class SysUserController extends BaseController {
	
	private final static Logger log= Logger.getLogger(SysUserController.class);
	@Autowired
	private SysUserService sysUserService; 
	
	
	
	@RequestMapping("view") 
	@ResponseBody
	public Object list(String sysT,HttpServletRequest request,String kw){
		if("i".equals(sysT)) {
			return JsonResult.getSuccessResult(sysUserService.getById(request.getParameter("id")));
		}
		
		Pagination pagination = sysUserService.findForPage(getPagination(request),kw);
		return DataGridResult.getResult(pagination);
	}
		
	
	
	
	@RequestMapping("save") 
	@ResponseBody
	public Object save(SysUser po) {
		sysUserService.save(po);
		return JsonResult.getSuccessResult();
	}
	
	
	@RequestMapping("delete")
	@ResponseBody
	public Object delete(String ids) {
			this.sysUserService.delete(ids.split(","));
			return JsonResult.getSuccessResult();
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Object update(SysUser user) {
		sysUserService.update(user);
		return JsonResult.getSuccessResult();
	}

	
	@RequestMapping("/toSetUserRoles")
	@ResponseBody
	public Object toSetUserRoles(@RequestBody SysUserRole[] sysUserRoles){
		
		this.sysUserService.setUserRole(sysUserRoles);
		return JsonResult.getSuccessResult();
	}
	
	@RequestMapping("toSetRole") 
	public String toSetRole(String userId){
		
		return "sys/auth/user_role";
	}
	

	
}