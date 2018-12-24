<!DOCTYPE html>
<html lang="en">
<head>
    <title>职员管理-职员列表</title>
</head>

<body>
        <!-- Main bar -->
        <div class="mainbar">

            <!-- Page heading -->
            <div class="page-head">
                <h2 class="pull-left"><i class="icon-user"></i> 职员管理 > 职员列表</h2>
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
                                    <div class="pull-left mtop5">职员列表</div>
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
                                   	 	<div class="form-group table-search">
	                                        <input  id="searchName" class="form-control searchTxt" type="text" placeholder="员工名称" value="${searchName}">
	                                     </div>
                                      	<button id="mySubmit" class="btn btn-primary fl">查 询</button>
                                       <button class="btn btn-success pull-right btn-csvDownload" type="button">
                                            		导出
                                        </button>
                                        <button class="marginR10 btn btn-success pull-right btn-addStaff" type="button">
                                            <i class="icon-plus"></i>
                                            		新增职员
                                        </button>
                                    </div>
                                </div>
                                <div class="widget-content">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                        	<th>id</th>	
                                            <th>姓名</th>
                                            <th>身份证号</th>
                                            <th>年龄</th>
                                            <th>性别</th>
                                            <th>手机号</th>
                                            <th>楼盘名称</th>
                                            <th>职位名称</th>
                                            <th>工作状态</th>
                                            <th>注册时间</th>
                                            <th>修改时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if allStaff ??>
                                        	<#list  allStaff as staff>
		                                        <tr>
		                                            <td>${staff.id}</td>
		                                            <td>${staff.name}</td>
		                                            <td>${staff.idCard}</td>
		                                            <td>${staff.age}</td>
		                                            <#if staff.gender == 1>
		                                            	<td>男</td>	
		                                           <#else>
		                                             	<td>女</td>	
		                                            </#if>
		                                            <td>${staff.phone}</td>
		                                            <td>${staff.towerName}</td>
		                                            <td>${staff.positionName}</td>
		                                            <#if staff.status == 0>
		                                            	<td>在职</td>	
		                                           <#else>
		                                             	<td>离职</td>	
		                                            </#if>
		                                            <td>${staff.created}</td>
		                                            <td>${staff.updated}</td>
		                                            <td>
		                                                <button class="btn btn-xs btn-warning btn-update" data-id=${staff.id} title="编辑"><i class="icon-pencil" > 编辑</i></button>
		                                                <button class="btn btn-xs btn-danger btn-delete" data-id=${staff.id} title="删除"><i class="icon-del" > 删除</i></button>
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
                        <div class="col-md-4"  style="display:none;"  id="updatebStaff">
                            <div class="widget alert-Box">

                                <div class="widget-head clearfix">
                                    <div class="pull-left">职员信息</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>

                                </div>
                                <div class="widget-content">
                                    <div class="padd">
                                        <div class="form quick-post">
                                            <!-- Edit profile form (not working)-->
                                            <form class="form-horizontal"  action="${contextPath}/service/bstaff/addOrUpdateStaff">
                                            	<input type="hidden" name="staffId" id="staffId"/>
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
                                                        <input type="text" class="form-control" maxlength="18"  name="idCard" id="idCard" value="">
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
                                                        <input type="text" class="form-control" name="phone" id="phone" onkeyup="value=value.replace(/[^\d]/g,'')"  value="" maxlength="11">
                                                    </div>
                                                </div>
                                               <div class="form-group storeIdDiv" >
                                                    <label class="control-label col-lg-3">所属楼盘</label>
                                                    <div class="col-lg-9">
                                                        <select class="form-control" id="towerIds" onchange="btnChange(this[selectedIndex].value)">
	                                                        <#list towerList as tower>
	                                                        	<option value="${tower.towerId}">${tower.towerName}</option>
	                                                        </#list>
                                                        </select>
                                                    </div>
                                                </div>
												<div class="form-group storeIdDiv" >
                                                    <label class="control-label col-lg-3">所属职称</label>
                                                    <div class="col-lg-9">
                                                        <select class="form-control" id="positionIds">
	                                                       <option value="0">请选择</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group storeIdDiv" >
                                                    <label class="control-label col-lg-3">工作状态</label>
                                                    <div class="col-lg-9">
                                                        <select class="form-control" id="status">
	                                                       <option value="0">在职</option>
	                                                       <option value="1">离职</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <!-- Buttons -->
                                                <div class="form-group">
                                                    <!-- Buttons -->
                                                    <div class="col-lg-12 text-center">
                                                        <button class="btn btn-success mlt7" type="button" id="saveStaff">
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
    <script>seajs.use("baojie/getAllStaff.js");</script>
      <script type="text/javascript">
      function btnChange(values) {
	 	$.post(root+"/service/bstaff/getPositionListByTowerId",{"id":values},function(data){
			var workList = '<option value="-1">请选择</option>';
			if(data.retCode){
				var dataList=data.positionList;
				if(dataList.length>0){
					var workLists=dataList.map(function(item,index){
						var row=[
						         '<option value="',item.positionId,'">',item.positionName,'</option>'
						         ];
						return row.join("");
					}).join("");
					workList+=workLists;
				}
			}
			$("#positionIds").html(workList);
	 	});
	 }
	</script>
    <form action="${contextPath}/service/bstaff/getAllStaff" method="post" id="myForm">
    	<input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber"/>
    	<input type="hidden" name="towerId" value="${searchTowerId}" id="searchTower"/>
    	<input type="hidden" name="staffName" value="${searchName}" id="searchStaffName"/>
    </form>
    <form action="${contextPath}/service/bstaff/csvDownLoadAllStaff" method="post" id="csvDownLoadAllStaff">
    	<input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber"/>
    	<input type="hidden" name="towerId" value="${searchTowerId}" id="searchTower"/>
    	<input type="hidden" name="staffName" value="${searchName}" id="searchStaffName"/>
    </form>
</body>
</html>