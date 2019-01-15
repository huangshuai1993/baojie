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
                                   <div class="pull-right col-md-6">
	                                    <div class="form-group table-search">
	                                        <input  id="searchName" class="form-control searchTxt" type="text" placeholder="合同名称" value="${searchName}">
	                                     </div>
                                     	<button id="mySubmit" class="btn btn-primary fl">查 询</button>
                                      	 <button class="btn btn-success pull-right btn-csvDownload" type="button">
                                            		导出
                                        </button>
                                        <button class="marginR10 btn btn-success pull-right btn-addContract" type="button">
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
													<td>${contract.typeName}-${contract.detailTypeName}</td>
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
													<td>${contract.statusName}</td>
													<#if contract.created ??>
		                                            	<td>${contract.created?string("yyyy-MM-dd HH:mm:ss")}</td>
		                                            <#else>
		                                            	<td></td>
		                                            </#if>
													<#if contract.updated ??>
		                                            	<td>${contract.updated?string("yyyy-MM-dd HH:mm:ss")}</td>
		                                            <#else>
		                                            	<td></td>
		                                            </#if>
		                                            <td>
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
                                            <form class="form-horizontal"  action="${contextPath}/service/bcontract/addOrUpdateContract">
                                            	<input type="hidden" name="contractId" id="contractId"/>
                                                <!-- Title -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">合同名称</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="contractName" id="contractName" maxlength="50"  value="">
                                                    </div>
                                                </div>
                                              <div class="form-group storeIdDiv" >
                                                <label class="control-label col-lg-3">类型</label>
                                                <div class="col-lg-9">
                                                    <select class="form-control" id="type" >
                                                     	<#list contractType?keys as key >
                                                        	<option value="${key}">${contractType["${key}"]}</option>
                                                        </#list>
                                                    </select>
                                                </div>
                                            </div>
                                                <!--detailType -->
                                                <div class="form-group storeIdDiv" >
                                                    <label class="control-label col-lg-3">类型明细</label>
                                                    <div class="col-lg-9">
                                                        <select class="form-control" id="detailType" >
                                                        	<#list contractDetailType?keys as key >
                                                        		<option value="${key}">${contractDetailType["${key}"]}</option>
                                                       		 </#list>
                                                        </select>
                                                    </div>
                                                </div>
                                                 <div class="form-group">
                                                    <label class="control-label col-lg-3">标的/项目</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" maxlength="30"  name="towerName" id="towerName" value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">我方单位</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" maxlength="30"  name="company" id="company" value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">对方单位</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" maxlength="30"  name="serviceCompany" id="serviceCompany" value="">
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">合同生效时间</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" maxlength="30" placeholder="2018-01-01"  name="commencementDate" id="commencementDate" value="">
                                                    </div>
                                                </div>
                                                
                                                
                                                  <div class="form-group">
                                                    <label class="control-label col-lg-3">合同终止时间</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" maxlength="30" placeholder="2018-01-01" name="terminationDate" id="terminationDate" value="">
                                                    </div>
                                                </div>
                                                
                                                  <div class="form-group">
                                                    <label class="control-label col-lg-3">合同总价</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" maxlength="30" placeholder="单位：元" name="totalPrices" id="totalPrices" value="">
                                                    </div>
                                                </div>
                                                
                                                  <div class="form-group">
                                                    <label class="control-label col-lg-3">合同月金额</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" maxlength="30"  placeholder="单位：元" name="monthPrices" id="monthPrices" value="">
                                                    </div>
                                                </div>
                                                
                                                  <div class="form-group">
                                                    <label class="control-label col-lg-3">合同已付金额</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" maxlength="30"  placeholder="单位：元" name="paidPrices" id="paidPrices" value="">
                                                    </div>
                                                </div>
                                                
                                                
                                                  <div class="form-group">
                                                    <label class="control-label col-lg-3">合同余额</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" maxlength="30" placeholder="单位：元" name="balance" id="balance" value="">
                                                    </div>
                                                </div>
                                                
                                                  <div class="form-group">
                                                    <label class="control-label col-lg-3">合同人数</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" maxlength="30"  name="peopleCount" id="peopleCount" value="">
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">合同份数</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" maxlength="30"  name="copies" id="copies" value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">签约部门</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" maxlength="30"  name="department" id="department" value="">
                                                    </div>
                                                </div>
                                                 <div class="form-group">
                                                    <label class="control-label col-lg-3">经办人</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" maxlength="30"  name="transactor" id="transactor" value="">
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">联系方式</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="contact" id="contact" onkeyup="value=value.replace(/[^\d]/g,'')"  value="" maxlength="11">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">合同履行情况</label>
                                                    <div class="col-lg-9">
                                                        <select class="form-control" id="status">
                                                        	<#list contractStatus?keys as key >
	                                                        	<option value="${key}">${contractStatus["${key}"]}</option>
	                                                        </#list>
                                                        </select>
                                                    </div>
                                                </div>
                                                <!-- Buttons -->
                                                <div class="form-group">
                                                    <!-- Buttons -->
                                                    <div class="col-lg-12 text-center">
                                                        <button class="btn btn-success mlt7" type="button" id="saveContract">
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
    <script>seajs.use("baojie/getAllContract.js");</script>
    <form action="${contextPath}/service/bcontract/getAllContract" method="post" id="myForm">
    	<input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber"/>
    	<input type="hidden" name="contractName" value="${searchName}" id="searchContractName"/>
    </form>
    <form action="${contextPath}/service/bcontract/csvDownLoadAllContract" method="post" id="csvDownLoadAllContract">
    	<input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber"/>
    	<input type="hidden" name="contractName" value="${searchName}" id="searchContractName"/>
    </form>
</body>
</html>