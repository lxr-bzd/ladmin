package com.foxtail.common.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.foxtail.common.LoggerUtils;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.WebUtils;
import com.ladmin.util.PageUtils;
import com.ladmin.JsonResult;
import com.lxr.commons.exception.ApplicationException;


public class BaseController implements WebBindingInitializer
{
 
  static Logger logger = org.slf4j.LoggerFactory.getLogger(BaseController.class);
  
  public static final String ACTION_EDIT = "edit";
  public static final String ACTION_ADD = "add";
  public static final String ACTION_INFO = "info";
  
  String baseJsp;
  
  public void putAction(Map<String, Object> map,Object val) {
	map.put("action", val);

}
  

  @InitBinder
  public void initBinder(WebDataBinder binder, WebRequest request)
  {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
    
  }
  
  @ExceptionHandler
  @ResponseBody
  public Object exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
	
		if(!WebUtils.isAjax(request))
			throw e;
		
		if(e instanceof ApplicationException)
			return JsonResult.getFailResult(e.getMessage());
		
		logger.error("未知异常",e);
		
		return JsonResult.getFailResult("未知异常:"+e.getMessage());

	}

  public String getBaseJsp() {
	  if(baseJsp==null) {
		  
		  baseJsp = getClass().getAnnotation(RequestMapping.class).value()[0];
	  }
	  
	  return baseJsp;

  }
  
 
  public String getMainJsp(String module) {
	  
	  return getBaseJsp()+(StringUtils.isEmpty(module)?"_main":("_main@"+module));
  }
  
  
  public String getEditJsp(String module) {
	  return getBaseJsp()+(StringUtils.isEmpty(module)?"_edit":("_edit@"+module));
}
  
  public String getInfoJsp(String module) {
	  return getBaseJsp()+(StringUtils.isEmpty(module)?"_info":("_info@"+module));


}
  
  
  
  protected boolean isEditPage(String sysA) {
	return "edit".equals(sysA);

  }
  
  
  
  
  public Pagination getPagination(HttpServletRequest request) {
	
	  Pagination page = new Pagination();
	  
	  int pageNo=PageUtils.getPage();
	int pageSize=PageUtils.getRows();
	  
	  try {
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
	} catch (Exception e) {
		LoggerUtils.getLog(this).warn("分页参数错误：pageNo="+pageNo+",pageSize="+pageSize);
	}
	  
	  
	  
	  return page;
	  
	  

  }

}
