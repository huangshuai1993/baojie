<!DOCTYPE html>
<html lang="en">
<head>
    <title>历史记录-历史记录列表</title>
    <link rel="stylesheet" type="text/css" href="${root}/style/investment-list.css"/>
</head>

<body>
        <!-- Main bar -->
        <div class="mainbar">

            <!-- Page heading -->
            <div class="page-head">
                <h2 class="pull-left"><i class="icon-user"></i> 历史记录> 历史记录列表</h2>
                <div class="clearfix"></div>

            </div>
            <!-- Page heading ends -->
            <!-- Matter -->
            <div class="matter">
                <div class="container">
                <div class="invest-biaolist inverst-bor">
                    <!--时间类型timeType-->
                    <div class='row'>
                    	<div class='col-md-12'>
                    		<p class='choiceTitle col-lg-2'>请选择时间：</p>
		                    <ul class="ui-tz-bdlx timeType" value='${timeType}'>
		                        <li class="cut"><a href="javascript:void(0)">全部</a></li>
		                        <li><a value="1" href="javascript:void(0)">近一天</a></li>
		                        <li><a value="2" href="javascript:void(0)">近一周</a></li>
		                        <li><a value="3" href="javascript:void(0)">近一月</a></li>
		                    </ul>
	                    </div>
                    </div>
                    <!--请求类型 requestType-->
                    <div class='row'>
                    	<div class='col-md-12'>
		                    <p class='choiceTitle col-lg-2'>请选择类型：</p>
		                    <ul class="ui-tz-bdlx requestType" value='${requestType}'>
		                        <#if typeEnums ??>
		                        <li class="cut"><a href="javascript:void(0)">全部</a></li>
		                            <#list  typeEnums as typeEnum>
		                        <li><a value="${typeEnum.id}" href="javascript:void(0)">${typeEnum.getName()}</a></li>
		                            </#list>
		                        </#if>    
		                    </ul>
		              	</div>
                    </div>       
                </div>
                    <!-- Table -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="widget">

                                <div class="widget-head clearfix">
                                    <div class="pull-left mtop5">历史记录</div>
                                    <div class="widget-icons pull-right mlt10 mtop5">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                </div>
                                <div class="widget-content">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>名称</th>
                                            <th>查询业务</th>
                                            <th>查询状态</th>
                                            <th>时间</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if historicalRecordList ??>
                                        	<#list  historicalRecordList as historicalRecord>
		                                        <tr>
		                                            <td>
		                                            <#if historicalRecord.result == 0>
		                                            <a <#if requestType == 15> target='_blank' </#if> href="${contextPath}/service/${historicalRecord.requestUrl}?swift_number=${historicalRecord.swiftnumber}">
		                                            <#else>
		                                            <a href="javascript:void(0)">
		                                            </#if>
		                                            ${historicalRecord.name}</a></td>
		                                            <td>${historicalRecord.typeName}</td>
		                                            <td  
		                                           <#if historicalRecord.result == 1> 
		                                           style="color:red"
		                                            </#if>
		                                           >
		                                            ${historicalRecord.resultDesc}
		                                            </td>
		                                            <#if historicalRecord.updated ??>
		                                            	<td>${historicalRecord.updated?string("yyyy-MM-dd HH:mm:ss")}</td>
		                                            <#else>
		                                            	<td></td>
		                                            </#if>
		                                        </tr>
	                                        </#list>
                                        <#else>
                                        		<tr>
		                                            <td>暂无记录</td>
		                                        </tr>
                                        </#if>
                                        </tbody>
                                    </table>
								<#if historicalRecordList ??>
                                    <div class="widget-foot clearfix">
                                        <ul class="pagination pull-right">
                                        	<#if (page.pageIndex>1)>
                                           		<li><a data-pagenum=${page.pageIndex-1}>Prev</a></li>
                                            </#if>
                                            <#list page.startNum..page.endNum as x>
                                            	<#if page.pageIndex==x>
                                            		<li><a  class="current" data-pagenum=${x?int}  style="color:red;">${x?int}</a></li>
                                            	<#else>
                                            		<li><a data-pagenum=${x}>${x?int}</a></li>
                                            	</#if>
                                            </#list>
                                            <#if (page.pageIndex<page.pageCount)>
                                            	<li><a data-pagenum=${page.pageIndex+1}>Next</a></li>
                                            </#if>
                                        </ul>
                                    </div>
								</#if>
                                </div>

                            </div>

                        </div>
                    </div>
 </div>

            <!-- Matter ends -->

        </div>

        <!-- Mainbar ends -->

    <!-- Content ends -->

    <!-- Footer starts -->
    <!-- Scroll to top -->
    <!-- Footer ends -->
    <script>seajs.use("historicalRecord/getHistoricalRecord.js");</script>
    <form action="${contextPath}/service/historicalRecord/getAllHistoricalRecord" method="post" id="myForm">
    	<input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber"/>
    	<input type="hidden" name="timeType" value="${timeType}" id="timeType"/>
    	<input type="hidden" name="requestType" value="${requestType}" id="requestType"/>
    </form>
</body>
</html>