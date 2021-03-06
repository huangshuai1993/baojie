<!DOCTYPE html>
<html lang="en">
<head>
    <title>配置管理-配置项明细列表</title>
</head>

<body>
        <!-- Main bar -->
        <div class="mainbar">

            <!-- Page heading -->
            <div class="page-head">
                <h2 class="pull-left"><i class="icon-user"></i> 配置管理 > 配置项明细列表</h2>
                <div class="clearfix"></div>

            </div>
            <!-- Page heading ends -->
            <!-- Matter -->
            <div class="matter">
                <div class="container">
                    <!-- Table -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="widget">

                                <div class="widget-head clearfix">
                                    <div class="pull-left mtop5">配置项明细列表</div>
                                    <div class="widget-icons pull-right mlt10 mtop5">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                    <div class="pull-right col-md-7">
                                    <div class="col-lg-3">
                                        <select class="form-control" id="searchConfig">
                                        	<option value="">选择所属配置项</option>
                                            <#list configList as config>
                                            	<option value="${config.configuration}">${config.configDesc}</option>
                                            </#list>
                                        </select>
                                   	</div>
                                    <div class="form-group table-search">
                                        <input  id="searchName" class="form-control searchTxt" type="text" placeholder="配置项明细描述" value="${configDetailDesc}">
                                     </div>
                                     <button id="mySubmit" class="btn btn-primary fl">查 询</button>
                                     <button class="marginR10 btn btn-success pull-right btn-addConfig" type="button">
                                            <i class="icon-plus"></i>
                                        		    新增配置项明细
                                     </button>
                                    </div>
                                </div>
                                <div class="widget-content">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                         	<th>id</th>
                                            <th>配置名称</th>
                                            <th>配置明细描述</th>
                                            <th>配置明细状态</th>
                                            <th>备注</th>
                                            <th>创建时间</th>
                                            <th>修改时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if allConfigDetail ??>
                                        	<#list  allConfigDetail as config>
		                                        <tr>
		                                        	<td>${config.id}</td>
		                                            <td>${config.configuration}</td>
		                                            <td>${config.configDetailDesc}</td>
		                                            <td>${config.configValue}</td>
		                                            <td>${config.memo}</td>
	                                              	<#if config.created ??>
	                                            	<td>${config.created?string("yyyy-MM-dd HH:mm:ss")}</td>
		                                            <#else>
		                                            	<td></td>
		                                            </#if>
													<#if config.updated ??>
		                                            	<td>${config.updated?string("yyyy-MM-dd HH:mm:ss")}</td>
		                                            <#else>
		                                            	<td></td>
		                                            </#if>
		                                            <td>
		                                                <button class="btn btn-xs btn-warning btn-update" data-id=${config.id} title="修改"><i class="icon-pencil" > 修改</i></button>
		                                                <button class="btn btn-xs btn-danger btn-delete" data-id=${config.id} title="删除"><i class="icon-del" > 删除</i></button>
		                                            </td>
		                                        </tr>
	                                        </#list>
                                        <#else>
                                        		<tr>
		                                            <td>暂无记录</td>
		                                        </tr>
                                        </#if>
                                        </tbody>
                                    </table>
								<#if allConfigDetail ??>
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
                    <div class="row">
                        <div class="col-md-4"  style="display:none;"  id="updateConfig">
                            <div class="widget alert-Box">
                                <div class="widget-head clearfix">
                                    <div class="pull-left">配置项明细信息</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>

                                </div>
                                <div class="widget-content">
                                    <div class="padd">
                                        <div class="form quick-post">
                                            <!-- Edit profile form (not working)-->
                                            <form class="form-horizontal"  action="${contextPath}/service/config/addOrUpdateConfigDetail">
                                            	<input type="hidden" name="id" id="id"/>
                                                <!-- Title -->
                                                <!-- 所属配置-->    
												<div class="form-group storeIdDiv" >
                                                    <label class="control-label col-lg-3">所属配置项</label>
                                                    <div class="col-lg-9">
                                                        <select class="form-control" id="configuration" >
	                                                        <#list configList as config>
	                                                        	<option value="${config.configuration}">${config.configDesc}</option>
	                                                        </#list>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">配置项明细描述</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="配置项明细描述" name="configDetailDesc" id="configDetailDesc" maxlength="30"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">配置项明细值</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="默认生成状态码" name="configValue" id="configValue" readonly="readonly" maxlength="2"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">备注</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="" name="memo" id="memo" maxlength="30"  value="">
                                                    </div>
                                                </div>
                                                <!-- Buttons -->
                                                <div class="form-group">
                                                    <!-- Buttons -->
                                                    <div class="col-lg-12 text-center">
                                                        <button class="btn btn-success mlt7" type="button" id="saveConfig">
                                                            <i class="icon-ok"></i>
                                                          	  保存
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                </div>
            </div>

            <!-- Matter ends -->

        </div>

        <!-- Mainbar ends -->
        <div class="clearfix"></div>

    <!-- Content ends -->

    <!-- Footer starts -->
    <!-- Scroll to top -->
    <!-- Footer ends -->
    <script>seajs.use("baojie/getAllConfigDetail.js");</script>
    <form action="${contextPath}/service/config/getAllConfigDetail" method="post" id="myForm">
    	<input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber"/>
    	<input type="hidden" name="configDetailDesc" value="${configDetailDesc}" id="SeachconfigDetailDesc"/>
    	<input type="hidden" name="configuration" value="${configuration}" id="SeachConfiguration"/>
    </form>
</body>
</html>