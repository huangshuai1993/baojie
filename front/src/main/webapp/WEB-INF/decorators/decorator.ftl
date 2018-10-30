<html>  
    <head> 
    	<#include "../views/common/common_src.ftl"> 
        <title>${title}</title>  
        ${head}  
    </head>  
    <body>  
	    <#include "../views/common/common_header.ftl">
	    <div class="content">
		<#include "../views/common/common_menu.ftl">
		${body}
         </div>  
        <#include "../views/common/common_footer.ftl"> 
        <span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>
    </body>  
</html>  