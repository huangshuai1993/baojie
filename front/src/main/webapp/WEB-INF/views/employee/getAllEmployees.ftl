<!DOCTYPE html>
<html lang="en">
<head>
    <title>员工管理-员工列表</title>
</head>

<body>
        <!-- Main bar -->
        <div class="mainbar">

            <!-- Page heading -->
            <div class="page-head">
                <h2 class="pull-left"><i class="icon-user"></i> 员工管理 > 员工列表</h2>
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
                                    <div class="pull-left mtop5">员工列表</div>
                                    <div class="widget-icons pull-right mlt10 mtop5">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                    <div class="bread-crumb pull-right">
                                        <button class="btn btn-success btn-addEmployee" type="button">
                                            <i class="icon-plus"></i>
                                            新增员工
                                        </button>
                                    </div>
                                </div>
                                <div class="widget-content">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>姓名</th>
                                            <th>登录名</th>
                                            <th>角色</th>
                                            <th>手机号码</th>
                                            <th>注册时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if employeeList ??>
                                        	<#list  employeeList as employee>
		                                        <tr>
		                                            <td>${employee.realName}</td>
		                                            <td>${employee.username}</td>
		                                            <td>${employee.persoaName}</td>
		                                            <td>${employee.phone}</td>
		                                            <#if employee.empRegTime ??>
		                                            	<td>${employee.empRegTime?string("yyyy-MM-dd HH:mm:ss")}</td>
		                                            <#else>
		                                            	<td></td>
		                                            </#if>
		                                            <td>
		                                            	<#if employee.empStatus=="1" && employee.employeeType !="1">
		                                            		<button class="btn btn-xs btn-success btn-disable" data-custNo=${employee.custNo} data-state="2" title="点击禁用"><i class="icon-ban-circle" > 点击禁用</i></button>
		                                            	<#elseif employee.employeeType !="1">
		                                            		<button class="btn btn-xs btn-danger btn-disable" data-custNo=${employee.custNo} data-state="1" title="点击启用"><i class="icon-ban-circle" > 点击启用</i></button>
		                                            	</#if>
		                                                <button class="btn btn-xs btn-warning btn-update" data-custNo=${employee.custNo} title="编辑"><i class="icon-pencil" > 编辑</i></button>
		                                                <#if employee.employeeType !="1">
		                                                <button class="btn btn-xs btn-danger btn-delete" data-custNo=${employee.custNo} title="删除"><i class="icon-del" > 删除</i></button>
		                                                </#if>
		                                                <button class="btn btn-xs btn-danger btn-password" data-custNo=${employee.custNo} title="修改密码"><i class="icon-ok" > 修改密码</i></button>
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
								<#if employeeList ??>
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
                        <div class="col-md-4"  style="display:none;"  id="updateEmployee">
                            <div class="widget alert-Box">

                                <div class="widget-head clearfix">
                                    <div class="pull-left">员工信息</div>
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
                                            	<input type="hidden" name="personaId" id="personaId"/>
                                            	<input type="hidden" name="custNo" id="updateCustNo"/>
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
                        <div class="col-md-4" style="display:none;" id="modifyPassword">
                        	<input type="hidden" id="custNo" />
                            <div class="widget alert-Box">

                                <div class="widget-head clearfix">
                                    <div class="pull-left">修改密码</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                </div>
                                <div class="widget-content">
                                    <div class="padd">
                                        <div class="form quick-post">
                                            <!-- Edit profile form (not working)-->
                                            <form class="form-horizontal">
                                                <!-- Title -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">新密码</label>
                                                    <div class="col-lg-9">
                                                        <input type="password" class="form-control" id="newPassword">
                                                    </div>
                                                </div>
                                                <!-- Content -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">重复密码</label>
                                                    <div class="col-lg-9">
                                                        <input type="password" class="form-control" id="rePassword">
                                                    </div>
                                                </div>

                                                <!-- Buttons -->
                                                <div class="form-group">
                                                    <!-- Buttons -->
                                                    <div class="col-lg-12 text-center">
                                                        <button class="btn btn-success mlt7" type="button" id="updatePassword">
                                                            <i class="icon-ok"></i>
                                                            提交
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