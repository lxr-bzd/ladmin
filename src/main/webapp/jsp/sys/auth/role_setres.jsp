<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>授权</title>
<%@ include file="/common/edit_global.jsp"%>
<link rel="stylesheet" href="${path}/jslib/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="${path}/jslib/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${path}/jslib/zTree_v3/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="${path}/jslib/jquery.json-2.4.min.js"></script> 
<script type="text/javascript" src="${path}/js/MaskUtil.js"></script>
<style type="text/css">
.tree_div {
  width:360px;
  height: 360px;
  overflow: auto;
}
.button_div{
  text-align: center;
  vertical-align:middle;
  width: 360px;
}
</style>
	<script type="text/javascript">
	    var zTree;
	    var selectNode;
		var setting = {
				check : {
					enable : true,
					 chkboxType:  { "Y": "ps", "N": "s" },
					chkStyle : "checkbox"
				},
				async : {
					enable : true, //是否通过异步方式加载数据
					dataType : "text",
					type : "post",
					url :  "${path}/sys/auth/res/loadSelectTree.do?id=${roleId}"
				},
				view : {
					dblClickExpand : true,
					selectedMulti : true
				},
				data : {
					key : {
						name : "text"
					},
					simpleData : {
						enable : true,
						idKey : "id", // id编号命名 默认   
						pIdKey : "pId", // 父id编号命名 默认    
						rootPId : 0
					// 用于修正根节点父节点数据，即 pIdKey 指定的属性值   
					}
				},
				callback : {
					
				}
			};
			
		$(function() {
			zTree = $.fn.zTree.init($("#treeDemo"), setting);
		});

		function submitSelect(){
			
			var nodes = zTree.getCheckedNodes(true);

			var resids = [];
			var roleId = '${roleId}';
			$.each(nodes,function(i,node){
				var resid = node.id;
				resids.push(resid);
			});
			MaskUtil.mask();
			
			
			$.ajax({
				  url:"${path}/sys/auth/role/toAuthorization.do",
				  type:"post",
				  data:{roleid:'${roleId}',resids:resids.join(",")},
				  dataType:"json",
				  cache:false,
				  success:function(json){
					  if(json.code==1)
					  $app.alert('成功',function(){  //关闭事件
							goBack();
						});
					  else $app.alert(data.msg?data.msg:'失败');
				   },error:function(data){
					   
				   }
				}); 
		}
		
	
	</script>
</head>


<body style="margin-bottom: 45px;">
	<div id="treeDemo" class="ztree tree_div"></div>
	<div class="btnWrap">
    				<input name="" type="button" class="btn btn-primary" value="确认" onclick="submitSelect();"/>&nbsp;&nbsp;&nbsp;&nbsp;
    				<input name="" type="button" class="btn btn-warning" value="取消" onclick="goBack();"/>
	</div>
</body>
</html>