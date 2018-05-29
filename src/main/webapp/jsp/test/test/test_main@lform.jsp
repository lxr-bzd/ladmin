<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%@ include file="/common/global.jsp"%>	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询列表</title>
<script>
(function(){
	var lform = {};
	
	lform.init = function(form,model){
		form.find(".init-widget").each(function(i,e){
			if('INPUT'==e.tagName){
				switch ($(e).attr("type")) {
				case "text":
				case "hidden":
					initText(e,model,form);
					break;
				case "radio":
					initRadio(e,model,form);
					break;

				default:
					initText(e,model);
					break;
				}
				
			}
			if("SELECT"==e.tagName){
				
				
			}
			
			
		});
	
	}
	
	
	
	function initText(e,mo){
		var vkey = $(e).attr("data-vkey");
		var format = $(e).attr("data-format");
		var val = (format?window[format](mo[vkey]):mo[vkey])
		$(e).val(val);
		
	}
	
	function initRadio(e,mo,form){
		var vkey = $(e).attr("data-vkey");
		var format = $(e).attr("data-format");
		var val = (format?window[format](mo[vkey]):mo[vkey])
		
		form.find("input[name="+$(e).attr("name")+"][value="+val+"]").attr("checked",'checked');
		
	}
	
	initSelect = function(e,mo,form){
		var vkey = $(e).attr("data-vkey");
		var format = $(e).attr("data-format");
		var val = (format?window[format](mo[vkey]):mo[vkey])
		
		var select = $(e);
		var p = eval("("+select.attr("data-model")+")");
			
			$.ajax({
				url:p.url,
				dataType : 'JSON',
				type:'post',
				success:function(data){
					
					var l = data;
					if(p.root)
					var ex  = p.root.split(",");
					else var ex = [];
					
					for (var i = 0; i < ex.length; i++) {
						l = l[ex[i]];
					}
					
					
					if(l instanceof Array){
						var html = '';
						for (var i = 0; i < l.length; i++) {
							html+='<option value="'+l[i][p.val]+'">'+l[i][p.name]+'</option>';
						}
						
						select.append(html);
						
						if(p.initVal)select.val(val);
					}else{
						console.log(JSON.stringify(p)+":返回数据错误");
						
					}
				}
			});
			
		
	}
	
	
	window.$lform = lform;
})();

</script>

<script type="text/javascript">
function ab(v){
	return  2;
	
}


$(function(){
	
	$lform.init($("#searchForm"),{name:"123"})
	
})

</script>


</head>
<body>




    
    <div class="rightinfo">
		<div>
    		<form id="searchForm" name="searchForm"  method="post">
    		<input class="init-widget" data-vkey="name" type="text" >
    		<input class="init-widget" data-vkey="name" type="hidden" >
    		<input class="init-widget" data-vkey="status" data-format="ab" type="radio"  name="status" value="1">启用
			<input type="radio"  name="status" value="2">禁用
			<select class="init-widget" data-vkey="name">
			<option value="1">retgerg</option>
			</select>
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