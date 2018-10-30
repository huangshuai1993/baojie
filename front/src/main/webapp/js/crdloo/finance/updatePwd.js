$(function(){
	function isNumAndletter(s){//6-18数字和字母
		var re = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,18}$/; 
		return re.test(s)
	}
	$("#pwd").blur(function() {  
		var pwd = $("#pwd").val();  
		if (!isNumAndletter(pwd)){
            $("#pwdError").show(); 
            $("#pwdInfo").hide();
        }else{
        	$("#pwdError").hide(); 
            $("#pwdInfo").hide();
        } 
    });
	
	$("#pwd").focus(function() {  
	    $("#pwdInfo").show();
	    $("#pwdError").hide(); 
    });
	//修改密码
	$("#save").on("click",function(){
		var pwd = $("#pwd").val();
		var repeatPwd = $("#repeatPwd").val();
		if(pwd =='' && pwd==""){
			alert("密码不能为空");
			return;
		}
		if(repeatPwd =='' && repeatPwd==""){
			alert("重复密码不能为空");
			return;
		}
		if (!isNumAndletter(pwd)){
			alert("密码格式不对");
			return ;
		}
		if (repeatPwd != pwd){
			alert("两次密码不一致");
			return;
		}
		var postdata = {
				"pwd":pwd,
				"repeatPwd":repeatPwd
		};
		$.post(root+"/service/finance/updatePwd",postdata,function(data){
			if(data.retCode){
				alert(data.retMsg);
				window.location.reload(true);
			}else{
				alert(data.retMsg);
				return false;
			}
		});
	});
});