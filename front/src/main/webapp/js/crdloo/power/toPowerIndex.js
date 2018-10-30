$(function(){
	//选择当前列
	var length = $("#allPersonas .juese-ui-cur").length;
	if(length<=0){
		var l = $("#allPersonas .juese-ui").length;
		if(l>=1){
			$("#allPersonas .juese-ui:eq(0)").addClass("juese-ui-cur");
		}
	};
	
	//当前列切换
	$("#allPersonas .juese-ui").on("click",function(){
		var personaId = $(this).attr("data-personaid");
		$("#allPersonas .juese-ui").removeClass("juese-ui-cur");
		$(this).addClass("juese-ui-cur");
		window.location.href=root+"/service/persona/getPersonaDetails?personaId="+personaId;
		
	});
	//保存按钮
	$("#savePersona").on("click",function(){
		var hasSelecteds = $(".current");
		var personId = $("#personId").val();
		var powerIds = [];
		var personaName = $("#personaName").val();
		var personaDesc = $("#personaDesc").val();
		if(personaName==''){
			alert("角色名称不能为空！");
			return;
		}
		 $.each(hasSelecteds,function(i,n){
		 		powerIds.push($(n).attr("data-powerid"));
		 		console.info(powerIds);
		 });
		var postdata = {
				"personId":personId,
				"powerIds":powerIds,
				"personaName":personaName,
				"personaDesc":personaDesc,
		};
		$.post(root+"/service/persona/savaOrUpdatePersona",postdata,function(data){
			if(data.retCode){
				alert("成功!");
				$("#personId").val("");
				$("#personaName").val("");
				$("#personaDesc").val("");
				window.location.href = root+"/service/persona/getPersonaDetails";
			}else{
				alert(data.retMsg);
				return false;
			}
		});
	});
	
	//选择按钮
	$("input[type='checkbox']").on("click",function(){
		$(this).toggleClass("current");
	});
});