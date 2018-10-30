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
                <div class="invest-biaolist inverst-bor" style='height: 180px;'>
                    <div class='row mt20'>
                    	<div class='col-md-12'>
		                    <p class='col-lg-2 inputTitle'>请输入用户名：</p>
		                    <div class="form-group">
								<input class="form-control"  type="text" placeholder="用户登录名" id="usernameValue" value="${username}" style='width: 180px;'>
							</div>
		              	</div>
                    </div>
                    <div class='row'>
                    	<div class='col-md-12'>
                    		<p class='col-lg-2 inputTitle'>请选择时间：</p>
		                    <div class="form-group mrt10 fl" style='position: relative;'>	
								<div class="input-append " id="datetimepicker1">
									<input class="form-control dtpicker validate[required]" type="text"  id="minTimeValue" value="${minTime}" placeholder="开始时间" name="minTimeValue"  onclick="WdatePicker({autoPickDate:'true',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'maxTimeValue\')}'})" style="width: 150px;" />
									<span class="add-on">
										<i class="btn btn-info icon-calendar" data-date-icon="icon-calendar" data-time-icon="icon-time"></i>
									</span>
								</div>
							</div>
                			<span class='mrt10 fl timeCenter'>至</span>
                			<div class="form-group fl" style='position: relative;'>	
								<div class="input-append " id="datetimepicker2">
									<input class="form-control dtpicker validate[required]" type="text" value="${maxTime}" placeholder="结束时间" id="maxTimeValue" name="maxTimeValue" onclick="WdatePicker({autoPickDate:'true',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'minTimeValue\')}'})" style="width: 150px;" />
									<span class="add-on">
										<i class="btn btn-info icon-calendar" data-date-icon="icon-calendar" data-time-icon="icon-time"></i>
									</span>
								</div>
							</div>
	                    </div>
                    </div>
                   <div class='row'>
                	<div class='col-md-12'>    
                		<button class="btn btn-primary" type="button" id="searcher" style='margin-left: 140px;'>查 询</button>
                		</div>
	              	</div>  
                </div>
                    <!-- Table -->
                   <#if historicalRecordList ??>
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
                                        	<#list  historicalRecordList as historicalRecord>
		                                        <tr>
		                                            <td>
		                                            <#if historicalRecord.result == 0>
		                                            <a href="${contextPath}/service/${historicalRecord.requestUrl}?swift_number=${historicalRecord.swiftnumber}">
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
                                        </tbody>
                                    </table>
                                   <#else>
                                   <div style="">
                                     	<h2>查询错误:${errMsg}</h2>
                                    <div> 
                                   </#if> 
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
    <script>seajs.use("historicalRecord/queryRecord.js");</script>
    <form action="${contextPath}/service/historicalRecord/queryRecord" method="post" id="myForm">
    	<input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber"/>
    	<input type="hidden" name="username" value="${username}" id="username"/>
    	<input type="hidden" name="minTime" value="${minTime}" id="minTime"/>
    	<input type="hidden" name="maxTime" value="${maxTime}" id="maxTime"/>
    </form>
</body>
</html>