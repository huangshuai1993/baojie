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
		var searchTowerIds = $("#searchTowerIds").val();
		var timeValue = $("#timeValue").val();
		searcher(searchTowerIds,timeValue,pageNumber);
		$("#myForm").submit();
	});
	
	//提交方法,(参数数值，当前页数)
	function searcher(searchTowerIds,timeValue,pageNumber ){
		$("#pageNumber").val(pageNumber);
		$("#searchTower").val(searchTowerIds);
		$("#time").val(timeValue);
		$("#myForm").submit();
	}
	
	//导出方法
	$(".btn-csvDownload").on("click",function(){
		var pageNumber = $(this).attr("data-pagenum");
		var searchTowerIds = $("#searchTowerIds").val();
		var timeValue = $("#timeValue").val();
		var flag = confirm("确认导出？");
		if(flag){
			$("#pageNumber").val(pageNumber);
			$("#searchTower").val(searchTowerIds);
			$("#time").val(timeValue);
			$("#csvDownLoadAllSalary").submit();
		}
	});
	//查询列表
	$("#mySubmit").on("click",function(){
		var searchTowerIds = $("#searchTowerIds").val();
		var timeValue = $("#timeValue").val();
		searcher(searchTowerIds,timeValue,1);
	});
	//删除方法
	$(".btn-delete").on("click",function(){
		var id = $(this).attr("data-id");
		var flag = confirm("确定删除？");
		if(flag){
			$.post(root+"/service/salary/deleteSalary",{"id":id},function(data){
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
		$.post(root+"/service/salary/getSalaryInfo",{"id":id},function(data){
			if(data.retCode){
				$("#updateSalary").show();
				var e = data.salary;
				var id = $("#salaryId").val(e.id);
				var salaryMonth = $("#salaryMonth").val(e.salaryMonth);
				var staffName = $("#staffName").val(e.staffName);
				var positionName = $("#positionName").val(e.positionName);
				var basePay = $("#basePay").val(e.basePay);
				var allowance = $("#allowance").val(e.allowance);
				var workDay = $("#workDay").val(e.workDay);
				var overtimePay = $("#overtimePay").val(e.overtimePay);
				var holiday = $("#holiday").val(e.holiday);
				var other = $("#other").val(e.other);
				var sendPay = $("#sendPay").val(e.sendPay);
				var personTax = $("#personTax").val(e.personTax);
				var socialSecurity = $("#socialSecurity").val(e.socialSecurity);
				var askForLeave = $("#askForLeave").val(e.askForLeave);
				var otherDeductPay = $("#otherDeductPay").val(e.otherDeductPay);
				var deductTotalPay = $("#deductTotalPay").val(e.deductTotalPay);
				var realPay = $("#realPay").val(e.realPay);
			}
		});
	});
	
	//添加工资按钮
	$(".btn-addSalary").on("click",function(){
		var flag = confirm("请确认楼盘信息及职务信息是否需要改动？每个月只可生成一次工资信息！");
		$.post(root+"/service/salary/addSalaryMonth",{},function(data){
			if(data.retCode){
				alert(data.retMsg);
				window.location.reload(true);
			}else{
				alert(data.retMsg);
				return false;
			}
		});
	});
	//保存修改
	$("#saveSalary").on("click",function(){
		var id = $("#salaryId").val();
		var salaryMonth = $("#salaryMonth").val();
		var staffName = $("#staffName").val();
		var positionName = $("#positionName").val();
		var basePay = $("#basePay").val();
		var allowance = $("#allowance").val();
		var workDay = $("#workDay").val();
		var overtimePay = $("#overtimePay").val();
		var holiday = $("#holiday").val();
		var other = $("#other").val();
		var sendPay = $("#sendPay").val();
		var personTax = $("#personTax").val();
		var socialSecurity = $("#socialSecurity").val();
		var askForLeave = $("#askForLeave").val();
		var otherDeductPay = $("#otherDeductPay").val();
		var deductTotalPay = $("#deductTotalPay").val();
		var realPay = $("#realPay").val();
		if(basePay == ''){
			alert("基本工资不能为空!");
			return;
		}
		if(workDay == ''){
			alert("出勤次数不能为空!");
			return;
		}
		if(allowance == ''){
			alert("岗位津贴不能为空!");
			return;
		}
		var postdata = {
				id:"salaryId",
				salaryMonth:"salaryMonth",
				staffName:"staffName",
				positionName:"positionName",
				basePay:"basePay",
				allowance:"allowance",
				workDay:"workDay",
				overtimePay:"overtimePay",
				holiday:"holiday",
				other:"other",
				sendPay:"sendPay",
				personTax:"personTax",
				socialSecurity:"socialSecurity",
				askForLeave:"askForLeave",
				otherDeductPay:"otherDeductPay",
				deductTotalPay:"deductTotalPay",
				realPay:"realPay"
		};
		//进行跟新操作
		$.ajax({
			type:"POST", 
            url:root+"/service/salary/updateStaffSalary", 
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