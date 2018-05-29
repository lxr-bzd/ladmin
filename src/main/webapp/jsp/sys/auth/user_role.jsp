<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/global.jsp"%>	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择角色树</title>
<%-- <link rel="stylesheet" href="${path}/jslib/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="${path}/jslib/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${path}/jslib/zTree_v3/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="${path}/jslib/zTree_v3/js/jquery.ztree.exhide-3.5.min.js"></script>
<script type="text/javascript" src="${path}/jslib/jquery.json-2.4.min.js"></script>  --%>

<script type="text/javascript">
	  
	  $(function(){
		 
		  $app.request("${path}/sys/auth/role/loadRoleTree.do?uid=${param.uid}",function(data){
			  var arr = data.data;
			  var html = "";
			  for (var i = 0; i < arr.length; i++) {
				html+='<li><input name="'+arr[i].id+'" type="checkbox" '+(arr[i].uid?'checked="checked" ':'')+'>'+arr[i].name+'</li>'
			}
			  
			  $("#role_group").html(html);
			  
		  });
		  
	  });
	  

	  
	  function getChecks(){
		  var ids = "";
		  $("#role_group input:checked").each(function(i,e){
			  ids += $(e).attr("name")+",";
			  
		  });
		  
		  if(ids)return ids.substring(0,ids.length-1);
		  
		  return ids;
		  
	  }
	  

		function toSubmit(){
			
			var parms = {uid:'${param.uid}',roleids:getChecks()};
			
			$app.request("${path}/sys/auth/user/setUserRoles.do",function(data){
				 if(data.code==1){
					  goBack();
				  }else{
					  $app.alert('设置失败');
				  }
				
			},{param:parms})
			
			
		}
		

		
		//返回列表
		function goBack(){
			try {
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			} catch (e) {
				if(isNaN(index))window.location=backurl;
			}
		}
	</script>
  </head>
  
  <body >
 	
	<div  >
				<ul id="role_group">
					
	    		</ul>
	
	</div>
<div class="btnWrap">
					<input name="" type="button" class="btn btn-primary" value="确认" onclick="toSubmit()"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="" type="button" class="btn btn-warning" value="取消" onclick="goBack();"/>
	    		</div>
	
 </body>
</html>