package com.foxtail.dao.mybatis.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.hwpf.model.SavedByEntry;

import com.foxtail.common.base.BaseDao;
import com.foxtail.common.page.Pagination;
import com.foxtail.model.sys.SysRole;

public interface SysRoleDao /*extends BaseDao<SysRole,Integer>*/ {	
    
	void save(@Param("mo")SysRole role);
	
	void update(@Param("mo")SysRole role);
	
	public SysRole getById(String id);
	
	public void delete(@Param("ids")String[] ids);
    
    public List<SysRole> findAll();
  
    List<SysRole> findForPage(String kw);

    List<String> findRoleTypesByUserId(String userId); 
    
    public void copyResources(@Param("roleid")String roleid, @Param("copyRoleid")String copyRoleid);
}
