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
		searcher(pageNumber);
		$("#myForm").submit();
	});
	
	//提交方法,(参数数值，当前页数)
	function searcher(pageNumber ){
		$("#pageNumber").val(pageNumber);
		$("#myForm").submit();
	}
	
	//删除方法
	$(".btn-delete").on("click",function(){
		var custNo = $(this).attr("data-custno");
		var flag = confirm("确定删除？");
		if(flag){
			$.post(root+"/service/employee/deleteEmployee",{"custNo":custNo},function(data){
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
		$("#employeePassword").hide();
		$(".storeIdDiv").hide();
		var custNo = $(this).attr("data-custno");
		$.post(root+"/service/employee/getEmployeeInfo",{"custNo":custNo},function(data){
			if(data.retCode){
				$("#updateEmployee").show();
				var e = data.employee;
				var username = $("#username").val(e.username);
				var realName = $("#realName").val(e.realName);
				var employIDCardNum = $("#employIDCardNum").val(e.employIDCardNum);
				var phone = $("#phone").val(e.phone);
				var personaId = $("#personaId").val(data.persona.personaId);
				var employeeId = $("#employeeId").val(e.employeeId);
				var updateCustNo = $("#updateCustNo").val(e.custNo);
				var personaId = $("#ddlRegType").val(data.persona.personaId);
				if(data.persona.personaId=="2"){
					$(".storeIdDiv").show();
					$("#storeIds").val(e.storeId);
				}
			}
		});
	});
	
	//添加显示员工按钮
	$(".btn-addEmployee").on("click",function(){
		$("#updateEmployee").show();
		$("#employeePassword").show();
		$(".storeIdDiv").hide();
		var username = $("#username").val("");
		var realName = $("#realName").val("");
		var employIDCardNum = $("#employIDCardNum").val("");
		var phone = $("#phone").val("");
		var personaId = $("#personaId").val("");
		var employeeId = $("#employeeId").val("");
		
	});
	//添加员工
	$("#saveEmployee").on("click",function(){
		var custNo = $("#updateCustNo").val();
		var employeeId = $("#employeeId").val();
		var username = $("#username").val();
		var realName = $("#realName").val();
		var employIDCardNum = $("#employIDCardNum").val();
		var phone = $("#phone").val();
		var personaId = $("#ddlRegType").val();
		var password = $("#password").val();
		var storeId = $("#storeIds").val();
		if (personaId != 2){
			storeId = "";
		}
		if(username =='' && username==""){
			alert("登录名不能为空");
			return;
		}
		if(realName ==""){
			alert("姓名不能为空");
			return;
		}
		if(!isNumAndletter(employIDCardNum)||employIDCardNum.length>18){
			alert("身份证号格式不对");
			return ;
		}
		if(!isPositiveNum(phone)){
			alert("手机号格式不对,请重新填写");
			return false;
		}
		var postdata = {
				"custNo":custNo,
				"username":username,
				"realName":realName,
				"employIDCardNum":employIDCardNum,
				"phone":phone,
				"employeeId":employeeId,
				"personaId":personaId,
				"password":password,
				"storeId":storeId
		};
		if(employeeId!=null && ''!=employeeId){
			//进行跟新操作
			$.post(root+"/service/employee/updateEmployee",postdata,function(data){
				if(data.retCode){
					alert(data.retMsg);
					window.location.reload(true);
				}else{
					alert(data.retMsg);
					return false;
				}
			});
		}else{
			if(password=='' && password=="") {
				alert("密码不能为空");
				return;
			}
			//进行添加操做
			$.post(root+"/service/employee/addEmployee",postdata,function(data){
				if(data.retCode){
					alert(data.retMsg);
					window.location.reload(true);
				}else{
					alert(data.retMsg);
					return false;
				}
			});
		}
	});
	
	//启用禁用
	$(".btn-disable").on("click",function(){
		var custNo = $(this).attr("data-custno");
		var state = $(this).attr("data-state");
		$.post(root+"/service/employee/updateEmpState",{"custNo":custNo,"empStatus":state},function(data){
			if(data.retCode){
				alert("修改成功!");
				window.location.reload(true);
			}else{
				alert("修改失败!");
				return false;
			}
		});
	});
	
	//显示修改密码
	$(".btn-password").on("click",function(){
		var custNo = $(this).attr("data-custno");
		$("#modifyPassword").show();
		$("#custNo").val(custNo);
	});
	//更改密码
	$("#updatePassword").on("click",function(){
		var password = $("#newPassword").val();
		var repassword = $("#rePassword").val();
		var custNo = $("#custNo").val();
		$.post(root+"/service/employee/updatePassword",{"password":password,"repassword":repassword,"custNo":custNo},function(data){
			if(data.retCode){
				alert("更改成功!");
				window.location.reload(true);
			}else{
				alert(data.retMsg);
				return false;
			}
		});
	});
});