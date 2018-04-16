<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

	<c:set var="path" value="${pageContext.request.contextPath}"/>
	
	<script src="${path }/jslib/jquery.min-1.11.js"></script>
	<script src="${path }/jslib/browser.js"></script>
	
	<%-- common js class --%>
	<script type="text/javascript" src="${path}/js/foxtail/Tools.js"></script>
	<script type="text/javascript" src="${path}/js/foxtail/app.js"></script>
	<script type="text/javascript" src="${path}/js/foxtail/util.js"></script>
	<!-- 日期 -->
	<script type="text/javascript" src="${path}/jslib/My97DatePicker/4.7/WdatePicker.js"></script>
	<link href="${path}/css/layout/style.css" rel="stylesheet" type="text/css" />
	
	<link rel="stylesheet" href="${path }/jslib/table/docs/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${path }/jslib/table/dist/bootstrap-table.min.css">
    <script src="${path }/jslib/table/docs/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${path }/jslib/table/dist/bootstrap-table.js"></script>
    <script src="${path }/jslib/table/dist/locale/bootstrap-table-zh-CN.min.js"></script>
    
	<script type="text/javascript" src="${path}/jslib/jquery.form.js"></script>
	
	<script type="text/javascript" src="${path}/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery.validate.messages.js"></script>
	<script type="text/javascript" src="${path}/js/template-web.js"></script>
		
	<script type="text/javascript">
	//屏蔽readonly下按backspace键返回的功能
		$(document).keydown(function(e){
			var event=arguments.callee.caller.arguments[0]||window.event;// 修正浏览器兼容 
			var target = e.target ;
			var tag = e.target.tagName.toUpperCase();
			if(event.keyCode == 8){
				if((tag == 'INPUT' && !$(target).attr("readonly"))||(tag == 'TEXTAREA' && !$(target).attr("readonly"))){
					if((target.type.toUpperCase() == "RADIO") || (target.type.toUpperCase() == "CHECKBOX")){
						return false ;
					}else{
						return true ;
					}
				}else{
					
					return false ;
				}
			}
		}); 
		
		
		function toSubmit(){
			
			var form = $("#sub_form");
			//表单验证
			if((typeof(onSubmit) == "function")&&!onSubmit(form))
				return;
			
		 $app.loading(function(close){
				//表单提交
					$("#sub_form").ajaxSubmit({
						url:$("#sub_form").attr("data-action"),
						data : $("#sub_form").serialize(),
						cache : false,
						dataType : 'JSON',
						type:'post',
						success:function(data){
							close();
							if(data.code==1){
								$app.alert('成功',function(){  //关闭事件
									goBack();
								});
								
							}else
								$app.alert(data.msg?data.msg:'失败');
								
							
						}
					});
				 
			 });
			
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
		
		$(document).ready(function () {
			
			 if(window.validateOpts)$("#sub_form").validate(validateOpts);
		});
		
		
	</script>
