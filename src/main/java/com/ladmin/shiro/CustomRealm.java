package com.ladmin.shiro;

import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.foxtail.model.sys.SysRes;
import com.foxtail.model.sys.SysUser;
import com.foxtail.service.sys.SysResService;
import com.foxtail.service.sys.SysRoleService;
import com.foxtail.service.sys.SysUserService;

/**
* Description:自定义realm 
* @ClassName: CustomRealm 
 */
public class CustomRealm extends AuthorizingRealm{
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysResService sysResService;
	
	@Autowired
	private SysRoleService sysRoleService;
	

	 /** 
     * 认证操作，判断一个请求是否被允许进入系统 
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		CaptchaUsernamePasswordToken captchaUsernamePasswordToken=(CaptchaUsernamePasswordToken)token;
		String userCode = (String) captchaUsernamePasswordToken.getUsername();
	/*	//验证验证码是否正确
		String captcha=null;
		Object obj_captcha=SecurityUtils.getSubject().getSession().getAttribute("CodeKey");
		if(obj_captcha instanceof String){
			captcha = (String)obj_captcha;
		}
		String passKey = captchaUsernamePasswordToken.getCaptcha();
		if(captcha != null &&!captcha.equalsIgnoreCase(passKey)){
			SecurityUtils.getSubject().getSession().setAttribute("CodeKey",null);
			throw new IncorrectCaptchaException("验证码错误！");
		}else if (null==captcha||null==captcha) {
			SecurityUtils.getSubject().getSession().setAttribute("CodeKey",null);
			throw new IncorrectCaptchaException("验证码为空");
		}*/
		
		SysUser sysUser = sysUserService.findSingleUser(userCode);
		//验证用户名
		if(null == sysUser){
			return null; //shiro 会自动抛出账号不存在的异常
		}
		//验证是否锁定
		if (sysUser.getIslock()!=null&&sysUser.getIslock()==2) //抛出帐号锁定的异常,配置ip地址可以放行
			throw new LockedAccountException();  
		
		
		/*BeanUtils.copyProperties(sysUser, sysUserActive);
		sysUserActive.setLoginName(userCode);*/
		String password = sysUser.getPassword();
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(sysUser, password, this.getName());
		return simpleAuthenticationInfo;
	}
	
	 /** 
     * 授权操作，决定那些角色可以使用那些资源 
     */ 
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("用户权限");
		SysUser sysUser =  (SysUser) principals.getPrimaryPrincipal();
		List<String> permissions = new ArrayList<String>();
		
 		if(null != sysUser){
			List<SysRes> resources = sysResService.findAllByUserId(sysUser.getId());
			for(SysRes resource : resources){
				String permission = resource.getPermission();
				if(resource.getStatus()!=null&&resource.getStatus()!=1)
					continue;
				if(null != permission && ! "".equals(permission.trim())){
					permissions.add(permission);
					
				}
			}
			
			
		}
 		
		//List<String> roleList = sysRoleService.findRoleTypesByUserId(sysUser.getId());
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addStringPermissions(permissions);
		
		//simpleAuthorizationInfo.addRoles(roleList);//角色类型
		
		return simpleAuthorizationInfo;
	}

	@Override
	public void setName(String name) {
		System.out.println("设置名字");
		super.setName("customRealm");
	}
	
	//清除缓存
	public void clearCached() {
		System.out.println("清除缓存");
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		
		super.clearCache(principals);
	}

	
}
