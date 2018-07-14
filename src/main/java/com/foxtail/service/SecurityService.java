package com.foxtail.service;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foxtail.bean.ServiceManager;
import com.foxtail.common.util.MD5Util;
import com.foxtail.model.sys.SysUser;
import com.lxr.commons.exception.ApplicationException;

@Service
public class SecurityService {
	
	@Autowired
	SessionDAO sessionDAO;
	
	
	public String getUid() {
		
		try {
			String id=  ServiceManager.jdbcTemplate.queryForObject("select id from man_emp where account=?", String.class, getUser().getAccount());
			return id;
		} catch (Exception e) {
			throw new ApplicationException("该账号异常");
		}
	}
	
	public boolean equestPwd(String pwd) {
		
		if(StringUtils.isEmpty(pwd))return false;
		
		
		return MD5Util.string2MD5(pwd).equals(getUser().getPassword());
		
	}
	
	
	public String getAccount() {
		return getUser().getAccount();

	}
	
	public SysUser getUser() {
		Subject subject = SecurityUtils.getSubject();
		return (SysUser) subject.getPrincipal();

	}
	
	
	public String getUname() {
		
		
		String id=  ServiceManager.jdbcTemplate.queryForObject("select name from man_emp where account=?", String.class, getUser().getAccount());
		
		
		return id;

	}
	
	
	public boolean isOnline(String[] ids) {
		
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		
		for (Session session : sessions) {
			Object object = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			if(!(object instanceof SimplePrincipalCollection))continue;
			
			SysUser activeVo = (SysUser)(((SimplePrincipalCollection)object).getPrimaryPrincipal());
			for (String id : ids) {
				if(id.equals(activeVo.getId().toString()))return true;
			}
			
		}
		
		return false;

	}
	
	

}
