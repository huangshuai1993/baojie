$(function(){
	
	var towerId=$("#searchTower").val();
	var searchTowerId = $("#searchTowerIds option[value='"+towerId+"']").attr("selected","selected");
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
		var searchName = $("#searchTowerIds").val();
		var searchBeginTime = $("#beginTime").val();
		var searchEndTime = $("#endTime").val();
		searcher(searchName,pageNumber,searchBeginTime,searchEndTime);
		$("#myForm").submit();
	});
	
	//提交方法,(参数数值，当前页数)
	function searcher(searchName,pageNumber,searchBeginTime,searchEndTime ){
		$("#pageNumber").val(pageNumber);
		$("#searchTower").val(searchName);
		$("#searchBeginTime").val(searchBeginTime);
		$("#searchEndTime").val(searchEndTime);
		$("#myForm").submit();
	}
	//查询列表
	$("#mySubmit").on("click",function(){
		var searchName = $("#searchTowerIds").val();
		var searchBeginTime = $("#beginTime").val();
		var searchEndTime = $("#endTime").val();
		searcher(searchName,1,searchBeginTime,searchEndTime);
	});
	//删除方法
	$(".btn-delete").on("click",function(){
		var id = $(this).attr("data-id");
		var flag = confirm("确定删除？");
		if(flag){
			$.post(root+"/service/material/deleteMaterial",{"id":id},function(data){
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
	
	//导出方法
	$(".btn-csvDownload").on("click",function(){
		var pageNumber = $(this).attr("data-pagenum");
		var searchName = $("#searchTowerIds").val();
		var searchBeginTime = $("#beginTime").val();
		var searchEndTime = $("#endTime").val();
		var flag = confirm("确认导出？");
		if(flag){
			$("#pageNumber1").val(pageNumber);
			$("#searchTower1").val(searchName);
			$("#searchBeginTime1").val(searchBeginTime);
			$("#searchEndTime1").val(searchEndTime);
			$("#csvDownLoadAllMaterial").submit();
		}
	});
	
	//修改员工
	$(".btn-update").on("click",function(){
		var id = $(this).attr("data-id");
		$.post(root+"/service/material/getMaterialInfo",{"id":id},function(data){
			if(data.retCode){
				$("#updateMaterial").show();
				var e = data.material;
				var materialId = $("#materialId").val(e.materialId);
				var materialName = $("#materialName").val(e.materialName);
				var towerId = $("#towerIds").val(e.towerId);
				var type = $("#type").val(e.type);
				var price = $("#price").val(e.price);
				var count = $("#count").val(e.count);
				var totalPrice = $("#totalPrice").val(e.totalPrice);
				var purchaseTime = $("#purchaseTime").val(e.purchaseTime);
			}
		});
	});
	
	//添加显示物料按钮
	$(".btn-addMaterial").on("click",function(){
		$("#updateMaterial").show();
		var materialId = $("#materialId").val("");
		var materialName = $("#materialName").val("");
		var towerId = $("#towerIds").val("");
		var type = $("#type").val("");
		var price = $("#price").val("");
		var count = $("#count").val("");
		var totalPrice = $("#totalPrice").val("");
		var purchaseTime = $("#purchaseTime").val("");
	});
	$("#saveMaterial").on("click",function(){
		var materialId = $("#materialId").val();
		var materialName = $("#materialName").val();
		var towerId = $("#towerIds").val();
		var type = $("#type").val();
		var price = $("#price").val();
		var count = $("#count").val();
		var totalPrice = $("#totalPrice").val();
		var purchaseTime = $("#purchaseTime").val();
		if(materialName == ''){
			alert("物料名称不能为空!");
			return;
		}
		if(towerId == ''){
			alert("楼盘信息不能为空!");
			return;
		}
		var postdata = {
				"materialId":materialId,
				"materialName":materialName,
				"towerId":towerId,
				"type":type,
				"price":price,
				"count":count,
				"totalPrice":totalPrice,
				"purchaseTime":purchaseTime
		};
		//进行跟新操作
		$.ajax({
			type:"POST", 
            url:root+"/service/material/addOrUpdateMaterial", 
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