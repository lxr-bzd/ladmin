package com.foxtail.service.sys.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foxtail.common.page.Pagination;
import com.ladmin.shiro.Myprem;
import com.ladmin.shiro.PermManager;
import com.foxtail.dao.sys.SysResDao;
import com.foxtail.dao.sys.SysRoleResDao;
import com.foxtail.model.sys.SysRes;
import com.foxtail.model.sys.SysRoleRes;
import com.foxtail.service.sys.SysResService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxr.commons.exception.ApplicationException;
@Service
public class SysResServiceImpl implements SysResService{

	private final static Logger log= Logger.getLogger(SysResServiceImpl.class);

	@Autowired
	private SysResDao SysResDao;
	
	@Autowired
	SysRoleResDao sysRoleResDao;
	
	List<SysRes> SysRess;
	
	boolean ismodify = true;


    
    public List<SysRes> selectList(SysRes SysRes){
    	return SysResDao.selectList(SysRes);
    }
    
    @Override
    public List<SysRes> findAll() {
    	/*if(SysRess==null||ismodify) {
    	*/
    		SysRess = SysResDao.findAll();
    		
    	/*	onUpdate();
    		
        	ismodify = false;
    	} else 	System.out.println("抓取缓存");*/
    	
		return SysRess;
    }
    
    
    public void onUpdate() {
    	PermManager.getAllPerms().clear();
    	for (SysRes res : SysRess) {
    		if(StringUtils.isBlank(res.getPermission()))continue;
    		
			Myprem myprem = Myprem.getMyprem(res.getPermission());
			List<Myprem> myprems = PermManager.getAllPerms().get(myprem.getUrl());
			if(myprems==null) { myprems = new ArrayList<Myprem>();PermManager.getAllPerms().put(myprem.getUrl(), myprems);}
			myprems.add(myprem);
    	
    	}
    }

    @Override
    public void delete(String[] ids){
    	String [] idArr=ids;
    	
    	//所有需要删除的ID
    	Set<String> idd = new HashSet<>();
    	
    	if (idArr.length<1) throw new ApplicationException("ids="+ids);
			
			for (int i = 0; i < idArr.length; i++) {
				String id = idArr[i];
				idd.add(id);
				queryChild(idd, id);
				
				
			}
			
			
			List l = new ArrayList<>(idd);
			String[] tids = new String[l.size()];
			l.toArray(tids);
			SysResDao.delete(tids);
			//删除关联表
			sysRoleResDao.deleteByResid(tids);
			
		ismodify = true;
    }
    
    
    private void queryChild(Set<String> ids,String pid) {
		
    	List<SysRes> list = SysResDao.findByPid(pid);
    	
    	if(list==null||list.size()<1)return;
    	
    	for (SysRes SysRes : list) {
    		String id = SysRes.getId();
    		ids.add(id);
			queryChild(ids, id);
			
		}

	}
    
    public String queryUriName(String uri) {
    	List<SysRes> list = SysResDao.queryUriName(uri);
    	if(list==null||list.size()<1)return "";
    	
    	if(list.size()>1)System.out.println("警告：多个资源uri相同");
    	
		return list.get(0).getName();

	}
    
    
    
    

    @Override
    public Pagination findForPage(Pagination page,String kw,String pid) {
    	PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page listCountry  = (Page)this.SysResDao.findForPage(kw,pid);
		page.setTotalCount((int)listCountry.getTotal());
		page.setList(listCountry.getResult());
		return page;
	  
    } 
    
    @Override
	public List<SysRes> findByPid(String pid) {
		
		return SysResDao.findByPid(pid);
	}
    
    @Override
	public List<SysRes> findAuthorizationAll(String roleId) {
		return SysResDao.findAuthorizationAll(roleId);
	}
    
	@Override
	public List<SysRes> findAllByUserId(String userId) {
		//findAll();
		return this.SysResDao.findAllByUid(userId);
	}

	@Override
	public Integer selectResourceReference(String resourceId) {
		return this.SysResDao.selectResourceReference(resourceId);
	}

	@Override
	public SysRes getById(String id) {
		  return SysResDao.getById(id);
	}

	@Override
	public void update(SysRes resource) {
		SysResDao.update(resource);
	}
	
	@Override
	public void save(SysRes resource) {
		SysResDao.save(resource);
	}

	@Override
	public List<SysRoleRes> findRoleResByRoleid(String roleid) {
		
		return sysRoleResDao.findByRoleid(roleid);
	}



	
}

