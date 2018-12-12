<!DOCTYPE html>
<html lang="en">
<head>
    <title>物料管理-物料列表</title>
</head>

<body>
        <!-- Main bar -->
        <div class="mainbar">

            <!-- Page heading -->
            <div class="page-head">
                <h2 class="pull-left"><i class="icon-user"></i> 物料管理 > 物料列表</h2>
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
                                    <div class="pull-left mtop5">物料列表</div>
                                    <div class="widget-icons pull-right mlt10 mtop5">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                   	<div class="pull-right col-md-6">
                                    <div class="col-lg-4">
                                        <select class="form-control" id="searchTowerIds">
                                        	<option value="">选择所属楼盘</option>
                                            <#list towerList as tower>
                                            	<option value="${tower.towerId}">${tower.towerName}</option>
                                            </#list>
                                        </select>
                                   	</div>
                                      <button id="mySubmit" class="btn btn-primary fl">查 询</button>
                                        <button class="btn btn-success pull-right btn-addMaterial" type="button">
                                            <i class="icon-plus"></i>
                                            		新进物料
                                        </button>
                                    </div>
                                </div>
                                <div class="widget-content">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                        	<th>id</th>
                                            <th>物料名称</th>
                                          	<th>物料类型</th>
                                          	<th>单价</th>
                                          	<th>数量</th>
                                            <th>总价</th>
                                            <th>进货时间</th>
                                            <th>所属楼盘</th>
                                            <th>创建时间</th>
                                            <th>修改时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if allMaterial ??>
                                        	<#list  allMaterial as material>
		                                        <tr>
		                                            <td>${material.materialId}</td>
		                                            <td>${material.materialName}</td>
		                                            <td>${material.typeName}</td>
		                                            <td>${material.price}</td>
		                                             <td>${material.count}</td>
		                                             <td>${material.totalPrice}</td>
		                                             <td>${material.purchaseTime}</td>
		                                             <td>${material.towerName}</td>
		                                             <td>${material.created}</td>
		                                             <td>${material.updated}</td>
		                                            <td>
		                                                <button class="btn btn-xs btn-warning btn-update" data-id=${material.materialId} title="编辑"><i class="icon-pencil" > 编辑</i></button>
		                                                <button class="btn btn-xs btn-danger btn-delete" data-i=${material.materialId} title="删除"><i class="icon-del" > 删除</i></button>
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
								<#if allPosition ??>
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
                        <div class="col-md-4"  style="display:none;"  id="updatePosition">
                            <div class="widget alert-Box">
                                <div class="widget-head clearfix">
                                    <div class="pull-left">职称信息</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                </div>
                                <div class="widget-content">
                                    <div class="padd">
                                        <div class="form quick-post">
                                            <!-- Edit profile form (not working)-->
                                            <form class="form-horizontal"  action="${contextPath}/service/position/addOrUpdatePosition">
                                            	<input type="hidden" name="positionId" id="positionId"/>
                                                <!-- Title -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">职位名称</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="positionName" id="positionName" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                 <!-- 所属楼盘-->    
												<div class="form-group storeIdDiv" >
                                                    <label class="control-label col-lg-3">所属楼盘</label>
                                                    <div class="col-lg-9">
                                                        <select class="form-control" id="towerIds" >
	                                                        <#list towerList as tower>
	                                                        	<option value="${tower.towerId}">${tower.towerName}</option>
	                                                        </#list>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">基本工资</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" maxlength="18"  name="basePay" id="basePay" value="" onkeyup="value=value.replace(/[^\d]/g,'')">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">岗位津贴</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="allowance" id="allowance" onkeyup="value=value.replace(/[^\d]/g,'')"  value="" maxlength="11">
                                                    </div>
                                                </div>

                                                <!-- Buttons -->
                                                <div class="form-group">
                                                    <!-- Buttons -->
                                                    <div class="col-lg-12 text-center">
                                                        <button class="btn btn-success mlt7" type="button" id="savePosition">
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
    <script>seajs.use("baojie/getAllPosition.js");</script>
      <script type="text/javascript">
	 function btnChange(values) {
		 if (values == "2") {
			 $(".storeIdDiv").show();
		 }else{
			 $(".storeIdDiv").hide();
		 }
	 }
	</script>
    <form action="${contextPath}/service/position/getAllPosition" method="post" id="myForm">
    	<input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber"/>
    	<input type="hidden" name="towerId" value="${searchTowerId}" id="searchTower"/>
    </form>
</body>
</html>