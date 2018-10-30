<!DOCTYPE html>
<html lang="en">
<head>
    <title>员工管理-添加菜单</title>
</head>

<body>
        <!-- Main bar -->
        <div class="mainbar">

            <!-- Page heading -->
            <div class="page-head">
                <h2 class="pull-left"><i class="icon-user"></i> 员工管理 > 添加菜单</h2>
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
                            </div>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4"   id="updateEmployee">
                            <div class="widget alert-Box">

                                <div class="widget-head clearfix">
                                    <div class="pull-left">添加一级菜单</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>

                                </div>
                                <div class="widget-content">
                                    <div class="padd">
                                        <div class="form quick-post">
                                            <!-- Edit profile form (not working)-->
                                            <form class="form-horizontal"  action="${contextPath}/service/persona/addPowerInfo">
                                            	<input type="hidden" name="employeeId" id="employeeId"/>
                                            	<input type="hidden" name="personaId" id="personaId"/>
                                                <!-- Title -->
                                               
                                                <!-- Content -->
                                                <!--<div class="form-group">
                                                    <label class="control-label col-lg-3">权限名称</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="icon-home" name="powerNameOne" id="powerNameOne" value="icon-home">
                                                    </div>
                                                </div>
                                                -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">一级菜单名称</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="menuNameOne" id="menuNameOne" value="">
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
                    <div class="row">
                        <div class="col-md-4"   id="updateEmployee">
                            <div class="widget alert-Box">

                                <div class="widget-head clearfix">
                                    <div class="pull-left">添加二级菜单信息</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>

                                </div>
                                <div class="widget-content">
                                    <div class="padd">
                                        <div class="form quick-post">
                                            <!-- Edit profile form (not working)-->
                                            <form class="form-horizontal"  action="${contextPath}/service/persona/addPowerInfo">
                                            	<input type="hidden" name="employeeId" id="employeeId"/>
                                            	<input type="hidden" name="personaId" id="personaId"/>
                                                <!-- Title -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">菜单路径</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="/service/*/*" name="powerUrl" id="powerUrl"  value="">
                                                    </div>
                                                </div>
                                                <!-- Content -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">权限名称</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" placeholder="ROLE_*" name="powerName" id="powerName" value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">二级菜单名称</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" name="menuName" id="menuName" value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">一级菜单</label>
                                                    <div class="col-lg-9">
                                                        <select class="form-control" id="powerIds">
	                                                        <#list power as p>
	                                                        	<option value="${p.powerId}">${p.menuName}</option>
	                                                        </#list>
                                                        </select>
                                                    </div>
                                                </div>

                                                <!-- Buttons -->
                                                <div class="form-group">
                                                    <!-- Buttons -->
                                                    <div class="col-lg-12 text-center">
                                                        <button class="btn btn-success mlt7" type="button" id="saveEmployeeTwo">
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
    <script>seajs.use("power/addPower.js");</script>
</body>
</html>