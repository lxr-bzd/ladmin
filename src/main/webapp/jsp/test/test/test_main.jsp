<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%@ include file="/common/global.jsp"%>	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询列表</title>
<script>

var treeTable = function(op){
	var tTable = {};
	
	tTable.model = $lxr.tree(op.data,{pidname:op.modelPid,idname:op.modelId});
	paintRoot();
	
	 function paintRoot(){
		 $(op.tbody).empty();
		 for (var i = 0; i < tTable.model.length; i++) {
			 var mo = tTable.model[i];
			 
			$(op.tbody).append(initTr(mo,0,true));
		}
		 
	 }
	 
	 
	 function initTr(model,level,isclose){
		 var $tr = $(op.getTr(model,level,isclose));
		 $tr.attr("data-pid",model[op.modelPid]);
		 $tr.attr("data-id",model[op.modelId]);
		 $tr.attr("data-isclose",isclose?"1":"");
		 $tr.attr("data-level",level);
		 $tr.data("treeModel",model);
		 return $tr;
	 }
	 
	 
	 
	 tTable.switchItem = function(id){
		 var $tr = $(op.tbody).find(">tr[data-id="+id+"]");
		if($tr.attr("data-isclose")){
			var $ctr = initTr($tr.data("treeModel"),$tr.attr("data-level"),false);
			$tr.replaceWith($ctr);
			printChild($ctr);
		}else{
			var $ctr = initTr($tr.data("treeModel"),$tr.attr("data-level"),true);
			$tr.replaceWith($ctr);
			removeChild($ctr);
			
		}
			
		 
	 }
	 
	 function printChild(tr){
			
			var childs = tr.data("treeModel").childs;
			if(!childs||childs.length<1)return;
			
			var level = tr.attr("data-level")+1;
			
			for (var i = 0; i < childs.length; i++) {
				var ctr = initTr(childs[i],level,true);
				$(tr).after(ctr);
			
			}
			
	 }
	 
	function removeChild(tr) {
		var id = tr.attr("data-id");
		var ch = $(op.tbody+">tr[data-pid="+id+"]");
		
		ch.each(function(i,e){
			removeChild($(e));
			
		});
		
		ch.remove();
	} 
	 
	
	
	return tTable;
}


</script>




</head>
<body>




    
    <div class="rightinfo">
		<div>
    		<form id="searchForm" name="searchForm"  method="post">
    		</form>
    	</div>
	  
		
		<div id="toolbar" class="btn-group" style="margin:15px auto">
	   
	   		 <button class="btn btn-info btn-round  btn-sm" onclick="toAdd();" >
					<i class="glyphicon glyphicon-plus"></i> 新增部门
			</button>

	   		
		</div>
    
    	<table id ="mtable"  data-toggle="table" class="table_list" >
			<thead>
				<tr>
					<th data-field="" data-checkbox="true"></th>
					<th data-field="id">id</th>
					<th >部门层级</th>
					<th >部门人数</th>
					<th >状态</th>
					
					<th >操作</th>
				</tr>
			</thead>
			<tbody id="main_tbody">
			
			</tbody>
		</table>

    </div>
      
</body>

<script type="text/javascript">

var data = [{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":26,"name":"综合服务部","parentid":null,"parentName":null,"manNum":"1","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":27,"name":"财务部","parentid":26,"parentName":null,"manNum":"2","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":31,"name":"安卓部","parentid":null,"parentName":null,"manNum":"13","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":34,"name":"苹果部","parentid":null,"parentName":null,"manNum":"6","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":35,"name":"后台1部","parentid":45,"parentName":null,"manNum":"6","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":36,"name":"后台2部","parentid":45,"parentName":null,"manNum":"8","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":37,"name":"后台3部","parentid":45,"parentName":null,"manNum":"4","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":38,"name":"前端部","parentid":null,"parentName":null,"manNum":"11","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":40,"name":"设计部","parentid":null,"parentName":null,"manNum":"11","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":42,"name":"商务部","parentid":null,"parentName":null,"manNum":"1","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":44,"name":"行政部","parentid":26,"parentName":null,"manNum":"2","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":45,"name":"后台部","parentid":null,"parentName":null,"manNum":"1","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":46,"name":"商务1部","parentid":42,"parentName":null,"manNum":"9","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":47,"name":"策划部","parentid":null,"parentName":null,"manNum":"5","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":50,"name":"总经办","parentid":null,"parentName":null,"manNum":"1","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":51,"name":"后台4部","parentid":45,"parentName":null,"manNum":"6","state":0},{"creator":"0","createtime":null,"updatetime":null,"remark":null,"isvalid":null,"id":52,"name":"后台5部","parentid":45,"parentName":null,"manNum":"7","state":0}];

var tb ;
$(function(){
	tb = treeTable({tbody:'#main_tbody',data:data,getTr:function(trModel,level,isclose){
		
		return template("tem_tr",{data:trModel,level:level,isclose:isclose});
		
	},modelId:"id",modelPid:"parentid"});
	
	//console.log(tb);
	
});









</script>



<script type="text/html" id="tem_tr">
<tr >
	<td class="bs-checkbox ">
		<input data-id="{{data.id}}"  name="btSelectItem" type="checkbox" onchage="">
	</td>
	<td>
		{{data.id}}
	</td> 
	<td style="text-align: left;padding-left:{{level*20}}px">
{{if level!=0}}  └─{{/if}}
{{if data.childs&&data.childs.length>0}}
		<span onclick="tb.switchItem('{{data.id}}')" {{if isclose}} class="icon icon-add" {{else}}class="icon icon-jian"  {{/if}}></span>
{{/if}}	
	<span>{{ data.name}}</span>
	</td>
	<td style="">{{data.manNum}}</td>
	<td style="">{{data.manNum}}</td>
	<td style="">
<div class="btn-group">
		
</div>
	</td>
</tr>
		


</script>
</html>