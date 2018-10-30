//cookie操作
function setCookie(name,value,day){
    if(null!=value){
        value=encodeURI(value);
    }
    if(day){
    	$.cookie(name,value,{expires:day,path:"/"});
    }else{
    	$.cookie(name,value,{path:"/"});
    }
}
function getCookie(name){
    var cookieValue = decodeURI(($.cookie(name) || ""),"utf-8");
    return cookieValue;
}
//loading样式
function showContentLoading(){
	$("#contentLoading").show();
};
function closeContentLoading(){
	$("#contentLoading").hide();
};
//sessionStorage的操作
session={
	get:function(key){
		return sessionStorage.getItem(key);
	},
	set:function(key,val){
        return sessionStorage.setItem(key,val);
    },
    del : function(key){
        return sessionStorage.removeItem(key);
    }
};
//格式化时间
function getFormatDate(myDate)
{
   var Year=myDate.getFullYear(),
   	   Month=myDate.getMonth()+1,
   	   Day=myDate.getDate();
   if(Month<10){
	   Month="0"+Month;
   }
   if(Day<10){
	   Day="0"+Day;
   }
   return Year+"-"+Month+"-"+Day;
}
