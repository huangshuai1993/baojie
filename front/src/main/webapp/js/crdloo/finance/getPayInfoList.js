$(function(){
	var storeId=$("#searchStoreId").val();
	if(storeId !=0){
		storeSyncChange(storeId,'#work_id');
	}
	var statusCode=$("#statusCode").val();
	var store_Id = $("#store_id option[value='"+storeId+"']").attr("selected","selected");
	var status_Code = $("#status_code option[value='"+statusCode+"']").attr("selected","selected");
	var searchWorkId=$("#searchWorkId").val();
	var searchWorkId = $("#work_id option[value='"+searchWorkId+"']").attr("selected","selected");
	
	//分页情况
	var $ur_a = $(".pagination li a");
	$ur_a.on("click",function(){
		var pageNumber = $(this).attr("data-pagenum");
		var searchMobile = $("#searchMobile").val();
		var searchName = $("#searchName").val();
		var status_code = $("#status_code option:selected").val();
		var store_id = $("#store_id option:selected").val();
		var work_id = $("#work_id option:selected").val();
		searcher(searchMobile,searchName ,store_id ,work_id ,status_code ,pageNumber);
		$("#myForm").submit();
	});
	
	//提交方法,(参数数值，当前页数)
	function searcher(searchMobile,searchName ,store_id ,work_id ,status_code ,pageNumber){
		$("#pageNumber").val(pageNumber);
		$("#TaskName").val(searchName);
		$("#statusCode").val(status_code);
		$("#search_Mobile").val(searchMobile);
		$("#searchStoreId").val(store_id);
		$("#searchWorkId").val(work_id);
		$("#myForm").submit();
	}
	//查询列表
	$("#mySubmit").on("click",function(){
		var searchMobile = $("#searchMobile").val();
		var searchName = $("#searchName").val();
		var store_id = $("#store_id option:selected").val();
		var work_id = $("#work_id option:selected").val();
		var status_code = $("#status_code option:selected").val();
		searcher(searchMobile,searchName ,store_id ,work_id ,status_code ,1);
	});
	//填写密码
	$(".btn-disable").on("click",function(){
		$('#task-remove').modal({
			keyboard: false,
			backdrop: "static"
		});
		var id = $(this).data("id");
		$("#taskId").val(id)
	});
	//取消清除密码
	$(".btn-default").on("click",function(){
		$("#payPwd").val("");
	})
	//退款
	$("#btn-returnPayBtn").on("click",function(){
		var id = $("#taskId").val();
		var payPwd=$("#payPwd").val();
		if (id == null || id ==""){
			alert("缺少参数");
			return;
		}
		if (payPwd == null || payPwd == ""){
			alert("请输入密码");
			return;
		}
		var postdata={
				"taskId" : id,
				"payPwd" : payPwd
			}
		$.post(root+"/service/finance/payReturn",postdata,function(data){
			if(data.retCode){
				alert(data.retMsg);
				window.location.reload(true);
			}else{
				alert(data.retMsg);
				return false;
			}
		});
	})

});
function storeSyncChange(storeId,id){  
	var postdata={
			"storeId" : storeId
		}
	$.ajax({

		type: "POST",

		url: root+"/service/organize/getWorkerByStoreId",

		data:postdata,
		async:false,
		success: function(data){
			var dataList=data.workerList;
			var workList = '<option value="0">全部</option>';
			if(dataList.length>0){
				var workLists=dataList.map(function(item,index){
					var row=[
					         '<option value="',item.id,'">',item.name,'</option>'
					         ];
					return row.join("");
				}).join("");
				workList+=workLists;
			}
			$(id).html(workList);
		}
	});
}
function storeChange(storeId,id){  
	var postdata={
			"storeId" : storeId
		}
	$.post(root+"/service/organize/getWorkerByStoreId",postdata,function(data){
		var dataList=data.workerList;
		var workList = '<option value="0">全部</option>';
		if(dataList.length>0){
			var workLists=dataList.map(function(item,index){
				var row=[
				         '<option value="',item.id,'">',item.name,'</option>'
				         ];
				return row.join("");
			}).join("");
			workList+=workLists;
		}
		$(id).html(workList);
	});
} 
