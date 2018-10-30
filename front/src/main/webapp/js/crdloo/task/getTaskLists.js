$(function(){
	var statusCode=$("#statusCode").val();
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
	//分配任务
	$(".btn-distribution").on("click",function(){
		$('#task-distribution').modal({
			keyboard: false,
			backdrop: "static"
		});
		var id = $(this).data("id");
		$("#taskId_distribution").val(id)
	});
	$("#distributionTask").on("click",function(){
		var id = $("#taskId_distribution").val();
		var storeId = $("#storeId_modal option:selected").val();
		var empId = $("#empId_modal option:selected").val();
		if(storeId == 0){
			alert("请选择门店");
			return;
		}
		$.post(root+"/service/btask/allocatingTask",{"id":id,"storeId":storeId,"empId":empId},function(data){
			if(data.retCode){
				alert("分配成功!");
				window.location.reload(true);
			}else{
				alert("分配失败!");
				return false;
			}
		});
	});
	//放弃
	$(".btn-disable").on("click",function(){
		$('#task-remove').modal({
			keyboard: false,
			backdrop: "static"
		});
		var id = $(this).data("id");
		$("#taskId").val(id)
	});
	$("#removeTask").on("click",function(){
		var id = $("#taskId").val();
		var memoVal= $("#memo").val();
		$.post(root+"/service/btask/removeTask",{"id":id,"memo":memoVal},function(data){
			if(data.retCode){
				alert("已放弃任务!");
				window.location.reload(true);
			}else{
				alert("放弃失败!");
				return false;
			}
		});
	});
	//添加显示任务按钮
	$(".btn-addTask").on("click",function(){
		$("#addTask").show();
		$(window).scrollTop(350);
		var name = $("#name").val("");
		var idcardNo = $("#idcardNo").val("");
		var birthday = $("#birthday").val("");
		var mobile = $("#mobile").val("");
		var income = $("#income").val("");
		var amount = $("#amount").val("");
	});
	//添加任务
	$("#saveTask").on("click",function(){
		var name = $("#name").val();
		var idcardNo = $("#idcardNo").val();
		var birthday = $("#birthday").val();
		var gender = $("input[name='gender']:checked").val();
		var mobile = $("#mobile").val();
		var marriage = $("input[name='marriage']:checked").val();
		var income = $("#income").val();
		var hasCautioner = $("input[name='hasCautioner']:checked").val();
		var cautionerName = $("#cautionerName").val();
		var cautionerIdcardNo = $("#cautionerIdcardNo").val();
		var cautionerRelation = $("#cautionerRelation").val();
		var homeProvinceCode = $("#homeProvinceCode option:selected").val();
		var homeCityCode = $("#homeCityCode option:selected").val();
		var homeRegionCode = $("#homeRegionCode option:selected").val();
		var homeAddress = $("#homeAddress").val();
		var workProvinceCode = $("#workProvinceCode option:selected").val();
		var workCityCode = $("#workCityCode option:selected").val();
		var workRegionCode = $("#workRegionCode option:selected").val();
		var workAddress = $("#workAddress").val();
		var product = $("#product option:selected").val();
		var amount = $("#amount").val();
		var source = $("#source option:selected").val();
		var storeId = $("#storeId option:selected").val();
		var empId = $("#empId option:selected").val();
		if(name =='' && name==""){
			alert("员工不能为空");
			return;
		}
		if (trim(idcardNo).length > 0) {
            if (!checkIdcard(idcardNo)) {
                alert("输入的身份证号格式错误");
                return;
            }
         }
		if(mobile=="" || !(/^1[34578]\d{9}$/.test(mobile))){ 
	        alert("手机号码有误，请重填");  
	        return ; 
	    } 
		if(marriage ==''){
			alert("请选择婚姻状况");
			return;
		}
		if(income ==''){
			alert("月收入不能为空");
			return;
		}
		if(hasCautioner =='' || hasCautioner == undefined ){
			alert("请选择是否有担保人");
			return;
		}else if(hasCautioner == 1){
			if(cautionerName == "" || cautionerIdcardNo=="" || cautionerRelation==""){
				alert("请输入担保人信息");
				return;
			}else{
				if (!checkIdcard(cautionerIdcardNo)) {
	                alert("输入的担保人身份证号格式错误");
	                return;
	            }
			}
		}
		if(homeProvinceCode =="" || homeCityCode=="" || homeAddress==""){
			alert("居住地址不能为空");
			return;
		}
		if(workProvinceCode =="" || workCityCode=="" || workAddress==""){
			alert("工作地址不能为空");
			return;
		}
		if(amount ==""){
			alert("借款金额不能为空");
			return;
		}
		var postdata = {
				"name":name,
				"idcardNo":idcardNo,
				"birthday":birthday,
				"gender":gender,
				"mobile":mobile,
				"marriage":marriage,
				"income":income,
				"hasCautioner":hasCautioner,
				"cautionerName":cautionerName,
				"cautionerIdcardNo":cautionerIdcardNo,
				"cautionerRelation":cautionerRelation,
				"homeProvinceCode":homeProvinceCode,
				"homeCityCode":homeCityCode,
				"homeRegionCode":homeRegionCode,
				"homeAddress":homeAddress,
				"workProvinceCode":workProvinceCode,
				"workCityCode":workCityCode,
				"workRegionCode":workRegionCode,
				"workAddress":workAddress,
				"product":product,
				"amount":amount,
				"source":source,
				"storeId":storeId,
				"empId":empId
		};
		//进行添加操做
		$.post(root+"/service/btask/addTask",postdata,function(data){
			if(data.retCode){
				alert(data.retMsg);
				window.location.reload(true);
			}else{
				alert(data.retMsg);
				return false;
			}
		});
	});
	
	   $("#idcardNo").blur(function () {
           var idNumber = $("#idcardNo").val();
           if (trim(idNumber).length > 0) {
               if (!checkIdcard(idNumber)) {
                   alert("输入的身份证号格式错误");
                   return;
               }
               else {
                   var birthday = getBirthdatByIdNo(idNumber);
                   var sex = getSexByIdNo(idNumber);
                   $("#birthday").val(birthday);
                   var gender=$("input[name='gender'][value='"+sex+"']").prop("checked","checked");
               }
           }
       })
	
	//
	 function trim(s) { return s.replace(/^\s+|\s+$/g, ""); };
     //获取出生日期
     function getBirthdatByIdNo(iIdNo) {
         var tmpStr = "";
         var strReturn = "";

         if (iIdNo.length == 15) {
             tmpStr = iIdNo.substring(6, 12);
             tmpStr = "19" + tmpStr;
             tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)
             return tmpStr;
         }
         else {
             tmpStr = iIdNo.substring(6, 14);
             tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)
             return tmpStr;
         }
     }
     function getSexByIdNo(iIdNo) {
         var tmpStr = "";
         var man=1;
         var woman=0;

         if (iIdNo.length == 15) {
             tmpStr = idCard.substring(14, 15);
             if(tmpStr%2==0){
            	 return woman;
             }
             return man;         
         }
         else {
             tmpStr = iIdNo.substring(16,17);
             if(tmpStr%2==0){
            	 return woman;
             }
             return man;  
         }
     }
     function checkIdcard(num) {
    	 // !(/(^\d{15}$)|(^\d{17}([0-9]|X)$)  简单正则
    	
         num = num.toUpperCase();  
         if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
             return false;
         }
         var len, re;
         len = num.length;
         if (len == 15) {
             re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
             //^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/ 15 位   
             var arrSplit = num.match(re);

             //检查生日日期是否正确 
             var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
             var bGoodDay;
             bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
             if (!bGoodDay) { 
                 return false;
             }
             else {
                 var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                 var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                 var nTemp = 0, i;
                 num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
                 for (i = 0; i < 17; i++) {
                     nTemp += num.substr(i, 1) * arrInt[i];
                 }
                 num += arrCh[nTemp % 11];
                 return true;
             }
         }
         if (len == 18) {
        	 //   /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/  18
             re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
             arrSplit = num.match(re);

             //检查生日日期是否正确 
             dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
             bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
             if (!bGoodDay) {
                 return false;
             }
             else { 
                 var valnum;
                 arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                 arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                 nTemp = 0, i;
                 for (i = 0; i < 17; i++) {
                     nTemp += num.substr(i, 1) * arrInt[i];
                 }
                 valnum = arrCh[nTemp % 11];
                 if (valnum != num.substr(17, 1)) {
                     //alert('18位身份证的校验码不正确！应该为：' + valnum); 
                     return false;
                 }
                 return true;
             }
         }
         return false;
     };
});
function empChange(empId){
	if (empId !=0 ){
		var postdata={
				"empId" : empId
			}
		$.post(root+"/service/btask/findTaskCountByEmpId",postdata,function(data){
			if (data.retCode){
				$("#taskCount").html(data.count);
				$(".taskCountDiv").show();
			}
		});
	}else{
		$(".taskCountDiv").hide();
	}
}
function empChanges(empId){
	if (empId !=0 ){
		var postdata={
				"empId" : empId
			}
		$.post(root+"/service/btask/findTaskCountByEmpId",postdata,function(data){
			if (data.retCode){
				$("#taskCounts").html(data.count);
				$(".taskCountDivs").show();
			}
		});
	}else{
		$(".taskCountDivs").hide();
	}
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
};

