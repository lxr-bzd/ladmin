package com.foxtail.controller;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.foxtail.bean.ServiceManager;
import com.foxtail.common.JsonResult;
import com.foxtail.common.LoggerUtils;
import com.foxtail.common.util.VerifyCodeUtils;
import com.foxtail.core.shiro.IncorrectCaptchaException;
import com.foxtail.core.shiro.ShiroUser;
import com.foxtail.model.sys.SysRes;
import com.foxtail.model.sys.SysUser;
import com.foxtail.service.sys.SysResService;

@Controller
public class LoginController {
	
	@Autowired
	protected RedisTemplate<Serializable, Serializable> redisTemplate;
	
	@Autowired
	private SysResService sysResService;
	
	@RequestMapping("login")
	public String login(HttpServletRequest request, ModelMap model) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){ //已经登录，重新登录
			SecurityUtils.getSecurityManager().logout(subject);
		}
		SysUser user = (SysUser) subject.getPrincipal();
		//String loginName = request.getParameter("loginName");
    	if(user != null){
    		model.addAttribute("loginName",user.getAccount());
    	}
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		if(exceptionClassName!=null){
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				model.addAttribute("warn","账号不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(
					exceptionClassName)) {
				String msg="用户名/密码错误";
				model.addAttribute("warn",msg);
			}else if (IncorrectCaptchaException.class.getName().equals(exceptionClassName)){
				model.addAttribute("warn","验证码错误");
			}else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
				model.addAttribute("warn","账户被禁用");
			}else {
				model.addAttribute("warn","登录异常");
			}
		}
		return "/login";
	}
	
	/**
	 * 
	* Description:加载验证码    
	* @Title: loadPasskey  
	* @since 2016年5月31日 下午2:27:30
	* @param request
	* @param response
	* @throws Exception
	 */
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
	}

	/**
	* Description:授权成功    
	* @since 2016年5月31日 下午2:27:54
	* @return
	 */
	@RequestMapping("admin")
    public String admin(ModelMap model)
    {
		SysUser user = ShiroUser.getUser();
    	model.put("user", user);
    	List<SysRes> resList = sysResService.findAllByUserId(ShiroUser.getUserId());
    	model.put("resList", resList);
    	return "main";
    }
	
	@RequestMapping("redis")
	@ResponseBody
	public Object redis() {
		/*JedisConnectionFactory jedisConnectionFactory;
		jedisConnectionFactory.seth*/
	
		 redisTemplate.execute(new RedisCallback<Object>() {

	            @Override
	            public Object doInRedis(RedisConnection connection) throws DataAccessException {
	                connection.set(redisTemplate.getStringSerializer().serialize("user.uid.1"),
	                               redisTemplate.getStringSerializer().serialize("rgrtgrtfgh"));
	                return null;
	            }
	        });
		
		
		return JsonResult.getSuccessResult();
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
