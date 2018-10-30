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
                                    <div class="pull-right col-md-8">
                                    <div class="form-group table-search">
                                        <input  id="searchName" class="form-control searchTxt" type="text" placeholder="员工名称" value="${searchName}">
                                     </div>
                                     <div class="form-group table-search">
                                        <input  id="searchMobile" class="form-control searchTxt" type="text" placeholder="手机号码" value="${searchMobile}">
                                     </div>
                                    <span style="float:left;margin:5px 0px 4px 35px;font-size:15px;">状态</span>
                                    <div class="col-lg-2" style='width: 130px;'>
                                        <select class="form-control" id="status_code">
                                        	<option value="10">全部</option>
                                            <option value="1">已启用</option>
                                            <option value="0">已禁用</option>
                                        </select>
                                   </div>
                                     <button id="mySubmit" class="btn btn-primary fl">查 询</button>
                                     <button class="btn btn-success pull-right btn-addWorker" type="button">
                                            <i class="icon-plus"></i>
                                        		    新增员工
                                     </button>
                                    </div>
                                    
                                </div>
                                <div class="widget-content">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>员工姓名</th>
                                            <th>身份证号</th>
                                            <th>手机号</th>
                                            <th>邮箱</th>
                                            <th>工号</th>
                                            <th>任务量</th>
                                            <th>操作</th> 
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if workerList ??>
                                        	<#list  workerList as worker>
		                                        <tr>
		                                            <td>${worker.name}</td>
		                                            <td>${worker.idcardNo}</td>
		                                            <td>${worker.mobile}</td>
		                                            <td>${worker.email}</td>
		                                            <td>${worker.empNo}</td>
		                                            <td>
		                                            <a href="${contextPath}/service/btask/getTaskList?workId=${worker.id}&taskStatus=1" >
		                                            ${worker.taskCount}
		                                            </a>
		                                            </td>
		                                            <td>
		                                            	<#if worker.status=="1">
		                                            		<button class="btn btn-xs btn-success btn-disable" data-id=${worker.id} data-state="0" title="点击禁用"><i class="icon-ban-circle" > 点击禁用</i></button>
		                                            	<#else>
		                                            		<button class="btn btn-xs btn-danger btn-disable" data-id=${worker.id} data-state="1" title="点击启用"><i class="icon-ban-circle" > 点击启用</i></button>
		                                            	</#if>
		                                                <button class="btn btn-xs btn-warning btn-update" data-id=${worker.id} title="修改"><i class="icon-pencil" > 修改</i></button>
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
								<#if workerList ??>
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
                        <div class="col-md-4"  style="display:none;"  id="updateWorker">
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
                                            <form class="form-horizontal"  action="${contextPath}/service/organize/addStore">
                                            	<input type="hidden" name="id" id="id"/>
                                                <!-- Title -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">员工姓名</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="name" id="name" maxlength="20"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">身份证号</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="idcardNo" id="idcardNo" maxlength="18"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">性别</label>
                                                    <div class="col-lg-9" id="gender">
                                                    	<input type="radio" name="gender" value="1" class="form-radio">男
														<input type="radio" name="gender" value="0" class="form-radio">女
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">出生日期</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="birthday" id="birthday"   value=""  readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">手机号码</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="mobile" id="mobile" maxlength="11"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">工号</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="empNo" id="empNo" maxlength="20"  value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">邮箱</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="email" id="email" maxlength="30"  value="">
                                                    </div>
                                                </div>
												<div class="form-group">
                                                    <label class="control-label col-lg-3">状态</label>
                                                    <div class="col-lg-9" id="status">
                                                    	<input type="radio" name="status" value="1" class="form-radio">启用
														<input type="radio" name="status" value="0" class="form-radio">禁用
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">所在区域</label>
                                                    <div class="col-lg-9">
                                                        <select class="form-control" id="region_id" onchange="checkField(this.value)">
                                                    		 <#list regionAndStore as r>
	                                                        	<option value="${r.region_id}">${r.region_name}</option>
	                                                        </#list>
                                                        </select>
                                                        <select class="form-control" id="store_id" style='margin-top: 14px;'>
                                                        </select>
                                                    </div>
                                                </div>
                                                <!-- Buttons -->
                                                <div class="form-group">
                                                    <!-- Buttons -->
                                                    <div class="col-lg-12 text-center">
                                                        <button class="btn btn-success mlt7" type="button" id="saveWorker">
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
    <form action="${contextPath}/service/organize/getWorkerList" method="post" id="myForm">
    	<input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber"/>
    	<input type="hidden" name="name" value="${searchName}" id="workerName"/>
    	<input type="hidden" name="status" value="${searchStatus}" id="statusCode"/>
    	<input type="hidden" name="mobile" value="${searchMobile}" id="search_Mobile"/>
    </form>
  	<script>
  		$(function(){
  			var $element={
  				region_0 : $("#region_id option:first")
  			}
  			var region_checkedval=$element.region_0.val();
  			$element.region_0.prop("selected",true);
  			checkField(region_checkedval);
  		});
  		 function checkField(storeVal){
  		 	var checkedArray =[];
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
	      $("#store_id").html(stores);
  		 }
  	</script>  
    <script>seajs.use("organize/getWorkerList.js");</script>
</body>
</html>