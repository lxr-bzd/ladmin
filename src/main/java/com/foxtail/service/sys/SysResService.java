package com.foxtail.service.sys;

import java.util.List;

import com.foxtail.common.base.BaseMybatisService;
import com.foxtail.common.page.Pagination;
import com.foxtail.model.sys.SysRes;
import com.foxtail.model.sys.SysRoleRes;

public interface SysResService {	
    
	SysRes getById(String id);
	
	public void delete(String[] ids);
	
	void save(SysRes resource);
	
	void update(SysRes resource);
    
    public Pagination findForPage(Pagination page,String kw,String pid);
    
    public List<SysRes> findAll();
    
    public List<SysRoleRes> findRoleResByRoleid(String roleid);
    
    

    public List<SysRes> selectList(SysRes sysRes);
    
    public List<SysRes> findByPid(String pid);
 	
 	
 	public List<SysRes> findAuthorizationAll(String roleId);
 	
	public List<SysRes> findAllByUserId(String userId);
	
	 public String queryUriName(String uri);
	
	public Integer selectResourceReference(String resourceId);
 	
 	
}

