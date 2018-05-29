(function(){
	var utils = {};
	
	utils.trimObj = function(obj){
		
		for ( var i in obj) {
			if(obj[i]===""||obj[i]==null)
				delete obj[i];
		}
		return obj;
		
	}
	
	
	utils.tree = function(array,op){
		
		
		if(!op)op = {};
		
		var pidname = !op.pidname?"pid":op.pidname;
		var childname = op.childname?op.childname:"childs";
		var idname = op.idname?op.idname:"id";
		
		var root = [];
		for (var i = 0; i < array.length; i++) {
			if(op.rootval==array[i][pidname])
				root.push(array[i]);
			
			
			var pid = array[i][idname];
			
			array[i].childs = getbyPid(array,pid);
		}
		
		
		
		function getbyPid(array,pid){
			
			var l = [];
			 for (var i = 0; i < array.length; i++) {
				if(array[i][pidname]==pid)
					l.push(array[i]);
			}
			 
			 return l;
		}
		
		
		
		
		
		
		return root;
		
	}
	
	
	window.$utils = utils;
	
})();