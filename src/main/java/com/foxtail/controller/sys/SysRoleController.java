package com.foxtail.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
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
import com.foxtail.model.sys.SysRole;
import com.foxtail.model.sys.SysUserRole;
import com.foxtail.service.sys.SysResService;
import com.foxtail.service.sys.SysRoleService;
import com.foxtail.vo.tree.TreeNode;
import com.lxr.commons.exception.ApplicationException;

@Controller
@RequestMapping("sys/auth/role") 
public class SysRoleController extends BaseController {
	
	private final static Logger log= Logger.getLogger(SysRoleController.class);

	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	SysResService sysResService;
	
	@RequestMapping
	public String toMain(String sysModule){
		
		return getMainJsp(sysModule);
	}
	
	@RequestMapping("/toedit") 
	public String  toAdd(String sysM,String sysA,String id,ModelMap model){
		String jsp= getEditJsp(sysM);
		if(isEditPage(sysA))
			model.put("vo", sysRoleService.getById(id));
		return jsp;
	}

	
	@RequestMapping("/view") 
	@ResponseBody
	public DataGrid list (HttpServletRequest request,String kw) {
		Pagination pagination = sysRoleService.findForPage(getPagination(request),kw);
		
		return DataGridResult.getResult(pagination);
	}
		
	
	
	
	
	@RequestMapping("save") 
	@ResponseBody
	public Object save(SysRole po) {
		
			this.sysRoleService.save(po);
			return JsonResult.getSuccessResult();
	}
	
	
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteById(String ids) {
		this.sysRoleService.delete(ids.split(","));
		return JsonResult.getSuccessResult();
	}
	
	
	
	@RequestMapping("update")
	@ResponseBody
	public Object editSubmit(SysRole po)throws Exception{
		
			this.sysRoleService.update(po);
			
		return JsonResult.getSuccessResult();
	}

	
	@RequestMapping("toSetRes") 
	public ModelAndView toSelectTree(Integer roleId)throws Exception{
		ModelAndView mv = new ModelAndView("sys/auth/role_setres");
		mv.addObject("roleId", roleId);
		return mv;
	}
	
	

	
	@RequestMapping("toAuthorization") 
	@ResponseBody
	public Object toAuthorization(String roleid, String resids){
		
			String[] residArr = null;
			if(StringUtils.isEmpty(resids))residArr = new String[0];
			else residArr = resids.split(",");
			this.sysRoleService.setRoleResources(roleid,residArr);	
		return JsonResult.getSuccessResult();
	}
	
	@RequestMapping("copyRes") 
	@ResponseBody
	public Object copyRes(String roleid,String copyRoleid){
		
			this.sysRoleService.copyResources(roleid, copyRoleid);;
			
		return JsonResult.getSuccessResult();
	}
	
	
	@RequestMapping("loadRoleTree")
	@ResponseBody
	public Object loadRoleTree(String uid){
/*		if(StringUtils.isBlank(uid))
			throw new ApplicationException("用户id不能为空");
	   
		List<TreeNode> treenodes = new ArrayList<TreeNode>();
		 
		List<SysRole> list = this.sysRoleService.findAll();
		if(list==null)return treenodes;
		
		Map<String, String> map =  toMap(uid,sysRoleService.findUserRoleByUid(uid));
		
		for(SysRole sysRole :list){
			TreeNode treeNode = new TreeNode(); 
			treeNode.setId(sysRole.getId());
			treeNode.setText(sysRole.getName());
			treeNode.setIsParent(false);
			if(map.get(sysRole.getId())!=null)treeNode.setChecked(true);
			treenodes.add(treeNode);
		}
		return treenodes;*/
		
		return JsonResult.getSuccessResult(sysRoleService.findAllByUid(uid));
	}
	
	
	private Map<String, String> toMap(String uid,List<SysUserRole> list) {
		
		Map<String, String> map = new HashMap<>();
		if(list==null)return map;
		for (int i = 0; i < list.size(); i++) {
			SysUserRole userRole = list.get(i);
			if(uid.equals(userRole.getUid()))
				map.put(userRole.getRoleid(), uid);
		}
		return map;

	}
	
}