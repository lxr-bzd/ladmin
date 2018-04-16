<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
<%@ include file="/common/edit_global.jsp"%>
<script type="text/javascript">

var backurl = "${path}/sys/auth/user.do";

	function onSubmit(form){
		//表单验证
		 if(form.valid())
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
	<div>
	
		<div class="formbody">
   			<c:if test="${empty param.sysA}">
   			
   			
   			<form id="sub_form" class="vform" method="post" data-action="${path}/sys/auth/user/save.do">
				<ul class="forminfo">
					<li><span><strong style="color:red;">*</strong>姓名：</span><input name="name" id="userName" type="text" class="form-control input-primary input-sm w260" /></li>
					<li><span><strong style="color:red;">*</strong>账号：</span><input name="account" id="account" type="text" class="form-control input-primary input-sm w260"  /></li>
					<li><span><strong style="color:red;">*</strong>密码：</span><input name="password" id="password" type="password" class="form-control input-primary input-sm w260" /></li>
					
					<li><span>状态</span>
						<div class="fl mr6">
							<input type="radio" id="status" name="status" value="0" checked="checked"><label for="status">启用</label>
						</div>
						<div class="fl mr6">
							<input type="radio" id="status1" name="status" value="1"><label for="status1">锁定</label>
						</div>
						<div class="clear"></div>
					</li>
	    			</ul>
    		</form>
    		<div class="btnWrap">
					<input name="" type="button" class="btn btn-primary" value="确认" onclick="toSubmit()"/>
					<input name="" type="button" class="btn btn-warning" value="取消" onclick="goBack();"/>
	    		</div>
    		</c:if>
    		
    		<c:if test="${param.sysA=='edit'}">
    		<form id="sub_form" method="post" data-action="${path}/sys/auth/user/update.do">
   				<input type="hidden" name="id" value="${vo.id}"/>
				<ul class="forminfo">
					<li><span>姓名：</span><input name="name" id="userName" value="${vo.name}" type="text" class="form-control input-primary input-sm w260" /></li>
					<li><span>账号：</span>
						<input name="account" id="account" value="${vo.account}" type="text" class="form-control input-primary input-sm w260"  />
					</li>
					
					<li><span>状态</span>
						<div class="fl mr6">
							<input type="radio" id="status" name="status" value="0" <c:if test="${vo.status==0 }">checked="checked"</c:if>>
							<label for="status">启用</label>
						</div>
						<div class="fl mr6">
							<input type="radio" id="status1" name="status" value="1" <c:if test="${vo.status==1 }">checked="checked"</c:if> >
							<label for="status1">锁定</label>
						</div>
						<div class="clear"></div>
					</li>
	    			<li><span>&nbsp;</span><input name="" type="button" class="btn btn-primary" value="确认保存" onclick="toSubmit()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" class="btn btn-warning" value="返回列表" onclick="goBackList();"/></li>
	    		</ul>
    		</form>
    		<div class="btnWrap">
					<input name="" type="button" class="btn btn-primary" value="确认" onclick="toSubmit()"/>
					<input name="" type="button" class="btn btn-warning" value="取消" onclick="goBack();"/>
	    		</div>
    		
    		
    		</c:if>
	    </div>
	</div>
</body>
</html>