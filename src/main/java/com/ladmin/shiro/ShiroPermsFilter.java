package com.ladmin.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import com.alibaba.fastjson.JSONObject;
import com.foxtail.common.web.WebUtils;
import com.ladmin.JsonResult;

public class ShiroPermsFilter extends AuthorizationFilter {
  

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		
		  	HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
	        
	       
	        Myprem myprem = Myprem.getMyprem(getPermsUrl(httpRequest));
	        
	        
	        if(SecurityUtils.getSubject().isPermitted(myprem))return true;
	       
	        return false;
	}
	
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if(WebUtils.isAjax(httpRequest)){
        	response.getWriter().print(JSONObject.toJSONString(JsonResult.getResult(JsonResult.STATUS_UN_PERMS, "无权限！")));
        }else
        	((ServletRequest)request).getRequestDispatcher(getUnauthorizedUrl()).forward(request, response);
        	
		return false;
	}
	
	private String getPermsUrl(HttpServletRequest httpRequest) {
		String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length(),httpRequest.getRequestURI().length() );
        
		if(url.startsWith("/"))
			url = url.substring(1,url.length()-3);
		
		String sysModel = httpRequest.getParameter(Myprem.MODULE_KEY);
		
		if(!StringUtils.isBlank(sysModel))
			url = url+"?"+Myprem.MODULE_KEY+"="+sysModel;
		
		return url;
		

	}
}
