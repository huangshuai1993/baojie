<!DOCTYPE html>
<html lang="en">
<head>
    <title>任务管理-任务列表</title>
</head>

<body>
		<!-- 门店主管页面 -->
        <!-- Main bar -->
        <div class="mainbar">

            <!-- Page heading -->
            <div class="page-head">
                <h2 class="pull-left"><i class="icon-user"></i> 任务管理 > 任务列表</h2>
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
                                    <div class="pull-left mtop5">任务列表</div>
                                    <div class="widget-icons pull-right mlt10 mtop5">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                    <div class="pull-right col-md-11">
                                    <div class="form-group table-search">
                                        <input  id="searchName"  class="form-control searchTxt" type="text" placeholder="客户名称" value="${searchName}">
                                     </div>
                                     <div class="form-group table-search">
                                        <input  id="searchMobile" style='width: 128px;' class="form-control searchTxt" type="text" maxlength="11" placeholder="手机号码" value="${searchMobile}">
                                     </div>
                                     <span style="float:left;margin:5px 0px 4px -20px;font-size:14px;">所属门店</span>
                                	<div class="col-lg-2" style='width: 160px;'>
                                    <select class="form-control" id="store_id" disabled>
                                    	<#list storeList as store>
                                    		<option value="${store.id}">${store.name}</option>
										</#list>
                                    </select>
                               		</div>
                                    <span style="float:left;margin:5px 0px 4px 1px;font-size:14px;">外访人员</span>
                                    <div class="col-lg-2" style='width: 110px;'>
                                    <select class="form-control" id="work_id">
                                    	<option value="0">全部</option>
                                    	<#list workForm as work>
                                    		<option value="${work.id}">${work.name}</option>
										</#list>
                                    </select>
                                   </div>
                                    <span style="float:left;margin:5px 0px 4px 1px;font-size:14px;">状态</span>
                                    <div class="col-lg-2" style='width: 110px;'>
                                        <select class="form-control" id="status_code">
                                        		<option value="10">全部</option>
                                        	<#list taskStatus as status>
                                        		<option value="${status.getId()}">${status.getDesc()}</option>
											</#list>
                                        </select>
                                   </div>
                                     <button id="mySubmit" class="btn btn-primary fl">查 询</button>
                                     <button class="btn btn-success pull-right btn-addTask" type="button">
                                            <i class="icon-plus"></i>
                                        		    新增任务
                                     </button>
                                    </div>
                                    
                                </div>
                                <div class="widget-content">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>客户姓名</th>
                                            <th>客户手机号</th>
                                            <th>居住地址</th>
                                            <th>借款金额(元)</th>
                                            <th>借贷产品</th>
                                            <th>录入时间</th>
                                            <th>外访人员</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if taskList ??>
                                        	<#list  taskList as task>
		                                        <tr>
		                                            <td>${task.name}</td>
		                                            <td>${task.mobile}</td>
		                                            <td>${task.homeAddress}</td>
		                                            <td>${task.amount}</td>
		                                            <td>${task.productName}</td>
		                                             <#if task.created ??>
		                                            	<td>${task.created?string("yyyy-MM-dd HH:mm:ss")}</td>
		                                            <#else>
		                                            	<td></td>
		                                            </#if>
		                                            <td>${task.empName}</td>
		                                           <td>${task.taskStatusName}</td>
		                                            <td>
		                                            	<#if task.taskStatus=="0">
		                                            		<button class="btn btn-xs btn-success btn-distribution" data-id='${task.id}' data-storeId='${task.storeId}' data-province='${task.storeProvinceCode}'  title="分配">分配</button>
		                                            	</#if>
		                                                <a href="${contextPath}/service/btask/getTaskInfo?id=${task.id}">
		                                                	<button class="btn btn-xs btn-warning btn-update" data-id=${task.id} title="详情">详情</button>
		                                            	</a>
		                                            	<#if (task.taskStatus < 3)>
		                                            		<button class="btn btn-xs btn-danger btn-disable" data-id=${task.id} title="放弃">放弃</button>
		                                            	</#if>
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
								<#if taskList ??>
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
                        <div class="col-md-10"  style="display:none;"  id="addTask">
                            <div class="widget alert-Box">

                                <div class="widget-head clearfix">
                                    <div class="pull-left">任务信息</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>

                                </div>
                                <div class="widget-content">
                                    <div class="padd">
                                        <div class="form quick-post">
                                            <!-- Edit profile form (not working)-->
                                            <form class="form-horizontal"  action="">
                                            	<input type="hidden" name="id" id="id"/>
                                                <!-- Title -->
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	客户姓名:
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <input type="text" class="form-control" name="name" id="name" maxlength="20"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	身份证号:
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <input type="text" class="form-control" name="idcardNo" id="idcardNo" maxlength="18"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right'>
                                                    	性别:
                                                    </div>
                                                    <div class="col-lg-4" id="gender" >
                                                    	<input type="radio" name="gender" value="1" class="form-radio" disabled>男
														<input type="radio" name="gender" value="0" class="form-radio" disabled>女
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	出生日期:
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <input type="text" class="form-control" name="birthday" id="birthday"   value=""  readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	客户手机号:
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <input type="text" class="form-control" name="mobile" id="mobile" maxlength="11"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	婚姻状况:
                                                    </div>
                                                     <div class="col-lg-7" id="marriage">
                                                    	<input type="radio" name="marriage" value="0" class="form-radio">未婚
														<input type="radio" name="marriage" value="1" class="form-radio">已婚
														<input type="radio" name="marriage" value="2" class="form-radio">离异
														<input type="radio" name="marriage" value="3" class="form-radio">丧偶
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	月收入:
                                                    </div>
                                                    <div class="col-lg-4 par-unit">
                                                        <input type="text" class="form-control" name="income" id="income" maxlength="30"  value="">
                                                        <span class='son-unit'>元</span>
                                                    </div>
                                                </div>
												<div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	是否有担保人:
                                                    </div>
                                                    <div class="col-lg-7" id="hasCautioner">
                                                    	<input type="radio" name="hasCautioner" value="1" class="form-radio">是
														<input type="radio" name="hasCautioner" value="0" class="form-radio">否
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	担保人姓名:
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <input type="text" class="form-control" name="cautionerName" id="cautionerName" maxlength="20"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	担保人身份证号:
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <input type="text" class="form-control" name="cautionerIdcardNo" id="cautionerIdcardNo" maxlength="18"  value="">
                                                    </div>
                                                </div>
                                                 <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	和担保人关系:
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <input type="text" class="form-control" name="cautionerRelation" id="cautionerRelation" maxlength="18"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	居住地址:
                                                    </div>
                                                    <div class="col-lg-10">
                                                        <div class="col-lg-2" style='padding-left:0;'>
                                                        	<select class="form-control" id="homeProvinceCode" onchange="provinceChange('#homeProvinceCode','#homeCityCode','#homeRegionCode')">
	                                                        	<option value="">选择省份</option>
	                                                        	<#list regionList as province>
	                                                        		<option value="${province.region_id}">${province.region_name}</option>
	                                                        	</#list>
                                                        	</select>
                                                        </div>	
                                                        <div class="col-lg-2" style='padding-left:0;'>
	                                                        <select class="form-control" id="homeCityCode" onchange="cityChange('#homeCityCode','#homeRegionCode')">
	                                                        	<option value="">选择城市</option>
	                                                        </select>
	                                                    </div>
	                                                    <div class="col-lg-2" style='padding-left:0;'>
	                                                        <select class="form-control" id="homeRegionCode">
	                                                        	<option value="">选择区县</option>
	                                                        </select>
	                                                    </div>
	                                                    <div class="col-lg-4" style='padding-left:0;'>
                                                        	<input type="text" placeholder="请输入详细地址" class="form-control" name="homeAddress" id="homeAddress" maxlength="30"  value="">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	工作地址:
                                                    </div>
                                                    <div class="col-lg-10">
                                                        <div class="col-lg-2" style='padding-left:0;'>
                                                        	<select class="form-control" id="workProvinceCode" onchange="provinceChange('#workProvinceCode','#workCityCode','#workRegionCode')">
	                                                        	<option value="">选择省份</option>
	                                                        	<#list regionList as province>
	                                                        		<option value="${province.region_id}">${province.region_name}</option>
	                                                        	</#list>
                                                        	</select>
                                                        </div>	
                                                        <div class="col-lg-2" style='padding-left:0;'>
	                                                        <select class="form-control" id="workCityCode" onchange="cityChange('#workCityCode','#workRegionCode')">
	                                                        	<option value="">选择城市</option>
	                                                        </select>
	                                                    </div>
	                                                    <div class="col-lg-2" style='padding-left:0;'>
	                                                        <select class="form-control" id="workRegionCode">
	                                                        	<option value="">选择区县</option>
	                                                        </select>
	                                                    </div>
	                                                    <div class="col-lg-4" style='padding-left:0;'>
                                                        	<input type="text" placeholder="请输入详细地址" class="form-control" name="workAddress" id="workAddress" maxlength="30"  value="">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	借款产品:
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <select class="form-control" id="product">
                                                        	<option value="0">兴业贷</option>
                                                        	<option value="1">英才贷</option>
                                                        	<option value="2">诚薪贷</option>
                                                        	<option value="3">诚薪楼贷</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	借款金额:
                                                    </div>
                                                    <div class="col-lg-4 par-unit">
                                                        <input type="text" class="form-control" name="amount" id="amount" maxlength="18"  value="">
                                                        <span class='son-unit'>元</span>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	客户来源:
                                                    </div>
                                                    <div class="col-lg-4">
                                                       <select class="form-control" id="source" >
	                                                        	<option value="0">线下</option>
	                                                        	<option value="1">佰宝金服</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	分配外访人员:
                                                    </div>
                                                    <div class="col-lg-10">
                                                        <div class="col-lg-2" style='padding-left:0;'>
	                                                        <select class="form-control" id="region_task" disabled>
	                                                    		<#list regionAndStore as province>
	                                                        		<option value="${province.region_id}">${province.region_name}</option>
	                                                        	</#list>
	                                                        </select>
	                                                    </div>
	                                                    <div class="col-lg-3" style='padding-left:0;'>
	                                                        <select class="form-control" id="storeId" disabled>
		                                                        <#list storeList as store>
	                            									<option value="${store.id}">${store.name}</option>
																</#list>
	                                                        </select>
	                                                    </div>
	                                                    <div class="col-lg-2" style='padding-left:0;'>
	                                                        <select class="form-control" id="empId" onchange="empChange(this.value)">
	                                                        <option value="0">请选择</option>
								                            	<#list workForm as work>
								                            		<option value="${work.id}">${work.name}</option>
																</#list>
	                                                        </select>
	                                                    </div>
                                                    </div>
                                                    <div class="col-lg-10 taskCountDiv" style="display:none;">
								                      	  <h6 style="font-weight: bold; margin-left: 131px;">所选的外访人员当前任务量为:<span id="taskCount" style="color:red;"></span>个.</h6>
								                    </div>
                                                </div>
                                                <!-- Buttons -->
                                                <div class="form-group">
                                                    <!-- Buttons -->
                                                    <div class="col-lg-12 text-center">
                                                        <button class="btn btn-success mlt7" type="button" id="saveTask">
                                                            <i class="icon-ok"></i>
                                                            	确定
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
		<div id="task-remove" class="modal fade" tabindex="-1" role="dialog" style="top: 70px;z-index:2041;">
			<div class="modal-dialog" style="width: 600px;">
				<input type="hidden" id="taskId"/>
	            <div class="modal-content">
	              <div class="modal-header">
	                <h4 class="modal-title">填写放弃任务原因</h4>
	              </div>
	              <div class="modal-body">
	              	<textarea id="memo" style="width: 500px;margin: 0 auto;display: block;height: 120px;" maxlength='200'></textarea >
	              </div>
	              <div class="modal-footer">
	              	<button type="button" class="btn btn-primary" id="removeTask">确定</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	              </div>
	            </div>
	        </div>
		</div>
		<div id="task-distribution" class="modal fade" tabindex="-1" role="dialog" style="top: 70px;z-index:2041;">
			<div class="modal-dialog" style="width: 600px;">
				<input type="hidden" id="taskId_distribution"/>
	            <div class="modal-content">
	              <div class="modal-header">
	                <h4 class="modal-title">分配外访人员</h4>
	              </div>
	              <div class="modal-body" style='height:100px;'>
	              	 <div class="col-lg-10" style='margin-top: 24px;'>
                        <div class="col-lg-4" style='padding-left:0;'>
                            <select class="form-control" id="region_task_modal" disabled>
                        		<#list regionAndStore as province>
                            		<option value="${province.region_id}">${province.region_name}</option>
                            	</#list>
                            </select>
                        </div>
                        <div class="col-lg-4" style='padding-left:0;' >
                            <select class="form-control" id="storeId_modal" disabled>
                           		 <#list storeList as store>
                            		<option value="${store.id}">${store.name}</option>
								</#list>
                            </select>
                        </div>
                        <div class="col-lg-4" style='padding-left:0;'>
                            <select class="form-control" id="empId_modal" onchange="empChanges(this.value)">
                            	<option value="0">请选择</option>
                            	<#list workForm as work>
                            		<option value="${work.id}">${work.name}</option>
								</#list>
                            </select>
                        </div>
                    </div>
                    <div class="col-lg-10 taskCountDivs" style="display:none;">
              	  	   <h6 style="font-weight: bold;">所选的外访人员当前任务量为:<span id="taskCounts" style="color:red;"></span>个.</h6>
           		    </div>
	              </div>
	              <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	              	<button type="button" class="btn btn-primary" id="distributionTask">确定</button>
	              </div>
	            </div>
	        </div>
		</div>
        <!-- Mainbar ends -->
        <div class="clearfix"></div>

    <!-- Content ends -->

    <!-- Footer starts -->
    <form action="${contextPath}/service/btask/getTaskList" method="post" id="myForm">
    	<input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber"/>
    	<input type="hidden" name="name" value="${searchName}" id="TaskName"/>
    	<input type="hidden" name="taskStatus" value="${searchStatus}" id="statusCode"/>
    	<input type="hidden" name="mobile" value="${searchMobile}" id="search_Mobile"/>
    	<input type="hidden" name="searchStoreId" value="${searchStoreId}" id="searchStoreId"/>
    	<input type="hidden" name="workId" value="${searchWorkId}" id="searchWorkId"/>
    </form>
    <script>
  		 function checkField(storeVal,id){
  		 	var checkedArray =[];
  		 	checkedArray.push("<option value=''>"+"请选择"+"</option>");
	       <#if regionAndStore??>
	            <#list regionAndStore as item>
	            	if(storeVal=="${item.region_id}"){
	            		<#list item.storeList as store>
	            			checkedArray.push("<option value='${store.id}'>"+"${store.name}"+"</option>");
	            		</#list>
	            	}
	            </#list>
	       </#if>
	       var stores=checkedArray.join("");
	      $(id).html(stores);
  		 }
  	</script>  
    <script>seajs.use("task/getTaskLists.js");seajs.use("common/regionChange.js");</script>
</body>
</html>