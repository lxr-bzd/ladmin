package com.foxtail.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.foxtail.common.DataGridResult;
import com.foxtail.common.base.BaseController;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;
import com.ladmin.shiro.Myprem;
import com.ladmin.shiro.ShiroUser;
import com.foxtail.model.sys.SysRes;
import com.foxtail.model.sys.SysRoleRes;
import com.foxtail.service.sys.SysResService;
import com.foxtail.service.sys.SysRoleService;
import com.foxtail.vo.tree.TreeNode;
import com.ladmin.JsonResult;

@Controller
@RequestMapping("sys/auth/res") 
public class SysResController extends BaseController {
	
	private final static Logger log= Logger.getLogger(SysResController.class);
	
	@Autowired
	private SysResService sysResService; 
	
	@Autowired
	SysRoleService sysRoleService;
	
	
	@RequestMapping
	public String toMain(String sysModule){
		Subject subject = SecurityUtils.getSubject();
		Boolean b = subject.isPermitted(Myprem.getMyprem("eferwf"));
		return getMainJsp(sysModule);
	}

	
	
	
	@RequestMapping("view") 
	@ResponseBody
	public DataGrid list (HttpServletRequest request,String kw,String parentId) {
		
		Pagination pagination = sysResService.findForPage(getPagination(request), kw,parentId);
		
		return DataGridResult.getResult(pagination);
	}
		
	
	@RequestMapping("toedit") 
	public String toAdd(String sysM,String sysA,String id,String parentId,Integer orderNum,ModelMap model){
		
		String jsp= getEditJsp(sysM);
		if(!isEditPage(sysA)) {
			SysRes sysRes = sysResService.getById(parentId);
			model.put("vo", sysRes);
		}else 
			model.put("vo", sysResService.getById(id));
		return jsp;
	
	}
	
	
	@RequestMapping("save") 
	@ResponseBody
	public Object save(SysRes po) {
	
			this.sysResService.save(po);
			return JsonResult.getSuccessResult();
	}
	
	
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteById(String  ids) {
		
		sysResService.delete(ids.split(","));
		return JsonResult.getSuccessResult();
	}
	
	
	@RequestMapping("update")
	@ResponseBody
	public Object update(SysRes po){
		sysResService.update(po);
		return JsonResult.getSuccessResult();
		
	}
	
	
	
	@RequestMapping("loadSelectTree") 
	@ResponseBody
	public List<TreeNode> loadSelectTree(String id){

		List<TreeNode> treenodes = new ArrayList<TreeNode>(); 
		List<SysRes> list = sysResService.findAll();
		Map<String, String> map = toMap(sysResService.findRoleResByRoleid(id));
		
		for(SysRes sysRes:list){
			TreeNode node = new TreeNode();
			node.setId(sysRes.getId());
			node.setText(sysRes.getName());
			node.setpId(sysRes.getParentid());
			
			List<SysRes> listChilden = getChild(id, list);
			if(listChilden.size() == 0){
				node.setIsParent(false);
			}
			if(null != sysRes.getParentid() && "0".equals(sysRes.getParentid())){
				node.setOpen(true);
			}
			if(map.get(sysRes.getId())!=null){
				node.setChecked(true);
			}
			treenodes.add(node);
		}
		
		return treenodes;
	}
	
	
	private Map<String, String> toMap(List<SysRoleRes> sysRoleRes) {
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < sysRoleRes.size(); i++) {
			map.put(sysRoleRes.get(i).getResid(), sysRoleRes.get(i).getRoleid());
			
		}
		return map;
	}
	
	
	private List<SysRes> getChild(String id,List<SysRes> allres) {
		List<SysRes> childs = new ArrayList<>();
		for (int i = 0; i < allres.size(); i++) {
			SysRes resource = allres.get(i);
			if(id.equals(resource.getParentid()))
				childs.add(resource);		
			}
			return childs;
	}
	
	
	
	@RequestMapping("toTree") 
	public ModelAndView toTree()throws Exception{
		ModelAndView mv = new ModelAndView("jsp/sys/sysresource/sysresource_tree");
		return mv;
	}
	
	@RequestMapping("loadTree") 
	@ResponseBody
	public List<TreeNode> loadTree(String id){
		if(id==null)id ="0";
		
		List<TreeNode> treenodes = new ArrayList<TreeNode>();
		
		List<SysRes> list = this.sysResService.findByPid(id);
		for(SysRes sysRes :list){
			TreeNode treeNode = new TreeNode();
			Map<String,Object> attributes = new HashMap<String,Object>();
			treeNode.setId(sysRes.getId());
			treeNode.setText(sysRes.getName());
			treeNode.setpId(sysRes.getParentid());
			treeNode.setState("closed");
			
			List<SysRes> listChilden = sysResService.findByPid(sysRes.getId());
			if(listChilden.size() == 0){
				treeNode.setIsParent(false);
			}
			attributes.put("resourceLevel", sysRes.getLevel());
			treeNode.setAttributes(attributes);
			
			treenodes.add(treeNode);
		}
		return treenodes;
	}
	

	@RequestMapping("myres")
	@ResponseBody
	public Object myRes() {
    	
    	List<SysRes> resList = sysResService.findAllByUserId(ShiroUser.getUserId());

    	return resList;
	}

}