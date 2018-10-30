<!DOCTYPE html>
<html lang="en">
<head>
    <title>财务管理-密码管理</title>
</head>

<body>
        <!-- Main bar -->
        <div class="mainbar">

            <!-- Page heading -->
            <div class="page-head">
                <h2 class="pull-left"><i class="icon-user"></i> 财务管理>密码管理</h2>
                <div class="clearfix"></div>

            </div>
            <!-- Page heading ends -->
            <!-- Matter -->
            <div class="matter">
                    <div class="row">
                        <div class="col-md-4" style="margin:0 auto; float:none" id="updatestore">
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
                                            	<input type="hidden" name="id" id="id"/>
                                                <!-- Title -->
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">新密码</label>
                                                    <div class="col-lg-9">
                                                        <input type="password" class="form-control" name="pwd" id="pwd" maxlength="18"  value="">
                                                    </div>
                                                    <div id="pwdInfo">
                                                     <font color="red" size="-1">6-18位数字+字母</font>  
                                                    </div>
                                                    <div id="pwdError" style="display:none">
                                                     <font color="red" size="-1">密码格式不对</font>  
                                                    </div>
										               
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-lg-3">重复密码</label>
                                                    <div class="col-lg-9">
                                                        <input type="password" class="form-control" name="repeatPwd" id="repeatPwd" maxlength="18"  value="">
                                                    </div>
                                                </div>

                                                <!-- Buttons -->
                                                <div class="form-group">
                                                    <!-- Buttons -->
                                                    <div class="col-lg-12 text-center">
                                                        <button class="btn btn-success mlt7" type="button" id="save">
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
        <div class="clearfix"></div>
    <script>seajs.use("finance/updatePwd.js");</script>
</body>
</html>