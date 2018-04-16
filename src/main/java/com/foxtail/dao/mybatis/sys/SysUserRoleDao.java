package com.foxtail.dao.mybatis.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxtail.model.sys.SysUserRole;

public interface SysUserRoleDao {	
    
    void deleteByUids(@Param("uids")String[] uids);
    
    void save(@Param("uid")String uid,@Param("roleid")String roleid);
    
    public List<SysUserRole> findUserRoleByUid(@Param("uid")String uid);
}
