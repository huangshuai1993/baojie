
$(function(){
	function isPositiveNum(s){//是否为正整数  
	    var re = /^[0-9]*[1-9][0-9]*$/ ;  
	    return re.test(s)  
	} 
	function isNumAndletter(s){//数字和字母
		var re = /^[A-Za-z0-9]+$/; 
		return re.test(s)
	}
	
	//提交方法,(参数数值，当前页数,时间类型,请求类型)
	function searcher(pageNumber,minTime,maxTime,username ){
		$("#pageNumber").val(pageNumber);
		$("#minTime").val(minTime);
		$("#maxTime").val(maxTime);
		$("#username").val(username);
		$("#myForm").submit();
	}
	var minTime=$("#minTimeValue").val(),
		maxTime=$("#maxTimeValue").val(),
		username=$("#usernameValue").val();
	//分页情况
		var $ur_a = $(".pagination li a");
		$ur_a.on("click",function(){
			var pageNumber = $(this).attr("data-pagenum");
			minTime=$("#minTimeValue").val();
			maxTime=$("#maxTimeValue").val();
			username=$("#usernameValue").val();
			searcher(pageNumber,minTime,maxTime,username );
			$("#myForm").submit();
		});
	 $('#searcher').on('click',function(){
		 	minTime=$("#minTimeValue").val();
			maxTime=$("#maxTimeValue").val();
			username=$("#usernameValue").val();
		 searcher(1,minTime,maxTime,username);
     });
});

