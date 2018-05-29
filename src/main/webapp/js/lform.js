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