$(function(){
	//回车时，默认是登陆
	$(document).keydown(function (event) {
	    if (event.keyCode == 13) {
	    	 $(".btn-danger").click();
	    }
	});
    
	//登录事件
	$(".btn-danger").on("click",function(){
		var loginAccount = $("#userName").val();
		var loginPassword = $("#userPassword").val();
	    if(null==loginAccount||loginAccount==""){
			alert("用户名称不能为空");
			return false;
		}
	    if(null==loginPassword||loginPassword==""){
	    	alert("密码不能为空");
	    	return false;
	    }else if(loginPassword.length<4||loginPassword.length>16){
	    	alert("密码长度为4-16个字符");
	    	return false;
	    }
		$.post(root+"/service/login/logins",{"userName":loginAccount,"password":loginPassword},function(data){
			if(data.retCode){
				//登录成功
				setSubIndexCookie(null);
				setMainIndexCookie(null);
				setProductMainIndexCookie(null);
				setProductSubIndexCookie(null);
				window.location.href=root+"/service/login/index"
			}else{
				//登录失败
				alert(data.retMsg);
				return false;
			}
		});
		
	});
	
	 //设置1级菜单单机序号
    function setMainIndexCookie(value){
        if(null!=value){
            value=encodeURI(value);
        }
        $.cookie("mainIndexCookie",value,{path:"/"});
    }
    
  //设置二级菜单单机序号
    function setSubIndexCookie(value){
        if(null!=value){
            value=encodeURI(value);
        }
        $.cookie("subIndexCookie",value,{path:"/"});
    }
    
  //设置产品管理二级菜单单机序号
    function setProductSubIndexCookie(value){
        if(null!=value){
            value=encodeURI(value);
        }
        $.cookie("productSubIndexCookie",value,{path:"/"});
    }
    
  //设置产品管理一级菜单单机序号
    function setProductMainIndexCookie(value){
        if(null!=value){
            value=encodeURI(value);
        }
        $.cookie("productMainIndexCookie",value,{path:"/"});
    }
});