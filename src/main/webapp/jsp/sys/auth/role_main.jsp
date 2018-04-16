<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/global.jsp"%>	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询列表</title>
<script>
	//添加
	function toAdd(){
		$app.dialog('${path}/sys/auth/role/toedit.do',function(){
    		refTable();
		});
	}
	//删除
	function toRemove(ids){

		if(!ids)
		ids=getSelectedRowsIds('manTable');
		if(ids){
			$app.confirm("删除数据不可恢复，确定要删除吗？",function(){
				 $.post('${path}/sys/auth/role/delete.do?ids='+ids,function(data){

					    if(data.code==1){
					    	$app.alert("删除成功",function(){
					    		refTable();
					    		
					    	});
		 					
					    }else
					    	$app.alert(msg);
					    	
					   
					 },"json");
				
			});
			
			
		}else
			//提示信息
 		   $app.alert("请选择一条数据进行操作");
		
	}
	
	 function toEdit(id){
	    	
	    	$app.dialog('${path}/sys/auth/role/toedit.do?sysA=edit&id='+id,function(){
	    		refTable();
			});
		}
	
    //查看
    function toInfo(){
    	var selected=getSelectedRowsArr('SysRoleList');
    	if(selected.length>0&&selected.length<2){
    		
    		$app.dialog('${path}/sys/auth/role/findById.do?id='+selected,function(){
        		refTable();
    		});
    	}else{
    		$app.alert('请选择一条数据进行操作');
    	}
	}
	
	//设置查询参数
	function postQueryParams(params) {
		var queryParams = $("#searchForm").serializeObject();
		queryParams.limit=params.limit;
		queryParams.offset=params.offset;
		return queryParams;
	}
	
    
   

	//根据id删除
	function deleteById(id){
		$app.confirm("删除数据不可恢复，确定要删除吗？",function(){
			 $.post('${path}/sys/auth/role/deleteById.do?ids='+id,function(data){
				   var json = data;
				   if(json.success){
					   
					   $app.alert("删除成功，角色被用户绑定的，将不删除",function(){  //关闭事件
						   refleshData('SysRoleList');
	 					});
					 
				   }else{
					   $app.alert("删除失败");
					   
				   }
			},"json");
			
		});
		
		
	}

	//根据id查看
	function viewById(id){
		$app.dialog('${path}/sys/auth/role/findById.do?id='+id,function(){
    		refTable();
		});
	}
	
    //操作工具栏
    function operatorFormatter(value, row, index) {
    	
    	var operator='<div class="btn-group">';
	 	
			operator+=$app.btn('auth','授权','toAuth(\''+row.id+'\')');;
		
    		operator+=$app.btn('edit','编辑','toEdit(\''+row.id+'\')');
	    
	   operator+=$app.btn('delete','删除','toRemove(\''+row.id+'\')');
    	
    	
	return operator+'</div>';
	}
     
    //角色状态格式化
    function statusFormatter(value,row,index){
    	if(value=='1'){
    		return '<span class="label label-success label-lg">启用</span>'
    	}else if(value=='2'){
    		return '<span class="label label-danger arrowed">禁用</span>';
    	}else{
    		return "";
    	}
    }

    function copyRes(roleid){
    	
    	$app.prompt("被复制id",function(pass){
    		$app.request("${path}/sys/auth/role/copyRes.do",function(data){
    			
    			
    		},{param:{roleid:roleid,copyRoleid:pass}});
    		
    	});
    }
    
    //授权
    function toAuth(id){
    	
    	
    		$app.dialog("${path}/sys/auth/role/toSetRes.do?roleId="+id,function(){
        		
    		});
    	
    } 
    
    
    function refTable(){
    	
    	$('#SysRoleList').bootstrapTable('refresh');
    }
    

</script>
</head>
<body>
	
    
    <div class="rightinfo">
		<div class="explain_col">
    		<form id="searchForm" name="searchForm"  method="post">
    			<span>角色名称：</span><input type="text" name="roleName" class="form-control input-sm" style="width: 220px;display: inline;">&nbsp;

    			<input type="button" class="btn btn-info btn-round btn-sm" value="查询" onclick="refTable()">&nbsp;&nbsp;
    	<input type="button" class="btn btn-warning btn-round btn-sm" value="重置" onclick="$('#searchForm')[0].reset();">	</form>
    	</div>
	    <div id="toolbar" class="btn-group">
	    	
		    	<button class="btn btn-info btn-round btn-sm" onclick="toAdd();">
					<i class="glyphicon glyphicon-plus"></i> 添加角色
				</button>
		    
			
				<button class="btn btn-warning btn-round btn-sm" onclick="toRemove()">
					<i class="glyphicon glyphicon-trash"></i> 批量删除
				</button>
			
	   
		</div>
    	<table id="SysRoleList"  class="table_list" data-toggle="table"
			data-url="${path}/sys/auth/role/view.do" data-pagination="true"
			data-side-pagination="server" data-cache="false" data-query-params="postQueryParams"
			data-page-list="[10, 15, 20, 30, 50,100]" data-method="post"
			data-show-refresh="false" data-show-toggle="false"
			data-show-columns="false" data-toolbar="#toolbar"
			data-click-to-select="false" data-single-select="false"
			data-striped="false" data-content-type="application/x-www-form-urlencoded"
			>
			<thead>
				<tr>
					<th data-field="" data-checkbox="true"></th>
					<th data-field="id">ID</th>
					<th data-field="name">角色名称</th>
					<th data-field="status" data-formatter="statusFormatter" >状态</th>
					<th data-field="createtime" data-formatter="dateFormatter" >创建时间</th>
					<th data-field="operator" data-formatter="operatorFormatter">操作</th>
				</tr>
			</thead>
		</table>
			<!--<label class="select_all"><input type="checkbox" name="checkall" onclick=" var sl =$('#SysRoleList thead input[name=btSelectAll]');  if(sl.prop('checked')!=this.checked)sl.click();" class="J_checkall">全选/取消</label>-->

    </div>
</body>
</html>