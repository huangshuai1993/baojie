<!DOCTYPE html>
<html lang="en">
<head>
    <title>财务管理-客户缴费记录</title>
</head>

<body>
        <!-- Main bar -->
        <div class="mainbar">
            <!-- Page heading -->
            <div class="page-head">
                <h2 class="pull-left"><i class="icon-user"></i> 财务管理-客户缴费记录</h2>
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
                                    <div class="pull-left mtop5">缴费记录</div>
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
                                	<div class="col-lg-2" style='width: 150px;'>
                                    <select class="form-control" id="store_id" onchange="storeChange(this.value,'#work_id')">
                                    		<option value="0">全部</option>
                                    	<#list storeList as store>
                                    		<option value="${store.id}">${store.name}</option>
										</#list>
                                    </select>
                               		</div>
                                    <span style="float:left;margin:5px 0px 4px 1px;font-size:14px;">外访人员</span>
                                    <div class="col-lg-2" style='width: 120px;'>
                                    <select class="form-control" id="work_id">
                                    	<option value="0">全部</option>
                                    </select>
                                   </div>
                                    <span style="float:left;margin:5px 0px 4px 1px;font-size:14px;">状态</span>
                                    <div class="col-lg-2" style='width: 135px;'>
                                        <select class="form-control" id="status_code">
                                        		<option value="10">全部</option>
                                        	<#list taskStatus as status>
                                        		<option value="${status.getId()}">${status.getDesc()}</option>
											</#list>
                                        </select>
                                    </div>
                                     <button id="mySubmit" class="btn btn-primary fl">查 询</button>
                                    </div>
                                </div>
                                <div class="widget-content">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>客户姓名</th>
                                            <th>客户手机号</th>
                                            <th>借款金额(元)</th>
                                            <th>借贷产品</th>
                                            <th>门店</th>
                                            <th>外访人员</th>
											<th>缴费时间</th>
											<th>支付方式</th>
                                            <th>缴费金额(元)</th>
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
		                                            <td>${task.amount}</td>
		                                            <td>${task.productName}</td>
		                                            <td>${task.storeName}</td> 
		                                            <td>${task.empName}</td>
		                                            <#if task.payDated ??>
		                                            	<td>${task.payDated?string("yyyy-MM-dd HH:mm:ss")}</td>
		                                            <#else>
		                                            	<td></td>
		                                            </#if>
		                                            <td>${task.channel}</td>
		                                            <td>${task.payAmount}</td>
		                                            <td>${task.taskStatusName}</td>
		                                            <td>
		                                            	<#if task.taskStatus=="5">
		                                            		<button class="btn btn-xs btn-success btn-disable" data-id='${task.id}' title="退款">退款</button>
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
               </div>
            <!-- Matter ends -->
        </div>
        <div id="task-remove" class="modal fade" tabindex="-1" role="dialog" style="top: 80px;z-index:2041;">
			<div class="modal-dialog" style="width: 530px;">
				<input type="hidden" id="taskId"/>
	            <div class="modal-content">
	              <div class="modal-header">
	                <h4 class="modal-title">退款</h4>
	              </div>
	              <div class="modal-body">
                    <div class='col-lg-12'>
                    	<label class="control-label col-lg-3 lineH32" style='text-align:right;'>交易密码</label>
	                    <div class="col-lg-7" stype="margin-left:0;">
	                        <input type="password"  class="form-control" name="payPwd" id="payPwd" maxlength="18"  value="">
	                    </div>
	                    <div style='clear:both;'></div>
                    </div>
	              </div>
	              <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	                <button type="button" class="btn btn-primary" id="btn-returnPayBtn">确定</button>
	              </div>
	            </div>
	        </div>
		</div>
    <!-- Content ends -->
    <div class="clearfix"></div>
    <!-- Footer starts -->
    <form action="${contextPath}/service/finance/getTaskList" method="post" id="myForm">
    	<input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber"/>
    	<input type="hidden" name="name" value="${searchName}" id="TaskName"/>
    	<input type="hidden" name="taskStatus" value="${searchStatus}" id="statusCode"/>
    	<input type="hidden" name="mobile" value="${searchMobile}" id="search_Mobile"/>
    	<input type="hidden" name="searchStoreId" value="${searchStoreId}" id="searchStoreId"/>
    	<input type="hidden" name="workId" value="${searchWorkId}" id="searchWorkId"/>
    </form>
    <script>
  	$(function(){
  			var $element={
  				region_0 : $("#region_task option:first")
  			}
  			var region_checkedval=$element.region_0.val();
  			$element.region_0.prop("selected",true);
  			checkField(region_checkedval,'#storeId');
  		});
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
    <script>seajs.use("finance/getPayInfoList.js");seajs.use("common/regionChange.js");</script>
</body>
</html>