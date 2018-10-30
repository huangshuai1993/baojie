$(function(){
	init();
	$("#qzbnav li").on("click",function(){
		var index = $(this).index();
		var powerId = $(".powerclass:eq("+index+")").find("#powerId:eq(0)").val();
		setMainIndexCookie(index);
		setMainPowerCookie(powerId)
		setSubIndexCookie(null);
	});
	//二级菜单单击事件
	//选择当前标签
	$("#nav li").on("click",function(){
		var index = $(this).index();
		var powerIdTwo = $(".powerClassTwo:eq("+index+")").find("#powerIdTwo:eq(0)").val();
		setSubIndexCookie(index);
	});
	function init(){
		//先设置cookie中的菜单，没有则设置第一个菜单
		var index = getMainIndexCookie();
		var subIndex = getSubIndexCookie();
		if(index== null || ''==index){
			//设置一级菜单
			$("#qzbnav li:eq(0) a").addClass("open");
			//设置二级菜单
			$(".sidebar:eq(0)").find("#nav li:eq(0) a").addClass("open");
		}else{
			//设置一级菜单
			$("#qzbnav li:eq("+index+") a").addClass("open");
			//设置二级菜单
			if(subIndex==null || ''==subIndex){
				$(".sidebar:eq(0)").find("#nav li:eq(0) a").addClass("open");
			}else{
				$(".sidebar:eq(0)").find("#nav li:eq("+subIndex+") a").addClass("open");
			}
		}
	};
	
	 //设置1级菜单单机序号
    function setMainIndexCookie(value){
        if(null!=value){
            value=encodeURI(value);
        }
        $.cookie("mainIndexCookie",value,{path:"/",domain:"51baojie.com"});
    }
    //得到1级菜单单机序号
    function getMainIndexCookie(){
        var u = decodeURI(($.cookie("mainIndexCookie") || ""),"utf-8");
        return u;
    }
	//得到二级菜单单机序号
	function getSubIndexCookie(){
        var u = decodeURI(($.cookie("subIndexCookie") || ""),"utf-8");
        return u;
    }
	
	//设置二级菜单单机序号
    function setSubIndexCookie(value){
        if(null!=value){
            value=encodeURI(value);
        }
        $.cookie("subIndexCookie",value,{path:"/",domain:"51baojie.com"});
    }
    
    //设置id序号
    function setMainPowerCookie(value){
        if(null!=value){
            value=encodeURI(value);
        }
        $.cookie("mainPowerCookie",value,{path:"/",domain:"51baojie.com"});
    }
    //得到id序号
    function getMainPowerCookie(){
        var u = decodeURI(($.cookie("mainPowerCookie") || ""),"utf-8");
        return u;
    }
    
})