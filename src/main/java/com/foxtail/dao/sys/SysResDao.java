package com.foxtail.dao.sys;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.foxtail.model.sys.SysRes;

public interface SysResDao {	
	
	SysRes getById(String id);
	
	void save(@Param("mo")SysRes Resource);
	
	void update(SysRes resource);
    
    public void delete(@Param("ids")String[] ids);
    
    List<SysRes> findAll();
    
    public List<SysRes> findAllByUid(@Param("uid")String userId);
    
    
    
    
    
    
    public void deleteByParentId(String parent_id);
    
    public List<SysRes> selectList(SysRes sysRes);
  
    List<SysRes> findForPage(@Param("kw")String kw,@Param("pid")String pid);
    
    public List<SysRes> findByPid(String pid);
    
    
    
    
    
    public List<SysRes> findAuthorizationAll(String roleId);
   
    
    
    public Integer selectCountByParentId(String parentId);
    
    List<SysRes> queryUriName(@Param("uri") String uri);
    
    
    public Integer selectResourceReference(String resourceId);


}
