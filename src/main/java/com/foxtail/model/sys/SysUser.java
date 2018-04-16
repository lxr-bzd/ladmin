package com.foxtail.model.sys;

import java.io.Serializable;

public class SysUser implements Serializable{
	String id;//int(11)	 '主键'
	String account;//varchar(50)	 '账号'
	String name;//varchar(50)	 '用户名称'
	String password;//varchar(50)	 '密码'
	Integer islock;
	Integer status;//int(11)	 '状态(1启用 2禁用 )'
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getIslock() {
		return islock;
	}
	public void setIslock(Integer islock) {
		this.islock = islock;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
   	
}

