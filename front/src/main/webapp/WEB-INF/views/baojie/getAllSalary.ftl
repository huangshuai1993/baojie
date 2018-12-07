<!DOCTYPE html>
<html lang="en">
<head>
    <title>职员管理-工资列表</title>
</head>

<body>
        <!-- Main bar -->
        <div class="mainbar">

            <!-- Page heading -->
            <div class="page-head">
                <h2 class="pull-left"><i class="icon-user"></i> 职员管理 > 工资列表</h2>
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
                                    <div class="pull-left mtop5">工资列表</div>
                                    <div class="widget-icons pull-right mlt10 mtop5">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                    <div class="pull-right col-md-7">
                                    <p class='col-lg-2 inputTitle'>请选择时间：</p>
			                    	<div class="form-group mrt10 fl" style='position: relative;'>	
									<div class="input-append " id="datetimepicker1">
										<input class="Wdate form-control dtpicker validate[required] " type="text"  id="timeValue" value="${searchTime}" placeholder="工资时间" name="minTimeValue"  onclick="WdatePicker({autoPickDate:'true',dateFmt:'yyyy-MM'})" style="width: 150px;height:32px;" />
									</div>
									</div>
                                    <div class="col-lg-4">
                                        <select class="form-control" id="searchTowerIds">
                                        	<option value="">选择所属楼盘</option>
                                            <#list towerList as tower>
                                            	<option value="${tower.towerId}">${tower.towerName}</option>
                                            </#list>
                                        </select>
                                   	</div>
                                     <button id="mySubmit" class="btn btn-primary fl">查 询</button>
                                     <button class="btn btn-success pull-right btn-addSalary" type="button">
                                            <i class="icon-plus"></i>
                                        		    新增本月工资
                                     </button>
                                    </div>
                                </div>
                                <div class="widget-content">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                         	<th>id</th>
                                            <th>楼盘名称</th>
                                            <th>负责人名称</th>
                                            <th>地址</th>
                                            <th>合同人数</th>
                                            <th>实际到岗人数</th>
                                            <th>进场时间</th>
                                            <th>创建时间</th>
                                            <th>修改时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if allTowerList ??>
                                        	<#list  allTowerList as tower>
		                                        <tr>
		                                        	<td>${tower.towerId}</td>
		                                            <td>${tower.towerName}</td>
		                                            <td>${tower.functionaryName}</td>
		                                            <td>${tower.address}</td>
		                                            <td>${tower.peopleCount}</td>
		                                            <td>${tower.virtualCount}</td>
		                                            <td>${tower.approachTime}</td>
	                                            	<td>${tower.created}</td>
	                                            	<td>${tower.updated}</td>
		                                            <td>
		                                                <button class="btn btn-xs btn-warning btn-update" data-id=${tower.towerId} title="修改"><i class="icon-pencil" > 修改</i></button>
		                                                <button class="btn btn-xs btn-danger btn-delete" data-id=${tower.towerId} title="删除"><i class="icon-del" > 删除</i></button>
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
								<#if allTowerList ??>
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
                        <div class="col-md-4"  style="display:none;"  id="updateTower">
                            <div class="widget alert-Box">

                                <div class="widget-head clearfix">
                                    <div class="pull-left">楼盘信息</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>

                                </div>
                                <div class="widget-content">
                                    <div class="padd">
                                        <div class="form quick-post">
                                            <!-- Edit profile form (not working)-->
                                            <form class="form-horizontal"  action="${contextPath}/service/btower/addOrUpdateTower">
                                            	<input type="hidden" name="towerId" id="towerId"/>
                                                <!-- Title -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">楼盘名称</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="请输入楼盘名称" name="towerName" id="towerName" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">负责人名称</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="请输入负责人名称名称" name="functionaryName" id="functionaryName" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">地址</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="地址" name="address" id="address" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">合同人数</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="请输入合同人数" name="peopleCount" id="peopleCount" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">实际到岗人数</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="请输入实际到岗人数" name="virtualCount" id="virtualCount" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">进场时间</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="进场时间(2018-01-01)" name="approachTime" id="approachTime" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                <!-- Buttons -->
                                                <div class="form-group">
                                                    <!-- Buttons -->
                                                    <div class="col-lg-12 text-center">
                                                        <button class="btn btn-success mlt7" type="button" id="saveTower">
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
    <script>seajs.use("baojie/getAllSalary.js");</script>
    <form action="${contextPath}/service/salary/getAllSalary" method="post" id="myForm">
    	<input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber"/>
    	<input type="hidden" name="towerName" value="${searchName}" id="searchTowerName"/>
    	<input type="hidden" name="time" value="${searchTime}" id="time"/>
    </form>
</body>
</html>