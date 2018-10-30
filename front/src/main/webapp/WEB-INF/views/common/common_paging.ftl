<div class="qzb-pageye">
<ul class="clearfix">
    <li><a href="javascript:void(0)" class="prev-on"><</a></li>
    <#list paging.startPageNo..paging.endPageNo as page>
    <li><a href="javascript:ajaxPage(${page})" <#if page==paging.pageNo>class="prev-o prev-cut"<#else>class="prev-o"</#if>>${page}</a></li>
    </#list>
    <li><a href="javascript:void(0)" class="prev-next">></a></li>
</ul>
<script type="text/javascript">
	$(function(){
		var pageTotal = ${paging.pageTotal};
		var pageNo = ${paging.pageNo};
		$(".prev-on").on("click",function(){
			if(pageNo-1<1){ajaxPage(1);}
			else{ajaxPage(pageNo-1);}
		});
		$(".prev-next").on("click",function(){
			if(pageNo+1>pageTotal){ajaxPage(pageTotal);}
			else{ajaxPage(pageNo+1);}
		});
	});
	function ajaxPage(pageNo){
		var params = {"currentPageNo":pageNo};
		if($("#beginTime").val()!=""){params.beginTime = $("#beginTime").val();}
		if($("#endTime").val()!=""){params.endTime = $("#endTime").val();}
		$.ajax({
            type:"POST",
            url:root+"/service/${ajaxPageUrl}?refresh="+new Date().getTime(),
            data:params,
            datatype:"html",
            beforeSend:function(){//$("#msg").html("logining");
            },
            success:function(data){
            	$(".qzb-pageye").html(data);
            },
            complete:function(XMLHttpRequest, textStatus){
               //alert(XMLHttpRequest.responseText);
               //alert(textStatus);
               $("#qzb-paging").html(XMLHttpRequest.responseText);
            },
            error:function(){
            	
            }         
        });
	}
</script>
</div>