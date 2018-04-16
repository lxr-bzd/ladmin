<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
<%@ include file="/common/edit_global.jsp"%>
<script type="text/javascript">

function onSubmit(form){
	
	//表单验证
	var message="";
	var resourceName=$.trim($("#resourceName").val());
	var permissionStr=$.trim($('#permissionStr').val());
	if(!resourceName){
		message="-资源名称不能为空";
	}
	if(!permissionStr){
		message+="<br/>-权限字符串不能为空";
	}
	if(message){
		$app.alert(message);
		
		return ;
	}
	
	return true;
}
	
</script>
</head>
<body>
	<div>
		
		<div class="formbody">
   			<c:if test="${empty param.sysA}" >
   			<form id="sub_form" method="post" data-action="${path}/sys/auth/res/save.do">
   			
				<ul class="forminfo">
					<li><label>上级资源名称：</label>
						<input class="form-control" style="width:350px;" value="${vo.name }" disabled="disabled" style="cursor: not-allowed;" />
						<input name="parentid" id="parentId" type="hidden" value="${vo.id }" />
					</li>
					<li><label>资源名称：<font color="red" size="3">*</font></label>
					<input name="name" id="resourceName" type="text" class="form-control input-primary" style="width:350px" /></li>
					<li><label>资源路径：<c:if test="${vo.level==2}"><font color="red" size="3">*</font></c:if></label>
					<input name="path" <c:if test="${vo.level==2}">required="true"</c:if> id="resourcePath" type="text" class="form-control input-primary" style="width:350px" /></li>
					<li><label>资源图标：</label>
					<input name="icon" id="resourceIcon" type="text" class="form-control input-primary" style="width:350px" /></li>
					<li><label>层级：</label>
						<input name="level" id="level" type="text" class="form-control input-primary" style="width:350px;cursor: not-allow" disabled="disabled"  value="${vo.level+1 }" />
						<input name="level"  type="hidden" value="${vo.level+1 }"  />
					</li>
					<li><label>权限字符串：<font color="red" size="3">*</font></label>
					<input name="permission" id="permissionStr" type="text" class="form-control input-primary" style="width:350px" /></li>
					<li><label>排序：</label><input name="sort" id="orderNum" value="${vo.sort}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="10" type="text" class="form-control input-primary" style="width:350px" /></li>
					<li><label>是否启用：</label>
						<input type="radio" id="status" name="status" value="1" checked="checked">启用
						<input type="radio" id="status" name="status" value="2">禁用
					</li>
					
					<c:if test="${vo.level == 2}">
					<li><label>是否生成菜单：</label>
						 <input checked="checked" type="checkbox" name="createButton" value="true" />
						          同时生成增、删、改、查按钮资源权限配置
					</li>
				    </c:if>
					<li><label>资源描述：</label>
						<textarea class="form-control" style="width:350px;" name="rdesc"></textarea>
					</li>
	    			<li><label>&nbsp;</label><input name="" type="button" class="btn btn-primary" value="确认保存" onclick="toSubmit()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" class="btn btn-warning" value="返回列表" onclick="goBackList();"/></li>
	    		</ul>
    		</form>
    		</c:if>
    		
    		<c:if test="${param.sysA=='edit'}">
    		<form id="sub_form" method="post" data-action="${path}/sys/auth/res/update.do">
   				<input type="hidden" name="id" value="${vo.id}"/>
				<ul class="forminfo">
					<li><span>上级资源名称：</span>
						<input class="form-control input-sm w260" value="${vo.name }" disabled="disabled" style="cursor: not-allowed;" />
					</li>
					<li><span><font color="red" size="3">*</font>资源名称：</span><input name="resourceName" id="resourceName" type="text" class="form-control input-primary input-sm w260" value="${vo.name }"/></li>
					<li><span>资源路径：<c:if test="${vo.level==3}"><font color="red" size="3">*</font></c:if></span>
						<input name="resourcePath" <c:if test="${vo.level==3}">required="true"</c:if> id="resourcePath" type="text" class="form-control input-primary input-sm w260" value="${vo.path }" />
					</li>
					<li><span>资源图标：</span><input name="resourceIcon" id="resourceIcon" type="text" class="form-control input-primary input-sm w260"  value="${vo.icon}" /></li>
					<li><span>层级：</span>
						<input name="level" id="level" type="text" class="form-control input-primary input-sm w260" style="cursor: not-allow" disabled="disabled"  value="${vo.level }" />
					</li>
					<li><span><font color="red" size="3">*</font>权限字符串：</span><input name="permissionStr" id="permissionStr" type="text" class="form-control input-primary input-sm w260" value="${vo.permission}" /></li>
					<li><span>排序：</span><input name="orderNum" id="orderNum" value="${vo.sort}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="10" type="text" class="form-control input-primary input-sm w260"/></li>
					<li><span>是否启用：</span>
						<div class="fl mr6">
							<input type="radio" id="status" <c:if test="${vo.status==1 }">checked="checked"</c:if> name="status" value="1" >
							<label for="status">启用</label>
						</div>
						<div class="fl mr6">
							<input type="radio" id="status1" <c:if test="${vo.status==2 }">checked="checked"</c:if> name="status" value="2">
							<label for="status1">禁用</label>
						</div>
						<div class="clear"></div>
					</li>
					<li><span>资源描述：</span>
						<textarea class="form-control input-sm w260" name="resourceDesc" id="resourceDesc">${vo.rdesc}</textarea>
					</li>	    			
	    			<li><span>&nbsp;</span><input name="" type="button" class="btn btn-primary" value="确认保存" onclick="toSubmit()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" class="btn btn-warning" value="返回列表" onclick="goBackList();"/></li>
	    		</ul>
    		</form>
    		
    		</c:if>
    	
	    </div>
	</div>
</body>
</html>