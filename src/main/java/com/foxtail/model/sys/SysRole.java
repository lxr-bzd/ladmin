package com.foxtail.model.sys;


import java.io.Serializable;

public class SysRole implements Serializable{

   	String id;
   	String name;
   	String rdesc;
   	Integer status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRdesc() {
		return rdesc;
	}
	public void setRdesc(String rdesc) {
		this.rdesc = rdesc;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}

