package com.foxtail.dao.mybatis.sys;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxtail.model.sys.SysRoleRes;

public interface SysRoleResDao {	
    
   void save(@Param("roleid")String roleid,@Param("resid")String resid);
   
   void delete(@Param("roleids")String[] roleids);
   
   void deleteByResid(@Param("resids")String[] resids);
   
   List<SysRoleRes> findByRoleid(@Param("roleid")String roleid);
}
