<!DOCTYPE html>
<html lang="en">
<head>
    <title>合同管理-合同列表</title>
</head>

<body>
        <!-- Main bar -->
        <div class="mainbar">

            <!-- Page heading -->
            <div class="page-head">
                <h2 class="pull-left"><i class="icon-user"></i> 合同管理 >合同列表</h2>
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
                                    <div class="pull-left mtop5">合同列表</div>
                                    <div class="widget-icons pull-right mlt10 mtop5">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                    <div class="bread-crumb pull-right">
                                        <button class="btn btn-success btn-addContract" type="button">
                                            <i class="icon-plus"></i>
                                            		新增合同
                                        </button>
                                    </div>
                                </div>
                                <div class="widget-content">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>id</th>
                                            <th>合同名称</th>
                                            <th>合同类型</th>
                                            <th>楼盘名称</th>
                                            <th>我方单位</th>
                                            <th>对方单位</th>
                                            <th>合同生效时间</th>
                                            <th>合同终止时间</th>
                                            <th>合同总价</th>
                                            <th>合同月金额</th>
                                            <th>合同已付金额</th>
                                            <th>合同余额</th>
                                            <th>合同人数</th>
                                            <th>合同份数</th>
                                            <th>签约部门</th>
                                            <th>经办人</th>
                                            <th>联系方式</th>
                                            <th>合同履行情况</th>
                                            <th>创建时间</th>
                                            <th>修改时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if allContractList ??>
                                        	<#list  allContractList as contract>
		                                        <tr>
		                                            <td>${contract.id}</td>
													<td>${contract.contractName}</td>
													<td>${contract.type}</td>
													<td>${contract.detailType}</td>
													<td>${contract.towerName}</td>
													<td>${contract.company}</td>
													<td>${contract.serviceCompany}</td>
													<td>${contract.commencementDate}</td>
													<td>${contract.terminationDate}</td>
													<td>${contract.totalPrices}</td>
													<td>${contract.monthPrices}</td>
													<td>${contract.paidPrices}</td>
													<td>${contract.balance}</td>
													<td>${contract.peopleCount}</td>
													<td>${contract.copies}</td>
													<td>${contract.department}</td>
													<td>${contract.transactor}</td>
													<td>${contract.contact}</td>
													<td>${contract.status}</td>
													<td>${contract.created}</td>
													<td>${contract.updated}</td>
													<td>${contract.dataFlag}</td>
		                                            <td>
		                                            	<#if contract.dataFlag=="1">
		                                            		<button class="btn btn-xs btn-success btn-disable" data-contractId=${contract.id} data-state="0" title="点击禁用"><i class="icon-ban-circle" > 点击禁用</i></button>
		                                            	<#elseif contract.dataFlag !="1">
		                                            		<button class="btn btn-xs btn-danger btn-disable" data-contractId=${contract.id} data-state="1" title="点击启用"><i class="icon-ban-circle" > 点击启用</i></button>
		                                            	</#if>
		                                                <button class="btn btn-xs btn-warning btn-update" data-contractId=${contract.id} title="编辑"><i class="icon-pencil" > 编辑</i></button>
		                                                <button class="btn btn-xs btn-danger btn-delete" data-contractId=${contract.id} title="删除"><i class="icon-del" > 删除</i></button>
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
								<#if allContractList ??>
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
                        <div class="col-md-4"  style="display:none;"  id="updateContract">
                            <div class="widget alert-Box">

                                <div class="widget-head clearfix">
                                    <div class="pull-left">合同信息</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>

                                </div>
                                <div class="widget-content">
                                    <div class="padd">
                                        <div class="form quick-post">
                                            <!-- Edit profile form (not working)-->
                                            <form class="form-horizontal"  action="${contextPath}/service/employee/addEmployee">
                                            	<input type="hidden" name="employeeId" id="employeeId"/>
                                                <!-- Title -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">登录名</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="username" id="username" maxlength="20"  value="">
                                                    </div>
                                                </div>
                                                <!-- Content -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">姓名</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="realName" id="realName" value="" maxlength="10">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">身份证号</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" maxlength="18"  name="employIDCardNum" id="employIDCardNum" value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">手机号码</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="phone" id="phone" onkeyup="value=value.replace(/[^\d]/g,'')"  value="" maxlength="11">
                                                    </div>
                                                </div>
                                                <div class="form-group" style="display:none;" id="employeePassword">
                                                    <label class="control-label col-lg-3">员工密码</label>
                                                    <div class="col-lg-9">
                                                        <input type="password" class="form-control" name="password" id="password" value="">
                                                    </div>
                                                </div>
                                                <!-- 所属角色 -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">所属角色</label>
                                                    <div class="col-lg-9">
                                                        <select class="form-control" id="ddlRegType" onchange="btnChange(this[selectedIndex].value)";>
	                                                        <#list personas as p>
	                                                        	<option value="${p.personaId}">${p.personaName}</option>
	                                                        </#list>
                                                        </select>
                                                    </div>
                                                </div>
	                                            <!-- 所属门店-->    
												<div class="form-group storeIdDiv" >
                                                    <label class="control-label col-lg-3">所属门店</label>
                                                    <div class="col-lg-9">
                                                        <select class="form-control" id="storeIds" >
	                                                        <#list stores as s>
	                                                        	<option value="${s.id}">${s.name}</option>
	                                                        </#list>
                                                        </select>
                                                    </div>
                                                </div>

                                                <!-- Buttons -->
                                                <div class="form-group">
                                                    <!-- Buttons -->
                                                    <div class="col-lg-12 text-center">
                                                        <button class="btn btn-success mlt7" type="button" id="saveEmployee">
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
            </div>

            <!-- Matter ends -->

        </div>

        <!-- Mainbar ends -->
        <div class="clearfix"></div>

    <!-- Content ends -->

    <!-- Footer starts -->
    <!-- Scroll to top -->
    <!-- Footer ends -->
    <script>seajs.use("employee/getAllEmployees.js");</script>
      <script type="text/javascript">
	 function btnChange(values) {
		 if (values == "2") {
			 $(".storeIdDiv").show();
		 }else{
			 $(".storeIdDiv").hide();
		 }
	 }
	</script>
    <form action="${contextPath}/service/employee/getAllEmployees" method="post" id="myForm">
    	<input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber"/>
    </form>
</body>
</html>