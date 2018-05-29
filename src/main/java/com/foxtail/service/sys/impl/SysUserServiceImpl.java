package com.foxtail.service.sys.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foxtail.bean.ServiceManager;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.util.MD5Util;
import com.foxtail.dao.mybatis.sys.SysUserDao;
import com.foxtail.dao.mybatis.sys.SysUserRoleDao;
import com.foxtail.model.sys.SysUser;
import com.foxtail.model.sys.SysUserRole;
import com.foxtail.service.sys.SysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxr.commons.exception.ApplicationException;


@Service
public class SysUserServiceImpl implements SysUserService{

	private final static Logger log = Logger.getLogger(SysUserServiceImpl.class);

	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
   
    
	@Override
	public SysUser getById(String id) {
		 return sysUserDao.getById(id);
	}

	@Override
	public void save(SysUser user) {
		if(isExist(user.getAccount()))
			throw new ApplicationException("账号已存在");
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		
		sysUserDao.save(user);
	}

	@Override
	public void update(SysUser user) {
		sysUserDao.update(user);
		
	}
	
    @Override
    public void delete(String[] ids){
    	
    	if(ids==null||ids.length<1)throw new ApplicationException("删除数量不能为空");
    	
    	if(ServiceManager.securityService.isOnline(ids))
    		throw new ApplicationException("存在在线用户不能删除");
    	
    	sysUserRoleDao.deleteByUids(ids);
		
		this.sysUserDao.delete(ids);
    }
    
    
    

    @Override
    public Pagination findForPage(Pagination page,String kw) {
    	PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page listCountry  = (Page)sysUserDao.findForPage(kw);
		page.setTotalCount((int)listCountry.getTotal());
		page.setList(listCountry.getResult());
		return page;
    }
    
    
    
    

	@Override
	public SysUser findSingleUser(String account) {
		return this.sysUserDao.findSingleUser(account);
	}

	@Override
	public void doSetRole(String uid,String[] roleids) {
		
		sysUserRoleDao.deleteByUids(new String[] {uid});
		if (null==roleids||roleids.length<1)return;
		
		for (String string : roleids) {
			sysUserRoleDao.save(uid,string);
		}
	}



	@Override
	public boolean updateByAccount(SysUser sysUser) {
		
		if(sysUser.getPassword()!=null)
			sysUser.setPassword(MD5Util.string2MD5(sysUser.getPassword()));
		
		return sysUserDao.updateByAccount(sysUser);
	}

	@Override
	public boolean isExist(String account) {
		
		if(sysUserDao.findSingleUser(account)!=null)return true;
		return false;
	}

	
		
	
}

