package com.foxtail.service.sys.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foxtail.common.page.Pagination;
import com.ladmin.shiro.CustomRealm;
import com.foxtail.dao.sys.SysRoleDao;
import com.foxtail.dao.sys.SysRoleResDao;
import com.foxtail.dao.sys.SysUserRoleDao;
import com.foxtail.model.sys.SysRole;
import com.foxtail.model.sys.SysRoleRes;
import com.foxtail.model.sys.SysUserRole;
import com.foxtail.service.sys.SysRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxr.commons.exception.ApplicationException;


@Service
public class SysRoleServiceImpl implements SysRoleService{

	private final static Logger log= Logger.getLogger(SysRoleServiceImpl.class);

	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private SysRoleResDao sysRoleResourceDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Autowired
	private CustomRealm customRealm;
    
    @Override
	public void delete(String[] ids) {
		
		if(ids==null||ids.length<1)
			throw new ApplicationException("删除数量不能为空");
		
			sysRoleResourceDao.delete(ids);
			sysUserRoleDao.deleteByUids(ids);
			
			this.sysRoleDao.delete(ids);
		
	}
    

    @Override
    public Pagination findForPage(Pagination page,String kw) {
    	PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page listCountry  = (Page)sysRoleDao.findForPage(kw);
		page.setTotalCount((int)listCountry.getTotal());
		page.setList(listCountry.getResult());
		return page;
    }

	@Override
	public void setRoleResources(String roleid,String[] resids) {
	
			sysRoleResourceDao.delete(new String[] {roleid});
			for (String resid : resids) {
				SysRoleRes roleResource = new SysRoleRes();
				roleResource.setRoleid(roleid);
				roleResource.setResid(resid);
				sysRoleResourceDao.save(roleid,resid);
			}
			customRealm.clearCached();
		
	}


	@Override
	public void copyResources(String roleid, String copyRoleid) {
		
		sysRoleResourceDao.delete(new String[] {roleid});
		sysRoleDao.copyResources(roleid, copyRoleid);
		
	}

	@Override
	public SysRole getById(String id) {
		
		return sysRoleDao.getById(id);
	}

	@Override
	public void save(SysRole role) {
		sysRoleDao.save(role);
		
	}

	@Override
	public void update(SysRole role) {
		sysRoleDao.update(role);
	}

	@Override
	public List<SysRole> findAll() {
		
		return sysRoleDao.findAll();
	}


	@Override
	public List<SysUserRole> findUserRoleByUid(String uid) {
		
		return null;
	}

	
	
	
}

