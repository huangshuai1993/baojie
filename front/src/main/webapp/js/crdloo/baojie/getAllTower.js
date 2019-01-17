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
		var searchBeginTime = $("#beginTime").val();
		var searchEndTime = $("#endTime").val();
		searcher(searchName,pageNumber,searchBeginTime,searchEndTime);
		$("#myForm").submit();
	});
	
	//提交方法,(参数数值，当前页数)
	function searcher(searchName,pageNumber,searchBeginTime,searchEndTime){
		$("#pageNumber").val(pageNumber);
		$("#searchTowerName").val(searchName);
		$("#searchBeginTime").val(searchBeginTime);
		$("#searchEndTime").val(searchEndTime);
		$("#myForm").submit();
	}
	
	//导出方法
	$(".btn-csvDownload").on("click",function(){
		var pageNumber = $(this).attr("data-pagenum");
		var searchName = $("#searchName").val();
		var searchBeginTime = $("#beginTime").val();
		var searchEndTime = $("#endTime").val();
		var flag = confirm("确认导出？");
		if(flag){
			$("#pageNumber").val(pageNumber);
			$("#searchTowerName1").val(searchName);
			$("#searchBeginTime1").val(searchBeginTime);
			$("#searchEndTime1").val(searchEndTime);
			$("#csvDownLoadAllTower").submit();
		}
	});
	//查询列表
	$("#mySubmit").on("click",function(){
		var searchName = $("#searchName").val();
		var searchBeginTime = $("#beginTime").val();
		var searchEndTime = $("#endTime").val();
		searcher(searchName,1,searchBeginTime,searchEndTime);
	});
	//删除方法
	$(".btn-delete").on("click",function(){
		var id = $(this).attr("data-id");
		var flag = confirm("确定删除？");
		if(flag){
			$.post(root+"/service/btower/deleteTower",{"id":id},function(data){
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
		$.post(root+"/service/btower/getInfoTower",{"id":id},function(data){
			if(data.retCode){
				$("#updateTower").show();
				var e = data.tower;
				var towerId = $("#towerId").val(e.towerId);
				var towerName = $("#towerName").val(e.towerName);
				var functionaryName = $("#functionaryName").val(e.functionaryName);
				var address = $("#address").val(e.address);
				var peopleCount = $("#peopleCount").val(e.peopleCount);
				var virtualCount = $("#virtualCount").val(e.virtualCount);
				var approachTime = $("#approachTime").val(e.approachTime);
			}
		});
	});
	
	//添加显示员工按钮
	$(".btn-addTower").on("click",function(){
		$("#updateTower").show();
		var towerId = $("#towerId").val("");
		var towerName = $("#towerName").val("");
		var functionaryName = $("#functionaryName").val("");
		var address = $("#address").val("");
		var peopleCount = $("#peopleCount").val("");
		var virtualCount = $("#virtualCount").val("");
		var approachTime = $("#approachTime").val("");
	});
	//添加合同
	$("#saveTower").on("click",function(){
		var towerId = $("#towerId").val();
		var towerName = $("#towerName").val();
		var functionaryName = $("#functionaryName").val();
		var address = $("#address").val();
		var peopleCount = $("#peopleCount").val();
		var virtualCount = $("#virtualCount").val();
		var approachTime = $("#approachTime").val();
		if(towerName == ''){
			alert("楼盘名称不能为空!");
			return;
		}
		if(functionaryName == ''){
			alert("负责人姓名不能为空!");
			return;
		}
		var postdata = {
				"towerId":towerId,
				"towerName":towerName,
				"functionaryName":functionaryName,
				"address":address,
				"peopleCount":peopleCount,
				"virtualCount":virtualCount,
				"approachTime":approachTime
		};
		//进行跟新操作
		$.ajax({
			type:"POST", 
            url:root+"/service/btower/addOrUpdateTower", 
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