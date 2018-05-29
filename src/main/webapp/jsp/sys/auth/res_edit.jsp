<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
<%@ include file="/common/edit_global.jsp"%>
<script type="text/javascript">

function onSubmit(form){
	
	
	
	return true;
}
	
	
var validateOpts = {
		  rules: {
		name: {required: true}
		 ,permission:{required: true}
		 
		  }
</script>
</head>
<body>
		
		<div class="formbody">
   			<c:if test="${empty param.sysA}" >
   			<form id="sub_form" method="post" data-action="${path}/sys/auth/res/save.do">
   			
					<ul class="forminfo">
					<li><span>上级资源名称：</span>
						<input class="form-control input-sm w260" value="${vo.name }" disabled="disabled" style="cursor: not-allowed;" />
						<input name="parentid" type="hidden" value="${vo.id }" />
					</li>
					<li><span>资源名称：</span>
					<input name="name"  type="text" class="form-control input-sm w260"  />
					</li>
					<li><span>资源路径：</span>
					<input name="path" type="text" class="form-control input-sm w260"  />
					</li>
					<li><span>资源图标：</span>
					<input name="icon" type="text" class="form-control input-sm w260" />
					</li>
					<li><span>层级：</span>
						<input name="level" type="text" class="form-control input-sm w260"  />
					</li>
					<li><span>权限字符串：</span>
					<input name="permission" type="text" class="form-control input-sm w260" />
					</li>
					<li><span>排序：</span><input name="sort" type="text" class="form-control input-sm w260" value="${param.sort+1}"  />
					</li>
					<li><span>是否启用：</span>
						<input type="radio"  name="status" value="1" checked="checked">启用
						<input type="radio"  name="status" value="2">禁用
					</li>
					
	    		</ul>
    		</form>
    		</c:if>
    		
    		<c:if test="${param.sysA=='edit'}">
    		<form id="sub_form" method="post" data-action="${path}/sys/auth/res/update.do">
   				<input type="hidden" name="id" value="${vo.id}"/>
				<ul class="forminfo">
					<li><span>上级资源名称：</span>
						<input class="form-control input-sm w260" value="${vo.parentName }" disabled="disabled" style="cursor: not-allowed;" />
					</li>
					<li><span>资源名称：</span>
					<input name="name" value="${vo.name }" type="text" class="form-control input-sm w260"  />
					</li>
					<li><span>资源路径：</span>
					<input name="path" value="${vo.path }" type="text" class="form-control input-sm w260"  />
					</li>
					<li><span>资源图标：</span>
					<input name="icon" value="${vo.icon }" type="text" class="form-control input-sm w260" />
					</li>
					<li><span>层级：</span>
						<input name="level" value="${vo.level }" type="text" class="form-control input-sm w260"  />
					</li>
					<li><span>权限字符串：</span>
					<input name="permission" value="${vo.permission }" type="text" class="form-control input-sm w260" />
					</li>
					<li><span>排序：</span>
					<input name="sort" type="text" value="${vo.sort }"  class="form-control input-sm w260" value="${param.sort+1}"  />
					</li>
					<li><span>是否启用：</span>
						<input type="radio"  name="status" value="1" checked="checked">启用
						<input type="radio"  name="status" value="2">禁用
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