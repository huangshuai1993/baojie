$(function(){
	$("#logout").on("click",function(){
		setSubIndexCookie(null);
		setMainIndexCookie(null);
		window.location.href=root+"/service/login/logout"
	});
	
	//设置二级菜单单机序号
    function setSubIndexCookie(value){
        if(null!=value){
            value=encodeURI(value);
        }
        $.cookie("subIndexCookie",value,{path:"/"});
    }
    
    //得到1级菜单单机序号
    function getMainIndexCookie(){
        var u = decodeURI(($.cookie("mainIndexCookie") || ""),"utf-8");
        return u;
    }
    //设置1级菜单单机序号
    function setMainIndexCookie(value){
        if(null!=value){
            value=encodeURI(value);
        }
        $.cookie("mainIndexCookie",value,{path:"/"});
    }
});