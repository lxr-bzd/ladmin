<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/global.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
<%@ include file="/common/edit_global.jsp"%>
<script type="text/javascript">

var backurl = "${path}/sys/auth/role/toList.do";

	function onSubmit(form){
		
		//表单验证
		var roleName=$('#roleName').val();
		if(!roleName){
			$app.alert('角色名称不能为空');
			return ;
		}
		
		return true;
	}
	
	
//表单验证
	 var validateOpts = {
			  rules: {
			userName: {required: true}
			 ,account:{required: true}
			 ,password:{required: true}
			 
			 
			  }
			/*   ,
			  messages: {
			   name: {
			    required: "hiik"
			   },
			   sort: {
			    required: "请输入密码" 
			   }
			  } */
			 };
	


</script>
</head>
<body>
		
		<div class="formbody">
   		<c:if test="${empty param.sysA}">
   			<form id="sub_form" method="post" data-action="${path}/sys/auth/role/save.do">
				<ul class="forminfo">
					<li><span>角色名称：</span><input name="name" id="roleName" type="text" class="form-control input-primary input-sm w260"/></li>
					
					
					<li><span>角色描述：</span>
						<textarea  class="form-control input-sm w260" name="rdesc" id="roleDesc"></textarea>
					</li>
					<li><span>状态：</span>
						<div class="fl mr6">
							<input type="radio" checked="checked" value="1" name="status" id="roleStatus">
							<label for="roleStatus">启用    </label>
						</div>
						<div class="fl mr6">
							<input type="radio" value="2" name="status" id="roleStatus1" name="roleStatus">
							<label for="roleStatus1">禁用</label>
						</div>
						<div class="clear"></div>
					</li>
	    		</ul>
    		</form>
    		</c:if>
    		
    		<c:if test="${param.sysA=='edit'}">
    		<form id="sub_form" method="post" data-action="${path}/sys/auth/role/update.do">
   				<input type="hidden" name="id" value="${vo.id}"/>
				<ul class="forminfo">
					<li><span>角色名称：</span><input name="name" id="roleName" value="${vo.name}" type="text" class="form-control input-primary input-sm w260" /></li>
					
					<li><span>角色描述：</span>
						<textarea class="form-control input-sm w260" name="rdesc" id="roleDesc">${vo.rdesc}</textarea>
					</li>
					<li><span>状态：</span>
						<div class="fl mr6">
							<input type="radio" value="1" name="status" id="roleStatus" <c:if test="${vo.status==1 }">checked="checked"</c:if> >
							<label for="roleStatus">启用</label>
						</div>
						<div class="fl mr6">
							<input type="radio" value="2" name="status" id="roleStatus1" name="roleStatus" <c:if test="${vo.status==2 }">checked="checked"</c:if> >
							<label for="roleStatus1">禁用</label> 
						</div>
						<div class="clear"></div>
					</li>
	    			</ul>
    		</form>
    		</c:if>
	    </div>
	<div class="btnWrap">
					<input name="" type="button" class="btn btn-primary" value="确认" onclick="toSubmit()"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="" type="button" class="btn btn-warning" value="取消" onclick="goBack();"/>
	    		</div>
</body>
</html>