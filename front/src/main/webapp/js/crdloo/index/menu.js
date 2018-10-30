$(function(){
		init();
		//主菜单单击事件
		mainMenuCurrent();
		//二级菜单单击事件
		subMenuCurrent();
		//进行初始化操作
		function init(){
		//当前菜单
		var $qzbnavLi = $("#qzbnav li");
		var $liOpen = $("#qzbnav li.open").length;
		//显示当前菜单
		currentMenu($qzbnavLi,$liOpen);
		function currentMenu ($qzbnavLi,$liOpen){
			//判断当前菜单个数
			if($liOpen<=0){
				if($qzbnavLi.length>=1){
					//先设置cookie中的菜单，没有则设置第一个菜单
					var index = getMainIndexCookie();
					var subIndex = getSubIndexCookie();
					if(index== null || ''==index){
						//设置一级菜单
						$("#qzbnav li:eq(0) a").addClass("open");
						//设置二级菜单
						$(".sidebar").hide();
						$(".sidebar:eq(0)").show();
						$(".sidebar:eq(0)").find("#nav li:eq(0) a").addClass("open");
					}else{
						//设置一级菜单
						$("#qzbnav li:eq("+index+") a").addClass("open");
						//设置二级菜单
						$(".sidebar").hide();
						$(".sidebar:eq("+index+")").show();
						if(subIndex==null || ''==subIndex){
							$(".sidebar:eq("+index+")").find("#nav li:eq(0) a").addClass("open");
						}else{
							$(".sidebar:eq("+index+")").find("#nav li:eq("+subIndex+") a").addClass("open");
						}
						var menuName = $("#qzbnav li:eq("+index+") a span").html();
						if(menuName=="产品管理"){
							specialMenuActivity(index);
						}
					}
					
				}else{
					alert("没有菜单显示");
				}
			};
		}
	};

	//一级菜单单机事件
	function mainMenuCurrent(){
		var $qzbnavLi = $("#qzbnav li");
		//选择当前标签
		$("#qzbnav li").on("click",function(){
			var index = $(this).index();
			var powerId = $(".powerclass:eq("+index+")").find("#powerId:eq(0)").val();
			setMainIndexCookie(index);
			setSubIndexCookie(null);
			var url = $(".sidebar:eq("+index+")").find("#nav li:eq(0) a").attr("href");
			if(index==0){
				window.location.href=root+"/service/login/index?powerId="+powerId;
			}else{
				window.location.href=url+"?powerId="+powerId;
			};
		});
	};
	
	//二级菜单单击事件
	function subMenuCurrent(){
		var $qzbnavLi = $("#nav li");
		//选择当前标签
		$("#nav li").on("click",function(){
			var index = $(this).index();
			setSubIndexCookie(index);
		});
	};
	
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
    
    //得到产品管理的一级菜单单机序号
    function getProductMainIndexCookie(){
        var u = decodeURI(($.cookie("productMainIndexCookie") || ""),"utf-8");
        return u;
    }
    //设置产品管理一级菜单单机序号
    function setProductMainIndexCookie(value){
        if(null!=value){
            value=encodeURI(value);
        }
        $.cookie("productMainIndexCookie",value,{path:"/"});
    }

    //得到产品管理二级菜单单机序号
	function getProductSubIndexCookie(){
        var u = decodeURI(($.cookie("productSubIndexCookie") || ""),"utf-8");
        return u;
    }
	
	//设置产品管理二级菜单单机序号
    function setProductSubIndexCookie(value){
        if(null!=value){
            value=encodeURI(value);
        }
        $.cookie("productSubIndexCookie",value,{path:"/"});
    }
    
    //填充产品管理
    function specialMenuActivity(index){
    	//产品管理二级菜单
    	var sub = getProductSubIndexCookie()||"";
    	//产品管理一级菜单
    	var main = getProductMainIndexCookie()||"";
    	
    	var l1 = $("._MEMBER").length;
    	var l2 = $("._ADMINA").length;
    	var l3 = $("._ADMINB").length;
    	
    	var sidebar = $(".sidebar:eq("+index+")");
    	var html1 = "";
    	if(l1>0){
    		html1 = '<li class="has_sub">'
				+'<a href="#" style="padding-left:12px;">会员申请标'
				+'<span class="pull-right mrt10">'
				+'<i class="icon-chevron-right"></i></span></a>'
				+'<ul>';
			$.each($("._MEMBER"),function(i,n){
				html1 =  html1+'<li><a href="'+$(n).attr("href")+'" class="_MEMBER">'+$(n).html()+'</a></li>'
			});
			html1 = html1+"</ul></li>";
    	};
    	
    	var html2 ="";
    	if(l2>0){
    		html2 = '<li class="has_sub">'
    			+'<a href="#" style="padding-left:12px;">后台发布标'
    			+'<span class="pull-right mrt10">'
    			+'<i class="icon-chevron-right"></i></span></a>'
    			+'<ul>';
        	$.each($("._ADMINA"),function(i,n){
        		html2 =  html2+'<li><a href="'+$(n).attr("href")+'" class="_ADMINA">'+$(n).html()+'</a></li>'
        	});
        	html2 = html2+"</ul></li>";
    	}
    	
    	var html3 = "";
    	if(l3>0){
    		html3 = '<li class="has_sub">'
    			+'<a href="#" style="padding-left:12px;">标的综合管理'
    			+'<span class="pull-right mrt10">'
    			+'<i class="icon-chevron-right"></i></span></a>'
    			+'<ul>';
        	$.each($("._ADMINB"),function(i,n){
        		html3 =  html3+'<li><a href="'+$(n).attr("href")+'" class="_ADMINB">'+$(n).html()+'</a></li>'
        	});
        	html3 = html3+"</ul></li>";
    	}
    	
    	var resulthtml = '<ul id="lever3">'
            			+html1+html2+html3
            			+'</ul>';
    	sidebar.find("#nav").remove("ul");
    	sidebar.find("#nav").html(resulthtml);
    	//添加事件
    	specialMenuClick(index,main,sub);
    }
    
    //产品管理事件
    function specialMenuClick(index,main,sub){
    	var sidebar = $(".sidebar:eq("+index+")");
    	if(main!=null && ""!=main){
    		sidebar.find("#nav ul#lever3 li.has_sub:eq("+main+") ul li:eq("+sub+") a").addClass("open");
    		sidebar.find("#nav ul#lever3 li.has_sub:eq("+main+") ul").slideDown(70);
    	}else{
    		sidebar.find("#nav ul#lever3 li.has_sub:eq("+0+") ul li:eq("+0+") a").addClass("open");
    		sidebar.find("#nav ul#lever3 li.has_sub:eq("+0+") ul").slideDown(70);
    	}
    	//产品管理下的一级菜单单击事件
    	$(".has_sub").on("click",function(){
    		$(this).find("ul").slideToggle(70);
    	});
    	//产品管理下二级菜单的单击事件
    	$(".has_sub ul li").on("click",function(){
    		$(".has_sub ul li a").removeClass("open");
    		$(this).find("a").addClass("open");
    		//二级菜单下标
    		setProductSubIndexCookie($(this).index());
    		//当前一级菜单下标
    		var mainIndex = $(this).closest("li.has_sub").index();
    		setProductMainIndexCookie(mainIndex);
    	});
    };
    //显示产品管理默认的第一个页面
    function specialMenuUrl(index){
    	var sidebarLi = $(".sidebar:eq("+index+")").find("#nav ul#lever3 li.has_sub:eq(0) ul li:eq(0) a");
    	//添加当前类
    	sidebarLi.addClass("open");
    	var url = sidebarLi.attr("href");
    	window.location.href=url;
    };
});