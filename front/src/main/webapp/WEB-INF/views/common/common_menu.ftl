<!-- class="open",当前选项添加open类 -->
<div id="weiyi">
<!-- class="open",当前选项添加open类 -->
<div class="lever-one clearfix">
	<!-- Dropdown menu -->
    <ul class="dropdown-menu">
        <li id ="logout"><a href="javascript:void(0);"><i class="icon-off"></i> 退出</a></li>
    </ul>
    <ul id="qzbnav">
        <#list resultMenu as indexMenu>
			<!-- 一级菜单 -->
			<li class="powerclass">
			<a href="${contextPath}${indexMenu.powerUrl}"><span>${indexMenu.menuName}</span></a>
			<input hidden id="powerId" value="${indexMenu.powerId}"/>
			</li>
  		</#list>
    </ul>
    
</div>
	<!-- 一级菜单权限-->
		<div class="sidebar">
		    <div class="sidebar-dropdown"><a href="#">导航</a></div>
		    	 <div id="nav">
			        <ul>
		        		<!--二级菜单权限-->
		        		<#list resultMenuTwo as sunMenu>
		        		<#if sunMenu.menuName!='index'>
	        				<#if sunMenu.powerUrl ??>
	        					<#if (sunMenu.powerName?substring(sunMenu.powerName?last_index_of('_'))!='BUT')>
	           						<li class="powerClassTwo"><a href="${contextPath}${sunMenu.powerUrl}">${sunMenu.menuName}</a>
	           						<input hidden id="powerIdTwo" value="${sunMenu.powerId}"/>
	           						</li>
	           						
	           					</#if>
		           			<#else>
		           				<#if (sunMenu.powerName?substring(sunMenu.powerName?last_index_of('_'))!='BUT')>
		           					<li><a href="">${sunMenu.menuName}</a></li>
		           				</#if>
		           			</#if>
	           			</#if>
	           			</#list>
			        </ul>
		    	</div>
		</div>
	


</div>
<script type="text/javascript" src="${root}/js/public/jquery.min.js"></script>
<script type="text/javascript" src="${root}/js/crdloo/index/common_menu.js"></script>
<script type="text/javascript" src="${root}/js/public/jquery.cookie.pack.js"></script>