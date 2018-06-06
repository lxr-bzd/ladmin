package com.ladmin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.foxtail.service.sys.SysResService;

public class DataInterceptor extends HandlerInterceptorAdapter{

	
	@Autowired
	private SysResService sysResService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String uri = request.getServletPath();
		uri = uri.substring(1,uri.length());
		
		request.setAttribute("resName",sysResService.queryUriName(uri));
		
		
		return super.preHandle(request, response, handler);
	} 
}
