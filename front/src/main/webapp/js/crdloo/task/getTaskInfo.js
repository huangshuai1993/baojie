$(function(){
	var chElement={
		$photoPic:$(".photo-pic"),
		$origUrlBG:$(".origUrlBG"),
		$closeImg:$(".closeImg"),
		$origUrl:$(".origUrl"),
		$btnRight:$(".btn-right"),
		$btnLeft:$(".btn-left"),
		$photoZb:$(".photo-zb"),
		$mapBG:$(".mapBG")
	}
	//修改任务详情
	$("#updateTaskInfo").on("click",function(){
		var id = $("#taskId").val();
		var taskStatus = $("#taskStatus").val();
		if(taskStatus == 0){//未分配
			var storeId = $("#storeId option:selected").val();
			var empId = $("#empId option:selected").val();
		}
		if(taskStatus <3){
			var removeStatus = $("input[name='removeStatus']:checked").val();
			var removeText = $("#removeText").val();
			if(removeStatus == 8){//放弃任务
				if(removeText ==""){
					alert("请填写放弃原因");
					return;
				}
			}
		}else if(taskStatus == 3){
			var passStatus = $("input[name='passStatus']:checked").val();
			var passText = $("#passText").val();
			if(passStatus != "" || passStatus !=null){//放弃任务
				if(passText ==""){
					alert("请填写处理备注");
					return;
				}
			}
		}
		var postdata = {
				"id":id,
				"taskStatus":taskStatus,
				"storeId":storeId,
				"empId":empId,
				"removeStatus":removeStatus,
				"removeText":removeText,
				"passStatus":passStatus,
				"passText":passText,
		};
		
		$.post(root+"/service/btask/updateTaskInfo",postdata,function(data){
			if(data.retCode){
				alert(data.retMsg);
				window.location.href=root+"/service/btask/getTaskList";
			}else{
				alert(data.retMsg);
				return false;
			}
		});
	});
	
     chElement.$photoPic.on('click',function(){
    	chElement.$origUrlBG.hide();
    	chElement.$mapBG.hide();
    	var xzIndex;
    	xzIndex=0;
    	imgRotate(chElement.$origUrl,0,"0s")
		var Index=chElement.$photoPic.index(this);
		chElement.$origUrlBG.eq(Index).show();
		chElement.$btnRight.on("click",function(){
			xzIndex++;
			imgRotate(chElement.$origUrl.eq(Index),xzIndex,"0.3s")
		});
		chElement.$btnLeft.on("click",function(){
			xzIndex--;
			imgRotate(chElement.$origUrl.eq(Index),xzIndex,"0.3s")
		});
     });
     
     chElement.$closeImg.on('click',function(){
		var Index=chElement.$closeImg.index(this);
		chElement.$origUrlBG.eq(Index).hide();
		xzIndex=0;
		imgRotate(chElement.$origUrl.eq(Index),xzIndex,"0")
     });
     
});
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
function imgRotate($origUrl,xzIndex,durTime){
	$origUrl.css({
		"-webkit-transform": "translate3d(-50%,-50%,0) rotate("+(90*xzIndex)+"deg)",
		"-moz-transform": "translate3d(-50%,-50%,0) rotate("+(90*xzIndex)+"deg)",
		"-ms-transform": "translate3d(-50%,-50%,0) rotate("+(90*xzIndex)+"deg)",
		"-o-transform": "translate3d(-50%,-50%,0) rotate("+(90*xzIndex)+"deg)",
		"transform": "translate3d(-50%,-50%,0) rotate("+(90*xzIndex)+"deg)",
		"-webkit-transition":"all "+durTime,
		"-moz-transition":"all "+durTime,
		"-ms-transition":"all "+durTime,
		"-o-transition":"all "+durTime,
		"transition":"all "+durTime,
		"max-width":"500px",
		"max-height":"500px"
	});
}
var mapElement={
	$mapContainer:$("#mapContainer"),
	$mapBG:$(".mapBG"),
	$origUrlBG:$(".origUrlBG")
}
function mapShow(pot){
	mapElement.$origUrlBG.hide();
	var pot=pot.split(",");
	mapElement.$mapBG.show();
	var map = new AMap.Map("mapContainer", {
		resizeEnable: true,
		zoom: 13
	});
	var infoWindow = new AMap.InfoWindow({offset:new AMap.Pixel(0,-30)});
	
	AMap.plugin('AMap.Geocoder',function(){
		var geocoder = new AMap.Geocoder({
			city: "010"//城市，默认：“全国”
		});
		var marker = new AMap.Marker({
			map:map,
			position:pot,
			bubble:true
		})
		geocoder.getAddress(pot,function(status,result){
			  if(status=='complete'){
				 infoWindow.setContent(result.regeocode.formattedAddress);
				 infoWindow.open(map,pot);
			  }
		})

	 });
	map.setFitView();
};
function mapHide(){
	mapElement.$mapBG.hide();
};

