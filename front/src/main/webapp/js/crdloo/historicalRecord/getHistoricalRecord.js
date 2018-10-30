
$(function(){
	function isPositiveNum(s){//是否为正整数  
	    var re = /^[0-9]*[1-9][0-9]*$/ ;  
	    return re.test(s)  
	} 
	function isNumAndletter(s){//数字和字母
		var re = /^[A-Za-z0-9]+$/; 
		return re.test(s)
	}
	var timeType=null,
		requestTypeValue=null;
	var timeTypeValue=$(".timeType").attr("value"),
		requestTypeValue=$(".requestType").attr("value");
	timeType=timeTypeValue,
	requestType=requestTypeValue;
	addClassCut();	
	//提交方法,(参数数值，当前页数,时间类型,请求类型)
	function searcher(pageNumber,timeType,requestType ){
		$("#pageNumber").val(pageNumber);
		$("#timeType").val(timeType);
		$("#requestType").val(requestType);
		$("#myForm").submit();
	}
	/**
	 * 条件查询
	 */
	
	 $('.timeType').on('click','li',function(){
		  if(!$(this).hasClass("title")){
			  $(this).addClass('cut').siblings('li').removeClass('cut');
			  timeType=$(this).find("a").attr("value");
			  searcher(1,timeType,requestType);
		  }
		  
     });
	 $('.requestType').on('click','li',function(){
		  if(!$(this).hasClass("title")){
			  $(this).addClass('cut').siblings('li').removeClass('cut');
			  requestType=$(this).find("a").attr("value");
			  searcher(1,timeType,requestType);
		  }
	});
	//分页情况
		var $ur_a = $(".pagination li a");
		$ur_a.on("click",function(){
			var pageNumber = $(this).attr("data-pagenum");
			searcher(pageNumber,timeType,requestType);
			$("#myForm").submit();
		});
	 $("#searchRecord").on("click",function(){
	 });
	 function searchHistoricalRecordPage(timeType,requestType,pageNumber){
		   $.post(root+"/service/historicalRecord/ajaxHistoricalRecord",{
		         "timeType": timeType,
		         "requestType": requestType,
		         "pageNumber":pageNumber
		     },function(data){
		    	  $(".timeType li a").each(function(){
					  if($(this).attr("value")==timeType){
						  $(this).parent().addClass("current").siblings('li').removeClass('current');
					  } 
				   });
		    	  $(".requestType li a").each(function(){
					  if($(this).attr("value")==requestType){
						  $(this).parent().addClass("current").siblings('li').removeClass('current');
					  } 
				   });
		        });
	 }
	 function addClassCut(){
			$(".timeType li a").each(function(){
				  if($(this).attr("value")==timeTypeValue){
					  $(this).parent().addClass("cut").siblings('li').removeClass('cut');
				  } 
			   });
			  $(".requestType li a").each(function(){
				  if($(this).attr("value")==requestTypeValue){
					  $(this).parent().addClass("cut").siblings('li').removeClass('cut');
				  } 
			   });
		}
	
});

