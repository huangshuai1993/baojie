
function provinceChange(province,city,region){
	var provinceVal=$(province+' option:selected').val();
	if(provinceVal==""){
		$(city).html('<option value="">请选择</option>');
		$(region).html('<option value="">请选择</option>');
		return;
	}else{
		$(region).html('<option value="">请选择</option>');
	}
	regionPost(city,provinceVal);
	
};
function cityChange(city,region){
	var cityVal=$(city+' option:selected').val();
	if(cityVal==""){
		$(region).html('<option value="">请选择</option>');
		return;
	}
	regionPost(region,cityVal);
	
};
function regionPost(region,regionVal){
	var postdata={
			region_id : regionVal
		}
	$.post(root+"/service/region/regions",postdata,function(data){
		var dataList=data.data;
		var regionList = '<option value="">请选择</option>';
		if(dataList.length>0){
			var regionLists=dataList.map(function(item,index){
				var row=[
				         '<option value="',item.region_id,'">',item.region_name,'</option>'
				         ];
				return row.join("");
			}).join("");
			regionList+=regionLists;
		}
		$(region).html(regionList);
	});
}
