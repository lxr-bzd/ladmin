package com.ladmin.shiro;

import java.io.Serializable;

import org.apache.shiro.authz.Permission;

import com.lxr.commons.exception.ApplicationException;

public class Myprem implements Serializable,Permission{

	public static String MODULE_KEY = "sysM";
	
	public static String VIEW_KEY = "$sysV";
	
	
	String expression;
	String url;
	String sysModule;
	String sysView;
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSysModule() {
		return sysModule;
	}
	
	public void setSysModule(String sysModule) {
		this.sysModule = sysModule;
	}
	
	public static String getMODULE_KEY() {
		return MODULE_KEY;
	}
	public static void setMODULE_KEY(String mODULE_KEY) {
		MODULE_KEY = mODULE_KEY;
	}
	public static String getVIEW_KEY() {
		return VIEW_KEY;
	}
	public static void setVIEW_KEY(String vIEW_KEY) {
		VIEW_KEY = vIEW_KEY;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getSysView() {
		return sysView;
	}
	public void setSysView(String sysView) {
		this.sysView = sysView;
	}
	public static Myprem getMyprem(String premStr) {
		try {
			
			Myprem myprem = new Myprem();
			myprem.setExpression(premStr);
			
			premStr = premStr.trim();
			int i = premStr.indexOf("?");
			if(i!=-1) {
				myprem.setUrl(premStr.substring(0, i));
				
				String[] params = premStr.substring(i+1,premStr.length()).split("&");
				for (int j = 0; j < params.length; j++) {
					int ii = params[j].indexOf(MODULE_KEY+"=");
					if(ii==-1)continue;
					myprem.setSysModule(params[j].substring(10, params[j].length()));
					
				}
				
				
			}else myprem.setUrl(premStr);
			return myprem;
		} catch (RuntimeException e) {
			throw new ApplicationException("权限字符串解析错误："+e.getMessage());
		}
		
	}
	
	
	@Override
	public boolean implies(Permission p) {
		 if(!(p instanceof Myprem)){
	            return false;
	        }
		
		 
		 Myprem mp = (Myprem)p;
		
		 
		 if(this.url.equals(mp.getUrl())) {
			 if(this.sysModule==null?mp.getSysModule()==null:this.sysModule.equals(mp.getSysModule()))
				 return true;
			
		 }
		 
		 
		return false;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Myprem [expression=" + expression + ", prem=" + url + ", sysModule=" + sysModule + ", sysView="
				+ sysView + "]";
	}
	public static void main(String[] args) {
 
		System.out.println(getMyprem("pp?sysModule=1").implies(getMyprem("pp?$sysView=0&sysModule=1")));
	}
}
