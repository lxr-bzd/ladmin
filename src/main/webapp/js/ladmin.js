var $ladmin = {mainTable:"#mainTable"};

$ladmin.toedit = function(id,op){
	if(!op)op = {};
	var url = op.url?op.url:this.getMainUrl()+"_edit.html?id="+id;
	var cop;
	if(op.width||op.heigth)cop = {width:op.width,heigth:op.heigth};
	$app.dialog(url,function(){
		$ladmin.refTable();
	},cop);
	
}

$ladmin.refTable = function(){
    	$(this.mainTable).bootstrapTable('refresh');
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