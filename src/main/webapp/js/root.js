(function(){
	
	var scripts = [
		'/jslib/browser.js',
		'/js/foxtail/Tools.js',
		'/js/foxtail/app.js',
		'/js/foxtail/util.js',
		'/js/ladmin.js',
		'/js/lform.js',
		
		'/jslib/My97DatePicker/4.7/WdatePicker.js',
		'/jslib/table/docs/assets/bootstrap/js/bootstrap.min.js',
		'/jslib/table/dist/bootstrap-table.js',
		'/jslib/table/dist/locale/bootstrap-table-zh-CN.min.js',
		
		'/js/foxtail/tableCommon.js',
		'/jslib/jquery.form.js',
		'/js/jquery.validate.min.js',
		'/js/jquery.validate.messages.js',
		'/js/template-web.js',
		'/jslib/layer/layer.js'
		];
	
	var links = [
		'/css/layout/style.css',
		'/jslib/table/docs/assets/bootstrap/css/bootstrap.min.css',
		'/jslib/table/dist/bootstrap-table.min.css'
		
		];
	
	var rootPath = "/ladmin"
	$('head').append('<base href="'+getRootPath()+rootPath+'/"/>');
	
	
	
	var html = '';
	for (var i = 0; i < scripts.length; i++) {
		html+='<script src="'+rootPath+scripts[i]+'" type="text/javascript" ></script>'
		
	}
	
	$('head').append(html);
	
	html = '';
	for (var i = 0; i < links.length; i++) {
		html+='<link rel="stylesheet" href="'+rootPath+links[i]+'">'
		
	}
	
	$('head').append(html);
	
	function getRootPath(){
		if (window["context"] == undefined) {
		    if (!window.location.origin) {
		        return window.location.protocol + "//" + window.location.hostname + (window.location.port ? ':' + window.location.port: '');
		    }
		    
		}
		
		return window.location.origin;
		
	}
	
	
	
	
	
})();


function getParentPath(){
	
	var i = window.location.href.lastIndexOf("/");
	
	return window.location.href.substring(0,i);
}

