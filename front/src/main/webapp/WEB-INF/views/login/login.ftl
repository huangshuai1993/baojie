<!DOCTYPE html>
<html lang="en">
<head>
  <#include "./common/common_src.ftl">
  <title>后台登陆页面</title>
  <style>
  	.form-horizontal .control-label{
  		width:90px;
  		text-align:right;
  	}
  </style>
</head>
<body>
<!-- Form area -->
<div class="admin-form">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <!-- Widget starts -->
            <div class="widget worange">
              <!-- Widget head -->
              <div class="widget-head">
                <i class="icon-lock"></i>  登录
              </div>

              <div class="widget-content">
                <div class="padd">
                  <!-- Login form -->
                  <form class="form-horizontal">
                    <!-- Email -->
                    <div class="form-group">
                      <label class="control-label col-lg-3" for="inputEmail">用户名称</label>
                      <div class="col-lg-9">
                        <input type="text" class="form-control" id="userName" placeholder="用户名称" autocomplete="off">
                      </div>
                    </div>
                    <!-- Password -->
                    <div class="form-group">
                      <label class="control-label col-lg-3" for="inputPassword">用户密码</label>
                      <div class="col-lg-9">
                        <input type="password" class="form-control" id="userPassword" placeholder="用户密码" autocomplete="off">
                      </div>
                    </div>
                    <!-- Remember me checkbox and sign in button -->
                   <!-- <div class="form-group">
					<div class="col-lg-9 col-lg-offset-3">
                      <div class="checkbox">
                        <label>
                          <input type="checkbox"> Remember me
                        </label>
						</div>
					</div>
					</div>-->
                        <div class="col-lg-9 col-lg-offset-2">
							<button type="button" class="btn btn-danger">登录</button>
							<button type="reset" class="btn btn-default">重置</button>
						</div>
                    <br />
                  </form>
				</div>
                </div>
            </div>  
      </div>
    </div>
  </div> 
</div>
<#include "./common/common_footer.ftl">
<script type="text/javascript">
	$("footer").css({"position":"fixed","bottom":"0","width":"100%"});
</script>
<script>seajs.use("login/login");</script>

</body>
</html>