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
                                     <button class="btn btn-success pull-right btn-csvDownload" type="button">
                                            		导出
                                        </button>
                                     <button class="marginR10 btn btn-success pull-right btn-addSalary" type="button">
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
                                         	<th>工资时间</th>
                                            <th>职员姓名</th>
                                            <th>楼盘名称</th>
                                            <th>职务名</th>
                                            <th>基本工资</th>
                                            <th>岗位津贴</th>
                                            <th>出勤</th>
                                            <th>加班费</th>
                                            <th>节假日</th>
                                            <th>其他</th>
                                            <th>应发工资</th>
                                            <th>个调税</th>
                                            <th>社保</th>
                                            <th>病事假</th>
                                            <th>其他扣款项</th>
                                            <th>扣款合计</th>
                                            <th>实发工资</th>
                                            <th>创建时间</th>
                                            <th>修改时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if allTowerList ??>
                                        	<#list  allSalary as salary>
		                                        <tr>
		                                        	<td>${salary.id}</td>
		                                            <td>${salary.salaryMonth}</td>
		                                            <td>${salary.staffName}</td>
		                                            <td>${salary.towerName}</td>
		                                            <td>${salary.positionName}</td>
		                                            <td>${salary.basePay}</td>
		                                            <td>${salary.allowance}</td>
		                                            <td>${salary.workDay}</td>
		                                            <td>${salary.overtimePay}</td>
		                                            <td>${salary.holiday}</td>
		                                            <td>${salary.other}</td>
		                                            <td>${salary.sendPay}</td>
		                                            <td>${salary.personTax}</td>
		                                            <td>${salary.socialSecurity}</td>
		                                            <td>${salary.askForLeave}</td>
		                                            <td>${salary.otherDeductPay}</td>
		                                            <td>${salary.deductTotalPay}</td>
		                                            <td>${salary.realPay}</td>
	                                            	<td>${salary.created}</td>
	                                            	<td>${salary.updated}</td>
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
                        <div class="col-md-4"  style="display:none;"  id="updateSalary">
                            <div class="widget alert-Box">
                                <div class="widget-head clearfix">
                                    <div class="pull-left">工资信息</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>

                                </div>
                                <div class="widget-content">
                                    <div class="padd">
                                        <div class="form quick-post">
                                            <!-- Edit profile form (not working)-->
                                            <form class="form-horizontal"  action="${contextPath}/service/salary/updateStaffSalary">
                                            	<input type="hidden" name="id" id="salaryId"/>
                                                <!-- Title -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">工资时间</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="salaryMonth" id="salaryMonth" maxlength="50"  value="" readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">职员姓名</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="staffName" id="staffName" maxlength="50"  value="" readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">楼盘名称</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="towerName" id="towerName" maxlength="50"  value="" readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">职务名</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control"  name="positionName" id="positionName" maxlength="50"  value="" readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">基本工资</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="请输入基本工资" name="basePay" id="basePay" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">岗位津贴</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="请输入岗位津贴" name="allowance" id="allowance" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">工作天数(出勤)</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="请输入工作天数(出勤)" name="workDay" id="workDay" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">加班费</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="请输入加班费" name="overtimePay" id="overtimePay" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">节假日费</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="请输入节假日费" name="holiday" id="holiday" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">其他</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="其他" name="other" id="other" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">应发工资</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="应发工资" name="sendPay" id="sendPay" maxlength="50"  value="" readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">个调税</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="请输入个调税" name="personTax" id="personTax" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">社保</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="请输入社保" name="socialSecurity" id="socialSecurity" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">病事假</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="病事假" name="askForLeave" id="askForLeave" maxlength="50"  value="" readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">其他扣款项</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="请输入其他扣款项" name="otherDeductPay" id="otherDeductPay" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">扣款合计</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="扣款合计" name="deductTotalPay" id="deductTotalPay" maxlength="50"  value="" readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">实发工资</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="实发工资" name="realPay" id="realPay" maxlength="50"  value="" readonly="readonly">
                                                    </div>
                                                </div>
                                                <!-- Buttons -->
                                                <div class="form-group">
                                                    <!-- Buttons -->
                                                    <div class="col-lg-12 text-center">
                                                        <button class="btn btn-success mlt7" type="button" id="saveSalary">
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
    	<input type="hidden" name="towerId" value="${searchTowerId}" id="searchTower"/>
    	<input type="hidden" name="time" value="${searchTime}" id="time"/>
    </form>
     <form action="${contextPath}/service/salary/csvDownLoadAllSalary" method="post" id="csvDownLoadAllSalary">
    	<input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber"/>
    	<input type="hidden" name="towerId" value="${searchTowerId}" id="searchTower"/>
    	<input type="hidden" name="time" value="${searchTime}" id="time"/>
    </form>
</body>
</html>