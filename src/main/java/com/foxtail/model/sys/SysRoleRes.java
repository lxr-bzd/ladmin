package com.foxtail.model.sys;

import java.io.Serializable;

public class SysRoleRes implements Serializable{

 	
   	private String id;
   	private String roleid;
   	private String resid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getResid() {
		return resid;
	}
	public void setResid(String resid) {
		this.resid = resid;
	}



}

