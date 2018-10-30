<!DOCTYPE html>
<html lang="en">
<head>
    <title>员工管理-角色权限</title>
</head>

<body>
  
        <!-- Main bar -->
        <div class="mainbar">

            <!-- Page heading -->
            <div class="page-head">
                <h2 class="pull-left"><i class="icon-user"></i> 员工管理 > 角色权限</h2>
                <div class="clearfix"></div>

            </div>
            <!-- Page heading ends -->
            <!-- Matter -->
            <div class="matter">
                <div class="container">
                    <!-- Table -->

                    <div class="row">
                        <div class="col-md-4">
                            <div class="widget alert-Box">

                                <div class="widget-head clearfix">
                                    <div class="pull-left">角色列表</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>

                                </div>
                                <div class="widget-content">
                                    <div class="padd" id="allPersonas">
                                    	<#list allPersonas as persona>
                                        	 	<div class="juese-ui <#if personaId == persona.personaId> juese-ui-cur </#if>"  data-personaId=${persona.personaId}>${persona.personaName}</div>
                                         </#list>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="col-md-4">
                            <div class="widget alert-Box">

                                <div class="widget-head clearfix">
                                    <div class="pull-left">角色详情</div>
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
                                                    <label class="control-label col-lg-3">角色名称</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" class="form-control" value="${p2p_persona.personaName}" id="personaName">
                                                    </div>
                                                </div>
                                                <!-- Content -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">角色详情</label>
                                                    <div class="col-lg-9">
                                                        <textarea class="form-control" id="personaDesc">${p2p_persona.personaDesc}</textarea>
                                                    </div>
                                                </div>

                                                <!-- Buttons -->
                                                <div class="form-group">
                                                    <!-- Buttons -->
                                                    <div class="col-lg-12 text-center">
                                                        <button class="btn btn-success mlt7" type="button" id="savePersona">
                                                            <i class="icon-ok"></i>
                                                            <input type="hidden" value="${personaId}" id="personId"/>
                                                            保存
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="widget alert-Box">
                                <div class="widget-head clearfix">
                                    <div class="pull-left ">成员列表</div>
                                    <div class="widget-icons pull-right mlt10 ">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                </div>
                                <div class="widget-content">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>姓名</th>
                                            <th>登录名</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#list allEmployee as employee>
	                                        <tr>
	                                            <td>${employee.realName}</td>
	                                            <td>${employee.username}</td>
	                                        </tr>
                                       </#list>
                                        </tbody>
                                    </table>
                                    <!--<div class="widget-foot clearfix">
                                    </div>-->
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="widget alert-Box">

                                <div class="widget-head clearfix">
                                    <div class="pull-left">权限展示</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>

                                </div>
                                <div class="widget-content">
                                    <div class="padd">
	                                    <#if personaId=='1'>
			                                    <#list list1 as powerA>
				                                        <div class="clearfix" >
				                                            <h5 class="qx-type"><span>${powerA.menuName}</span></h5>
				                                            <#list allPowers as  powerB>
				                                            	<#if powerB.parentId==powerA.powerId>
				                                            		<#if powerB.menuName != 'index'>
							                                            <div class="mlt10">
								                                                <div class="check-box">
								                                                    <label><input type="checkbox" checked="checked" class="current"  data-powerid=${powerB.powerId}> ${powerB.menuName}</label>
								                                                </div>
							                                            </div>
				                                            		</#if>
					                                          	</#if>
				                                            </#list>
				                                        </div>
			                                    </#list>
		                                 <#else>
		                                 	    <#list list1 as powerA>
			                                        <div class="clearfix">
			                                            <h5 class="qx-type"><span>${powerA.menuName}</span></h5>
			                                            <#list allPowers as  powerB>
			                                            	<#if powerB.parentId==powerA.powerId>
			                                            		<#if powerB.menuName != 'index'>
		                                            				<#if powerB.tempStr=='1'>
							                                            <div class="mlt10">
								                                                <div class="check-box">
								                                                    <label><input type="checkbox" checked="checked" class="current" data-powerid=${powerB.powerId}> ${powerB.menuName}</label>
								                                                </div>
							                                            </div>
							                                       	<#else>
							                                            <div class="mlt10">
								                                                <div class="check-box">
								                                                    <label><input type="checkbox"  data-powerid=${powerB.powerId}> ${powerB.menuName}</label>
								                                                </div>
							                                            </div>
			                                          		 		 </#if>
			                                            		</#if>
				                                          	</#if>
			                                            </#list>
			                                        </div>
		                                    </#list>
	                                    </#if>
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
    <!-- Footer ends -->
    <!-- Scroll to top -->
    <script>seajs.use("power/toPowerIndex");</script>
</body>
</html>