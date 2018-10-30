$(function(){
	$("#searcher").on("click",function(){
		var account = $("#tempAccount").val();
		var nickName = $("#tempNickName").val();
		$("#Uaccount").val(account);
		$("#Unickname").val(nickName);
		$("#myForm").submit();
	});
	
	//导出
	$("#export").on("click",function(){
		var temp = $("#tempParameName").val();
		$("#parameName").val(temp);
		$("#myForm").attr("action",root+"/service/employee/export?isCert=0");
		$("#myForm").submit();
		$("#myForm").attr("action",root+"/service/employee/getAllUsers");
	});
	
	var $ur_a = $(".pagination li a");
	$ur_a.on("click",function(){
		var account = $("#tempAccount").val();
		var nickName = $("#tempNickName").val();
		var pageNumber = $(this).attr("data-pagenum");
		searcher(account,nickName,pageNumber);
		$("#myForm").submit();
	});
	
	//提交方法,(参数数值，当前页数)
	function searcher(account,nickName,pageNumber ){
		$("#Uaccount").val(account);
		$("#Unickname").val(nickName);
		$("#pageNumber").val(pageNumber);
		$("#myForm").submit();
	}
	
	$(".user-disable").on("click",function(){
		var state = $(this).data("state");
		var custNo = $(this).data("custno");
		var postdata = {
				"state":state,
				"custNo":custNo
		};
		$.post(root+"/service/user/modifyUserEnable",postdata,function(data){
			if(data.retCode){
				alert("操作成功!");
				window.location.reload(true);
			}else{
				alert("操作失败!");
			}
		});
	});
});
$(".user-edit").on("click", function () {
        $(".modal-body").find("input[type=text]").val("");
        var userid = $(this).data("custno");

        $('#user-editor').data("userid", userid);

        $.get(root + "/service/user/findUserById", {
            userid: userid
        }).then(function (data) {
            if (!data.retCode) {
                console.log(data.retMsg);
                return;
            }

            var userInfo = data.result;
			
            $("#user-editor .user-field").each(function () {
                var $this = $(this);

                var fieldName = $this.attr("id");
                var fieldValue = userInfo[fieldName];

                if (fieldValue == null)
                    return;

                $input = $this.find("input");

                if ($input.length == 1) {
                    $input.val(fieldValue);

                } else {
                    if ($input.attr("type") == "radio") {
                        $input.each(function () {
                            var $thisInput = $(this);

                            if ($thisInput.val() == fieldValue) {
                                $thisInput.attr("checked", "checked");
                            }

                        });
                    }
                }
            });
			 var imgurl=$("#imgurl").val();
			 $("#img").attr("src",imgurl);
            $('#user-editor').modal({
                keyboard: false,
                backdrop: "static"
            });
        });
        return false;
       
    });

    $("#save-user").on("click", function () {
        var userInfo = {};

        $("#user-editor .user-field").removeClass("has-error");
		
        try {
            $("#user-editor .user-field").each(function () {
                var $this = $(this);

                var fieldName = $this.attr("id");
                var fieldValue = null;

                $input = $this.find("input");
                if ($input.length == 1) {
                    fieldValue = $input.val();
                } else {
                    if ($input.attr("type") == "radio") {
                        fieldValue = $this.find("input:checked").val();
                    }
                }

                if (fieldValue != null)
                    fieldValue = fieldValue.trim();

              /* var checker = userCheckers[fieldName];
                if (checker != null) {
                   var error = checker(fieldName, fieldValue);
                    if (error != null) {
                        $this.addClass("has-error");
                        throw error;
                    }
                }
				*/	
                if (fieldValue == null || fieldValue.length == 0)
                    return;

                userInfo[fieldName] = fieldValue;
            });

            $.post(root + "/service/user/updateUserInfo", userInfo)
                .then(function (data) {
                    if (!data.retCode) {
                        alert(data.retMsg);
                        return;
                    }

                    $('#user-editor').modal("hide");

                    alert("保存成功");
                    window.location.reload(true);
                });
        } catch (error) {
            alert(error);
            return;
        }
    });

 
    
    $(function(){
    	$("#delImg").on("click",function(){
    		 $("#imgurl").val(" ");
			 $("#img").attr("src","");
    	})
    });
   
    