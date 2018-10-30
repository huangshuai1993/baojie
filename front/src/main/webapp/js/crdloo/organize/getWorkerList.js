$(function(){
	var statusCode=$("#statusCode").val();
	var status_Code = $("#status_code option[value='"+statusCode+"']").attr("selected","selected");
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
		var searchMobile = $("#searchMobile").val();
		var searchName = $("#searchName").val();
		var status_code = $("#status_code option:selected").val();
		searcher(status_code,searchName,searchMobile,pageNumber);
		$("#myForm").submit();
	});
	
	//提交方法,(参数数值，当前页数)
	function searcher(status_code,searchName,searchMobile,pageNumber){
		$("#pageNumber").val(pageNumber);
		$("#workerName").val(searchName);
		$("#statusCode").val(status_code);
		$("#search_Mobile").val(searchMobile);
		$("#myForm").submit();
	}
	//查询列表
	$("#mySubmit").on("click",function(){
		var searchMobile = $("#searchMobile").val();
		var searchName = $("#searchName").val();
		var status_code = $("#status_code option:selected").val();
		searcher(status_code,searchName,searchMobile,1);
	});
	//启用禁用
	$(".btn-disable").on("click",function(){
		var id = $(this).data("id");
		var state = $(this).data("state");
		$.post(root+"/service/organize/updateWorkerStatus",{"id":id,"status":state},function(data){
			if(data.retCode){
				alert("修改成功!");
				window.location.reload(true);
			}else{
				alert("修改失败!");
				return false;
			}
		});
	});
	
	//修改员工
	$(".btn-update").on("click",function(){
		var id = $(this).data("id");
		var gender=$("input[name='gender']").prop("checked","");
		var status=$("input[name='status']").prop("checked","");
		$.post(root+"/service/organize/getWorkerInfo",{"id":id},function(data){
			if(data.retCode){
				$("#updateWorker").show();
				var e = data.worker;
				var name = $("#name").val(e.name);
				gender=$("input[name='gender'][value='"+e.gender+"']").prop("checked","checked");
				var idcardNo = $("#idcardNo").val(e.idcardNo);
				var birthday = $("#birthday").val(e.birthdayStr);
				var mobile = $("#mobile").val(e.mobile);
				var empNo = $("#empNo").val(e.empNo);
				var empNo = $("#email").val(e.email);
				status=$("input[name='status'][value='"+e.status+"']").prop("checked","checked");
				var id=$("#id").val(e.id);
				var st=data.storeForm;
				if(st!=null){//超级管理员
					var region_id = $("#region_id option[value='"+st.provinceCode+"']").attr("selected","selected");
					checkField(st.provinceCode)
					var store_id = $("#store_id option[value='"+st.id+"']").attr("selected","selected");
				}
			}
		});
	});
	
	//添加显示员工按钮
	$(".btn-addWorker").on("click",function(){
		$("#updateWorker").show();
		$(window).scrollTop(350);
		var name = $("#name").val("");
		var status=$("input[name='status'][value='"+1+"']").prop("checked","checked");
		var idcardNo = $("#idcardNo").val("");
		var birthday = $("#birthday").val("");
		var mobile = $("#mobile").val("");
		var empNo = $("#empNo").val("");
		var status = $("#status").val("");
		var email = $("#email").val("");
		var id=$("#id").val("");
	});
	//添加员工
	$("#saveWorker").on("click",function(){
		var name = $("#name").val();
		var gender = $("input[name='gender']:checked").val();
		var idcardNo = $("#idcardNo").val();
		var birthday = $("#birthday").val();
		var mobile = $("#mobile").val();
		var empNo = $("#empNo").val();
		var email = $("#email").val();
		var status = $("input[name='status']:checked").val();
		var region_id = $("#region_id option:selected").val();
		var store_id = $("#store_id option:selected").val();
		var id = $("#id").val();
		if(name =='' && name==""){
			alert("员工不能为空");
			return;
		}
		if(region_id ==""){
			alert("请选择区域");
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
		if(empNo =='' && empNo==""){
			alert("工号不能为空");
			return;
		}
		if(email =='' &&  !/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(email)){
			alert("邮箱有误，请重填");
			return;
		}
		if(store_id =='' && store_id==""){
			alert("门店不能为空");
			return;
		}
		var postdata = {
				"id":id,
				"name":name,
				"gender":gender,
				"mobile":mobile,
				"idcardNo":idcardNo,
				"birthday":birthday,
				"empNo":empNo,
				"email":email,
				"status":status,
				"region_id":region_id,
				"store_id":store_id
		};
		if(id!=null && ''!=id){ 
			//进行跟新操作
			$.post(root+"/service/organize/updateWorker",postdata,function(data){
				if(data.retCode){
					alert(data.retMsg);
					window.location.reload(true);
				}else{
					alert(data.retMsg);
					return false;
				}
			});
		}else{
			//进行添加操做
			$.post(root+"/service/organize/addWorker",postdata,function(data){
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
     }  

});