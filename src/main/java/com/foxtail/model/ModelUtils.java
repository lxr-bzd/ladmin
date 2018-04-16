package com.foxtail.model;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import com.foxtail.model.sys.SysUser;

public class ModelUtils {
	
	/**
	 * 填充model公共的属性，如创建者，创建时间
	 * @param model
	 * @return
	 */
	public static BaseModel finishSaveModel(BaseModel model) {
		
		Subject subject = SecurityUtils.getSubject();
		SysUser user = (SysUser) subject.getPrincipal();
		model.setCreator(user.getName());
		model.setCreatetime(System.currentTimeMillis()+"");
		return model;
	}
	
		
	public static BaseModel finishUpdateModel(BaseModel model) {
			
			model.setUpdatetime(System.currentTimeMillis()+"");
			
			return model;
		}
	}
