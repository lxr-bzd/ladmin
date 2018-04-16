package com.foxtail.service.sys;

import java.util.List;

import com.foxtail.common.base.BaseMybatisService;
import com.foxtail.common.page.Pagination;
import com.foxtail.model.sys.SysUser;
import com.foxtail.model.sys.SysUserRole;

public interface SysUserService {	
    
	SysUser getById(String id);
	
	void save(SysUser user);
	
    void delete(String[] ids);
    
    void update(SysUser user);
    
    public Pagination findForPage(Pagination page,String kw); 
    /**
     * Description:    
     * @Title: findSingleUser  根据账户，电子邮件，电话号码或者身份证号查找用户
      */
     public SysUser findSingleUser(String account);
     
     public void setUserRole(SysUserRole[] sysUserRoles);
     
     
     public boolean isExist(String account);
     
     
     boolean updateByAccount(SysUser sysUser);
     
     
     
     
   
}

