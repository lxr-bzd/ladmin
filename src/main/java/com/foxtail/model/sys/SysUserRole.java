package com.foxtail.model.sys;

import java.io.Serializable;

public class SysUserRole implements Serializable{
   	private String id;
   	private String uid;
   	private String roleid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}


}

