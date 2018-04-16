package com.foxtail.service.sys;

import java.util.List;

import com.foxtail.common.base.BaseMybatisService;
import com.foxtail.common.page.Pagination;
import com.foxtail.model.sys.SysRole;
import com.foxtail.model.sys.SysRoleRes;
import com.foxtail.model.sys.SysUserRole;

public interface SysRoleService/* extends BaseMybatisService<SysRole,Integer>*/ {	
    
    
	SysRole getById(String id);
	
     public void delete(String[] ids);
    
     void save(SysRole role);
     
     void update(SysRole role);
    
    public Pagination findForPage(Pagination page,String kw);
    
    public List<SysRole> findAll();
    
    public List<SysUserRole> findUserRoleByUid(String uid);
   
    /**
     * 授权
    * Description:    
     */
    public void setRoleResources(String roleid,String[] resids);
    
    
    void copyResources(String roleid,String copyRoleid);
    
    
    
}

