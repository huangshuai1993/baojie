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
		searcher(searchName,pageNumber);
		$("#myForm").submit();
	});
	
	//提交方法,(参数数值，当前页数)
	function searcher(searchName,pageNumber ){
		$("#pageNumber").val(pageNumber);
		$("#searchTower").val(searchName);
		$("#myForm").submit();
	}
	
	//导出方法
	$(".btn-csvDownload").on("click",function(){
		var pageNumber = $(this).attr("data-pagenum");
		var searchName = $("#searchTowerIds").val();
		var flag = confirm("确认导出？");
		if(flag){
			$("#pageNumber").val(pageNumber);
			$("#searchTower1").val(searchName);
			$("#csvDownLoadAllPosition").submit();
		}
	});
	//查询列表
	$("#mySubmit").on("click",function(){
		var searchName = $("#searchTowerIds").val();
		searcher(searchName,1);
	});
	//删除方法
	$(".btn-delete").on("click",function(){
		var id = $(this).attr("data-id");
		var flag = confirm("确定删除？");
		if(flag){
			$.post(root+"/service/position/deletePosition",{"id":id},function(data){
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
	
	//修改员工
	$(".btn-update").on("click",function(){
		var id = $(this).attr("data-id");
		$.post(root+"/service/position/getPositionInfo",{"id":id},function(data){
			if(data.retCode){
				$("#updatePosition").show();
				var e = data.position;
				var positionId = $("#positionId").val(e.positionId);
				var positionName = $("#positionName").val(e.positionName);
				var basePay = $("#basePay").val(e.basePay);
				var allowance = $("#allowance").val(e.allowance);
				var towerId = $("#towerIds").val(e.towerId);
			}
		});
	});
	
	//添加显示员工按钮
	$(".btn-addPosition").on("click",function(){
		$("#updatePosition").show();
		var positionId = $("#positionId").val("");
		var positionName = $("#positionName").val("");
		var basePay = $("#basePay").val("");
		var allowance = $("#allowance").val("");
		var towerId = $("#towerIds").val("");
	});
	$("#savePosition").on("click",function(){
		var positionId = $("#positionId").val();
		var positionName = $("#positionName").val();
		var basePay = $("#basePay").val();
		var allowance = $("#allowance").val();
		var towerId = $("#towerIds").val();
		if(positionName == ''){
			alert("职务名称不能为空!");
			return;
		}
		if(towerId == ''){
			alert("楼盘信息不能为空!");
			return;
		}
		var postdata = {
				"positionId":positionId,
				"positionName":positionName,
				"basePay":basePay,
				"allowance":allowance,
				"towerId":towerId
		};
		//进行跟新操作
		$.ajax({
			type:"POST", 
            url:root+"/service/position/addOrUpdatePosition", 
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