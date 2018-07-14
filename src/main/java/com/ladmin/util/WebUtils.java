package com.ladmin.util;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

	
	public static boolean isAjax(HttpServletRequest request) {
		return "XMLHttpRequest".equalsIgnoreCase(request .getHeader("X-Requested-With"));

	}
	
}
