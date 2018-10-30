<!DOCTYPE html>
<html lang="en">
<head>
    <title>门店管理-门店列表</title>
</head>

<body>
        <!-- Main bar -->
        <div class="mainbar">

            <!-- Page heading -->
            <div class="page-head">
                <h2 class="pull-left"><i class="icon-user"></i> 门店管理 > 门店列表</h2>
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
                                    <div class="pull-left mtop5">门店列表</div>
                                    <div class="widget-icons pull-right mlt10 mtop5">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                    <div class="pull-right col-md-6">
                                    <div class="col-lg-4">
                                        <select class="form-control" id="region_code">
                                        	<option value="">选择所属省份</option>
                                            <#list regionList as r>
                                            	<option value="${r.region_id}">${r.region_name}</option>
                                            </#list>
                                        </select>
                                   </div>
                                    <div class="form-group table-search">
                                        <input  id="searchName" class="form-control searchTxt" type="text" placeholder="门店名称" value="${searchName}">
                                     </div>
                                     <button id="mySubmit" class="btn btn-primary fl">查 询</button>
                                     <button class="btn btn-success pull-right btn-addStore" type="button">
                                            <i class="icon-plus"></i>
                                        		    新增门店
                                     </button>
                                    </div>
                                    
                                </div>
                                <div class="widget-content">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>门店名称</th>
                                            <th>所属省份</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if storeList ??>
                                        	<#list  storeList as store>
		                                        <tr>
		                                            <td>${store.name}</td>
		                                            <td>${store.province}</td>
		                                          <!--
		                                            <#if store.created ??>
		                                            	<td>${store.created?string("yyyy-MM-dd HH:mm:ss")}</td>
		                                            <#else>
		                                            	<td></td>
		                                            </#if>
		                                            -->
		                                            <td>
		                                                <button class="btn btn-xs btn-warning btn-update" data-id=${store.id} title="修改"><i class="icon-pencil" > 修改</i></button>
		                                                <button class="btn btn-xs btn-danger btn-delete" data-id=${store.id} title="删除"><i class="icon-del" > 删除</i></button>
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
								<#if storeList ??>
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
                        <div class="col-md-4"  style="display:none;"  id="updatestore">
                            <div class="widget alert-Box">

                                <div class="widget-head clearfix">
                                    <div class="pull-left">门店信息</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>

                                </div>
                                <div class="widget-content">
                                    <div class="padd">
                                        <div class="form quick-post">
                                            <!-- Edit profile form (not working)-->
                                            <form class="form-horizontal"  action="${contextPath}/service/organize/addStore">
                                            	<input type="hidden" name="id" id="id"/>
                                                <!-- Title -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">所属区域</label>
                                                    <div class="col-lg-9">
                                                        <select class="form-control" id="region_id">
                                                        <option value="">请选择区域</option>
	                                                        <#list regionList as r>
	                                                        	<option value="${r.region_id}">${r.region_name}</option>
	                                                        </#list>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">门店名称</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="请输入门店名称" name="name" id="name" maxlength="20"  value="">
                                                    </div>
                                                </div>

                                                <!-- Buttons -->
                                                <div class="form-group">
                                                    <!-- Buttons -->
                                                    <div class="col-lg-12 text-center">
                                                        <button class="btn btn-success mlt7" type="button" id="saveStore">
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
    <script>seajs.use("organize/getStoreList.js");</script>
    <form action="${contextPath}/service/organize/getStoreList" method="post" id="myForm">
    	<input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber"/>
    	<input type="hidden" name="name" value="${searchName}" id="storeName"/>
    	<input type="hidden" name="provinceCode" value="${searchProvinceCode}" id="provinceCode"/>
    </form>
</body>
</html>