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
		$("#searchTowerName").val(searchName);
		$("#myForm").submit();
	}
	//查询列表
	$("#mySubmit").on("click",function(){
		var searchName = $("#searchName").val();
		searcher(searchName,1);
	});
	//删除方法
	$(".btn-delete").on("click",function(){
		var id = $(this).attr("data-id");
		var flag = confirm("确定删除？");
		if(flag){
			$.post(root+"/service/bstaff/deleteStaff",{"id":id},function(data){
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
		$.post(root+"/service/bstaff/getStaffInfo",{"id":id},function(data){
			if(data.retCode){
				$("#updatebStaff").show();
				var e = data.bstaff;
				var staffId = $("#staffId").val(e.id);
				var name = $("#name").val(e.name);
				var idCard = $("#idCard").val(e.idCard);
				var phone = $("#phone").val(e.phone);
				var towerId = $("#towerIds").val(e.towerId);
				var positionId = $("#positionIds").val(e.positionId);
			}
		});
	});
	
	//添加显示员工按钮
	$(".btn-addStaff").on("click",function(){
		$("#updatebStaff").show();
		var staffId = $("#staffId").val("");
		var name = $("#name").val("");
		var idCard = $("#idCard").val("");
		var phone = $("#phone").val("");
		var towerId = $("#towerIds").val("");
		var positionId = $("#positionIds").val("");
	});
	$("#saveStaff").on("click",function(){
		var staffId = $("#staffId").val();
		var name = $("#name").val();
		var idCard = $("#idCard").val();
		var phone = $("#phone").val();
		var towerId = $("#towerIds").val();
		var positionId = $("#positionIds").val();
		if(name == ''){
			alert("职员名称不能为空!");
			return;
		}
		if(towerId == ''){
			alert("楼盘信息不能为空!");
			return;
		}
		if(positionId == ''){
			alert("职务信息不能为空!");
			return;
		}
		if (trim(idCard).length > 0) {
            if (!checkIdcard(idCard)) {
                alert("输入的身份证号格式错误");
                return;
            }
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
            url:root+"/service/bstaff/addOrUpdateStaff", 
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
	
	 $("#idCard").blur(function () {
         var idNumber = $("#idCard").val();
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