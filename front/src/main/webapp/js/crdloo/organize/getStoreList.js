$(function(){
	var provinceCode=$("#provinceCode").val();
	var region = $("#region_code option[value='"+provinceCode+"']").attr("selected","selected");
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
		var region_code = $("#region_code option:selected").val();
		var searchName = $("#searchName").val();
		searcher(region_code,searchName,pageNumber);
		$("#myForm").submit();
	});
	
	//提交方法,(参数数值，当前页数)
	function searcher(region_code,searchName,pageNumber){
		$("#pageNumber").val(pageNumber);
		$("#storeName").val(searchName);
		$("#provinceCode").val(region_code);
		$("#myForm").submit();
	}
	//查询列表
	$("#mySubmit").on("click",function(){
		var region_code = $("#region_code option:selected").val();
		var searchName = $("#searchName").val();
		searcher(region_code,searchName,1);
	});
	//删除方法
	$(".btn-delete").on("click",function(){
		var id = $(this).attr("data-id");
		var flag = confirm("确定删除？");
		if(flag){
			$.post(root+"/service/organize/deleteStore",{"id":id},function(data){
				if(data.retCode){
					alert("删除成功!");
					window.location.reload(true);
				}else{
					alert(data.retMsg);
					return false;
				}
			});
		}
	});
	
	//修改员工
	$(".btn-update").on("click",function(){
		var id = $(this).data("id");
		$.post(root+"/service/organize/getStoreInfo",{"id":id},function(data){
			if(data.retCode){
				$("#updatestore").show();
				var e = data.store;
				var name = $("#name").val(e.name);
				var id=$("#id").val(e.id);
				var provinceCode=$("#region_id option[value='"+e.provinceCode+"']").attr("selected","selected");
			}
		});
	});
	
	//添加显示门店按钮
	$(".btn-addStore").on("click",function(){
		$("#updatestore").show();
		$(window).scrollTop(350);
		var name = $("#name").val("");
		var region = $("#region_id").val("");
		var id=$("#id").val("");
		
	});
	//添加员工
	$("#saveStore").on("click",function(){
		var name = $("#name").val();
		var region_id = $("#region_id option:selected").val();
		var id = $("#id").val();
		if(region_id ==""){
			alert("请选择区域");
			return;
		}
		if(name =='' && name==""){
			alert("门店不能为空");
			return;
		}
		var postdata = {
				"id":id,
				"name":name,
				"provinceCode":region_id
		};
		if(id!=null && ''!=id){ 
			//进行跟新操作
			$.post(root+"/service/organize/updateStore",postdata,function(data){
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
			$.post(root+"/service/organize/addStore",postdata,function(data){
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
});