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
		$("#searchContractName").val(searchName);
		$("#myForm").submit();
	}
	//查询列表
	$("#mySubmit").on("click",function(){
		var searchName = $("#searchName").val();
		searcher(searchName,1);
	});
	//删除方法
	$(".btn-delete").on("click",function(){
		var contractId = $(this).attr("data-contractId");
		var flag = confirm("确定删除？");
		if(flag){
			$.post(root+"/service/bcontract/deleteContract",{"id":contractId},function(data){
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
		var contractId = $(this).attr("data-contractId");
		$.post(root+"/service/bcontract/getContractInfo",{"id":contractId},function(data){
			if(data.retCode){
				$("#updateContract").show();
				var e = data.contract;
				var contractId = $("#contractId").val(e.id);
				var contractName = $("#contractName").val(e.contractName);
				var detailType = $("#detailType").val(e.detailType);
				var towerName = $("#towerName").val(e.towerName);
				var company = $("#company").val(e.company);
				var serviceCompany = $("#serviceCompany").val(e.serviceCompany);
				var commencementDate = $("#commencementDate").val(e.commencementDate);
				var terminationDate = $("#terminationDate").val(e.terminationDate);
				var totalPrices = $("#totalPrices").val(e.totalPrices);
				var monthPrices = $("#monthPrices").val(e.monthPrices);
				var paidPrices = $("#paidPrices").val(e.paidPrices);
				var balance = $("#balance").val(e.balance);
				var peopleCount = $("#peopleCount").val(e.peopleCount);
				var copies = $("#copies").val(e.copies);
				var transactor = $("#transactor").val(e.transactor);
				var contact = $("#contact").val(e.contact);
				var status = $("#status").val(e.status);
				var department = $("#department").val(e.department);// department
			}
		});
	});
	
	//添加显示员工按钮
	$(".btn-addContract").on("click",function(){
		$("#updateContract").show();
		var contractId = $("#contractId").val("");
		var contractName = $("#contractName").val("");
		//var detailType = $("#detailType").val("");
		var towerName = $("#towerName").val();
		var company = $("#company").val();
		var serviceCompany = $("#serviceCompany").val("");
		var commencementDate = $("#commencementDate").val("");
		var terminationDate = $("#terminationDate").val("");
		var totalPrices = $("#totalPrices").val("");
		var monthPrices = $("#monthPrices").val("");
		var paidPrices = $("#paidPrices").val("");
		var balance = $("#balance").val("");
		var peopleCount = $("#peopleCount").val("");
		var copies = $("#copies").val("");
		var transactor = $("#transactor").val("");
		var contact = $("#contact").val("");
		var department = $("#department").val("");
		//var status = $("#status").val("0");
		//var type = $("#type").val("");  department
	});
	//添加合同
	$("#saveContract").on("click",function(){
		var contractId = $("#contractId").val();
		var contractName = $("#contractName").val();
		var detailType = $("#detailType").val();
		var towerName = $("#towerName").val();
		var company = $("#company").val();
		var serviceCompany = $("#serviceCompany").val();
		var commencementDate = $("#commencementDate").val();
		var terminationDate = $("#terminationDate").val();
		var totalPrices = $("#totalPrices").val();
		var monthPrices = $("#monthPrices").val();
		var paidPrices = $("#paidPrices").val();
		var balance = $("#balance").val();
		var peopleCount = $("#peopleCount").val();
		var copies = $("#copies").val();
		var transactor = $("#transactor").val();
		var contact = $("#contact").val();
		var status = $("#status").val();
		var type = $("#type").val();
		var department = $("#department").val();
		if(contractName == ''){
			alert("合同名称不能为空!");
			return;
		}
		if(company == '' || serviceCompany == ''){
			alert("双方公司不能为空!");
			return;
		}
		var postdata = {
				"id":contractId,
				"contractName":contractName,
				"detailType":detailType,
				"towerName":towerName,
				"company":company,
				"serviceCompany":serviceCompany,
				"commencementDate":commencementDate,
				"terminationDate":terminationDate,
				"totalPrices":totalPrices,
				"monthPrices":monthPrices,
				"paidPrices":paidPrices,
				"balance":balance,
				"peopleCount":peopleCount,
				"copies":copies,
				"department":department,
				"transactor":transactor,
				"contact":contact,
				"status":status,
				"type":type
		};
		//进行跟新操作
		$.ajax({
			type:"POST", 
            url:root+"/service/bcontract/addOrUpdateContract", 
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