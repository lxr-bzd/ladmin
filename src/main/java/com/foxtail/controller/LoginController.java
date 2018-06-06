package com.foxtail.controller;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ladmin.shiro.IncorrectCaptchaException;
import com.foxtail.bean.ServiceManager;
import com.foxtail.model.sys.SysUser;
import com.foxtail.service.sys.SysResService;
import com.ladmin.JsonResult;

@Controller
public class LoginController {

	//@Autowired
	//protected RedisTemplate<Serializable, Serializable> redisTemplate;
	
	@Autowired
	private SysResService sysResService;
	
	@RequestMapping("login")
	@ResponseBody
	public Object login(HttpServletRequest request, ModelMap model) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){ //已经登录，重新登录
			SecurityUtils.getSecurityManager().logout(subject);
		}
		SysUser user = (SysUser) subject.getPrincipal();
		
    	if(user != null){
    		model.addAttribute("loginName",user.getAccount());
    	}
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		if(exceptionClassName!=null){
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				return JsonResult.getFailResult("账号不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
				return JsonResult.getFailResult("用户名/密码错误");
			}else if (IncorrectCaptchaException.class.getName().equals(exceptionClassName)){
				return JsonResult.getFailResult("验证码错误");
			}else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
				return JsonResult.getFailResult("账户被禁用");
			}else {
				return JsonResult.getFailResult("登录异常");
			}
		}
		return JsonResult.getFailResult("登录失败");
	}
	
	/** 
	* Description:加载验证码    
	* @Title: loadPasskey  
	* @since 2016年5月31日 下午2:27:30
	* @param request
	* @param response
	* @throws Exception
	 *//*
	@RequestMapping("loadPasskey") 
	public void loadPasskey(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		HttpSession session = request.getSession();
		int width = 80, height = 30;
		ServletOutputStream responseOutputStream = null;
		try {
			 ServletOutputStream outputStream = response.getOutputStream();
			String codeKey= VerifyCodeUtils.outputVerifyImage(width, height, outputStream, 4);
			session.setAttribute("CodeKey", codeKey);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null != responseOutputStream){
				responseOutputStream.flush();
				responseOutputStream.close();
			}
		}
	}*/

	
	
	@RequestMapping("logout")
	@ResponseBody
	private Object logout() {
		Subject subject = SecurityUtils.getSubject();
		SecurityUtils.getSecurityManager().logout(subject);
		return JsonResult.getSuccessResult();
	}
	
	@RequestMapping("my")
	@ResponseBody
	public Object my() {
		
		return JsonResult.getSuccessResult(ServiceManager.securityService.getUser());
	}
	
	
	public static void main(String[] args) {
		System.out.println(Pattern.matches("35", "35|35,|,35|,35,"));
	}
	
	 public static boolean StringMatchRule(String souce, String regex) {
	        boolean result = false;
	        if (regex != null && souce != null) {
	            result = Pattern.matches(regex, souce);
	        }


	        return result;
	    }
	
}
