var $ladmin = {mainTable:"mainTable"};
console.log("this is ladmin.js");
$ladmin.toedit = function(id,op){
	if(!op)op = {};
	var url = op.url?op.url:this.getMainUrl()+"_edit.html?id="+id;
	var cop;
	if(op.width||op.heigth)cop = {width:op.width,heigth:op.heigth};
	$app.dialog(url,function(){
		$ladmin.refTable();
	},cop);
	
}

$ladmin.toadd = function(op){
	if(!op)op = {};
	var url = op.url?op.url:this.getMainUrl()+"_add.html";
	var cop;
	if(op.width||op.heigth)cop = {width:op.width,heigth:op.heigth};
	$app.dialog(url,function(){
		$ladmin.refTable();
	},cop);
	
}


//删除
$ladmin.todel = function(ids){

	if(!ids)
	ids=getSelectedRowsIds(this.mainTable);
	if(ids){
		$app.confirm("确定要删除吗？",function(){
			 $.post($ladmin.getMainUrl()+'/delete.do?ids='+ids,function(data){

				    if(data.code==1){
				    	$app.alert("删除成功",function(){
				    		$ladmin.refTable();
				    	});
	 					
				    }else $app.alert(json.msg?json.msg:'失败');
				    	
				   
				 },"json");
			
		});
		
		
	}else  $app.alert("请选择一条数据进行操作");
	
}

$ladmin.refTable = function(){
    	$("#"+this.mainTable).bootstrapTable('refresh');
}

$ladmin.getMainUrl = function(){
	
	var strPage=window.location.href;
	if(strPage.indexOf(".html")<0)return strPage;
	return strPage.substring(0,strPage.indexOf(".html"));
}


$ladmin.goBack = function(){
	
	try {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	} catch (e) {
		if(isNaN(index))history.back();
	}
}


$ladmin.toSubmit = function(){
	
	var form = $("#sub_form");
	//表单验证
	if((typeof(onSubmit) == "function")&&!onSubmit(form))
		return;
	
 $app.loading(function(close){
		//表单提交
			$("#sub_form").ajaxSubmit({
				url:$("#sub_form").attr("data-action"),
				cache : false,
				dataType : 'JSON',
				type:'post',
				success:function(json){
					if(typeof(data)=="string")
						data = $.parseJSON(data.replace(/<.*?>/ig,""));
					close();
					if(json.code==1){
						$app.alert('成功',function(){ 
							$ladmin.goBack();
						});
						
					}else
						$app.alert(json.msg?json.msg:'失败');
						
					
				}
			});
		 
	 });
	
}