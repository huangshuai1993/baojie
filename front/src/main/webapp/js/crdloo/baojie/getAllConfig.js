$(function(){
	function isPositiveNum(s){//是否为正整数  
	    var re = /^[0-9]*[1-9][0-9]*$/ ;  
	    return re.test(s)  
	} 
	function isNumAndletter(s){//数字和字母
		var re = /^[A-Za-z0-9]+$/; 
		return re.test(s)
	}
	//分页情况
	var $ur_a = $(".pagination li a");
	$ur_a.on("click",function(){
		var pageNumber = $(this).attr("data-pagenum");
		var searchName = $("#searchName").val();
		searcher(searchName,pageNumber);
		$("#myForm").submit();
	});
	
	//提交方法,(参数数值，当前页数)
	function searcher(searchName,pageNumber ){
		$("#pageNumber").val(pageNumber);
		$("#describe").val(searchName);
		$("#myForm").submit();
	}
	
	//查询列表
	$("#mySubmit").on("click",function(){
		var searchName = $("#searchName").val();
		searcher(searchName,1);
	});
	//删除方法
	$(".btn-delete").on("click",function(){
		var id = $(this).attr("data-id");
		var flag = confirm("确定删除？");
		if(flag){
			$.post(root+"/service/config/deleteConfig",{"id":id},function(data){
				if(data.retCode){
					alert("删除成功!");
					window.location.reload(true);
				}else{
					alert("删除失败!");
					return false;
				}
			});
		}
	});
	
	//修改
	$(".btn-update").on("click",function(){
		var id = $(this).attr("data-id");
		$.post(root+"/service/config/getInfoConfig",{"id":id},function(data){
			if(data.retCode){
				$("#updateConfig").show();
				var e = data.config;
				var id = $("#id").val(e.id);
				var configuration = $("#configuration").val(e.configuration);
				var describe = $("#describe").val(e.describe);
				var memo = $("#memo").val(e.memo);
			}
		});
	});
	
	//添加显示按钮
	$(".btn-addConfig").on("click",function(){
		$("#updateConfig").show();
		var id = $("#id").val("");
		var configuration = $("#configuration").val("");
		var describe = $("#describe").val("");
		var memo = $("#memo").val("");
	});
	//添加
	$("#saveConfig").on("click",function(){
		var id = $("#id").val();
		var configuration = $("#configuration").val();
		var describe = $("#describe").val();
		var memo = $("#memo").val();
		if(configuration == ''){
			alert("配置项名称不能为空!");
			return;
		}
		if(describe == ''){
			alert("配置项描述不能为空!");
			return;
		}
		var postdata = {
				"id":id,
				"configuration":configuration,
				"describe":describe,
				"memo":memo
		};
		//进行跟新操作
		$.ajax({
			type:"POST", 
            url:root+"/service/config/addOrUpdateConfig", 
            contentType:"application/json",  //发送信息至服务器时内容编码类型。 
            data:JSON.stringify(postdata),
            success:function(data){
    			if(data.retCode){
    				alert(data.retMsg);
    				window.location.reload(true);
    			}else{
    				alert(data.retMsg);
    				return false;
    			}
    		},
    		error:function(data){
				alert("请求失败");
				return false;
    		}
		});
	});
	
});