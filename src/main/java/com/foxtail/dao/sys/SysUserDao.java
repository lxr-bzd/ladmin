package com.foxtail.dao.sys;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.foxtail.model.sys.SysUser;

public interface SysUserDao {	
    
    public void delete(@Param("ids")String[] ids);
    
    public List<SysUser> selectList(SysUser sysUser);
  
    List<SysUser> findForPage(@Param("kw") String kw);

    /**
    * Description:    
    * @Title: findSingleUser  根据账户，电子邮件，电话号码或者身份证号查找用户
     */
    public SysUser findSingleUser(String account);
    
    /**
     * Description:查询账户是否存在    
     * @Title: selectCountIsExist  

      */
     public Integer selectCountIsExist(Map<String, String> map);
    
     
     public boolean updateByAccount(SysUser sysUser);
     
     
     public void deleteByAccounts(@Param("accounts") String[] accounts);
     
     
     
     public SysUser getById(String id);
     
     void save(@Param("mo")SysUser user);
     
     void update(@Param("mo")SysUser user);
}
