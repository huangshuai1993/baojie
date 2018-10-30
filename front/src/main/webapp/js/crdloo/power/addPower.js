$(function(){
	//添加菜单
	$("#saveEmployeeTwo").on("click",function(){
		var powerUrl = $("#powerUrl").val();
		var powerName = $("#powerName").val();
		var menuName = $("#menuName").val();
		var powerId = $("#powerIds").val();
		if (powerUrl == "" || powerName == "" || menuName == "" || powerId == ""){
			alert("不能为空");
			return;
		}
		var postdata = {
				"powerUrl":powerUrl,
				"powerName":powerName,
				"menuName":menuName,
				"powerId":powerId
		};
		$.post(root+"/service/persona/addPowerInfoTwo",postdata,function(data){
			if(data.retCode){
				alert(data.retMsg);
				window.location.reload(true);
			}else{
				alert("添加失败!");
				return false;
			}
		});
		
	});
	
	$("#saveEmployee").on("click",function(){
		//var powerName = $("#powerNameOne").val();
		var menuName = $("#menuNameOne").val();
		if (menuName == ""){
			alert("不能为空");
			return;
		}
		var postdata = {
				"powerName":"icon-home",
				"menuName":menuName,
		};
		$.post(root+"/service/persona/addPowerInfo",postdata,function(data){
			if(data.retCode){
				alert(data.retMsg);
				window.location.reload(true);
			}else{
				alert("添加失败!");
				return false;
			}
		});
		
	});

});