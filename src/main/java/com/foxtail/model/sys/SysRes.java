package com.foxtail.model.sys;

import java.io.Serializable;

public class SysRes implements Serializable{
	String id;//int(11)	 '主键'
	String name;//varchar(200)	 '资源名称'
	String parentid;//int(11)	 '上级id'
	String parentName;
	Integer level;//int(11)	 '层级（1-系统,2-模块, 3-菜单，4-按钮）'
	String ckey;//varchar(50)	 '名称'
	String path;//varchar(500)	 '资源路径'
	String icon;//varchar(100)	 '资源图标'
	String rdesc;//varchar(500)	 '资源描述'
	String permission;//varchar(100)	 '权限字符串'
	Integer sort;//int(11)	 '排序'
	Integer status;//int(11)	 '是否启用'
	String creator;//varchar(50)	 '创建人id'
	String createtime;//bigint(20)	 '创建时间'
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
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public String getCkey() {
		return ckey;
	}
	public void setCkey(String ckey) {
		this.ckey = ckey;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getRdesc() {
		return rdesc;
	}
	public void setRdesc(String rdesc) {
		this.rdesc = rdesc;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}

