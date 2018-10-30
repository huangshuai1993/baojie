<!DOCTYPE html>
<html lang="en">
<head>
    <title>任务详情</title>
</head>

<body>
        <!-- Main bar -->
        <div class="mainbar">
        	<!-- Page heading -->
            <div class="page-head">
                <h2 class="pull-left"><i class="icon-user"></i> 任务管理 > 任务详情</h2>
                <div class="clearfix"></div>

            </div>
			<!-- Page heading ends -->
			<!-- Matter -->
			<div class="matter">
				<div class="container">
					<form class="form-horizontal"  action="${contextPath}/service/organize/addStore">
					 <!--客户基本信息开始-->
					 <div class="form-group">
					<!-- Buttons  href="${root}/service/btask/getTaskList" -->
						<div class="col-lg-12 text-left">
							<input class="btn btn-success pull-right" onclick="history.go(-1);" type="button" value="返回列表"/>
						</div>
					 </div>
					 <div class="row">
                        <div class="col-md-12" id="">
                            <div class="widget alert-Box">
                                <div class="widget-head clearfix">
                                    <div class="pull-left">客户基本信息</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                </div>
                                <div class="widget-content">
                                    <div class="padd">
                                        <div class="form quick-post">
                                            <!-- Edit profile form (not working)-->
                                            	<input type="hidden" name="" value="${task.id}" id="taskId"/>
                                            	<input type="hidden" name="" value="${task.taskStatus}" id="taskStatus"/>
                                                <!-- Title -->
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	客户姓名:
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <input type="text" class="form-control" name="name" id="name" maxlength="20"  value="${task.name}" readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	身份证号:
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <input type="text" class="form-control" name="idcardNo" id="idcardNo" maxlength="18"  value="${task.idcardNo}" readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right'>
                                                    	性别:
                                                    </div>
                                                    <div class="col-lg-4" id="gender" >
                                                    	<#if task.gender == 1>
                                                    	男
                                                    	<#else>
                                                    	女
                                                    	</#if>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	出生日期:
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <input type="text" class="form-control" name="birthday" id="birthday"   value="${task.birthday?string("yyyy-MM-dd")}"  readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	客户手机号:
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <input type="text" class="form-control" name="mobile" id="mobile" maxlength="11"  value="${task.mobile}" readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right'>
                                                    	婚姻状况:
                                                    </div>
                                                     <div class="col-lg-7" id="marriage">
                                                     	<#if task.gender == 0>
                                                    	未婚
                                                    	<#elseif task.gender == 1>
                                                    	已婚
                                                    	<#elseif task.gender == 2>
                                                    	离异
                                                    	<#elseif task.gender == 3>
                                                    	丧偶
                                                    	</#if>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	月收入:
                                                    </div>
                                                    <div class="col-lg-4 par-unit">
                                                        <input type="text" class="form-control" name="income" id="income" value="${task.income?string("###,##0")}" readonly="readonly">
                                                        <span class='son-unit'>元</span>
                                                    </div>
                                                </div>
												<div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right'>
                                                    	是否有担保人:
                                                    </div>
                                                    <div class="col-lg-7" id="hasCautioner">
                                                    	<#if task.hasCautioner == 1>
                                                    	有
                                                    	<#else>
                                                    	无
                                                    	</#if>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	担保人姓名:
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <input type="text" class="form-control" name="cautionerName" id="cautionerName" maxlength="20"  value="${task.cautionerName}" readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	担保人身份证号:
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <input type="text" class="form-control" name="cautionerIdcardNo" id="cautionerIdcardNo" maxlength="18"  value="${task.cautionerIdcardNo}" readonly="readonly">
                                                    </div>
                                                </div>
                                                 <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	和担保人关系:
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <input type="text" class="form-control" name="cautionerRelation" id="cautionerRelation" maxlength="18"  value="${task.cautionerRelation}" readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	居住地址:
                                                    </div>
                                                    <div class="col-lg-10">
	                                                    <div class="col-lg-6" style='padding-left:0;'>
                                                        	<input type="text" class="form-control" name="homeAddress" id="homeAddress"  value="${task.homeAddress}" readonly="readonly">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	工作地址地址:
                                                    </div>
                                                    <div class="col-lg-10">
	                                                    <div class="col-lg-6" style='padding-left:0;'>
                                                        	<input type="text" class="form-control" name="workAddress" id="workAddress"  value="${task.workAddress}" readonly="readonly">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right'>
                                                    	借款产品:
                                                    </div>
                                                    <div class="col-lg-4">
                                                   		<#if task.product == 0>
                                                    	兴业贷
                                                    	<#elseif task.product == 1>
                                                    	英才贷
                                                    	<#elseif task.product == 2>
                                                    	诚薪贷
                                                    	<#elseif task.product == 3>
                                                    	诚薪楼贷
                                                    	</#if>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right lineH32'>
                                                    	借款金额:
                                                    </div>
                                                    <div class="col-lg-4 par-unit">
                                                        <input type="text" class="form-control" name="amount" id="amount"  value="${task.amount?string("###,###")}" readonly="readonly">
                                                        <span class='son-unit'>元</span>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class='col-lg-2 pull-left text-right'>
                                                    	客户来源:
                                                    </div>
                                                    <div class="col-lg-4">
                                                    	<#if task.product == 0>
                                                    	线下
                                                    	<#else>
                                                    	佰宝金服
                                                    	</#if>
                                                    </div>
                                                </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                	</div>
					<!--客户基本信息end-->
					<div class="row">
						<div class="col-md-12">
							<div class="widget alert-Box">
							 	<div class="widget-head clearfix">
	                                <div class="pull-left">外访人员</div>
	                                <div class="widget-icons pull-right">
	                                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
	                                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
	                                </div>
	                            </div>
								<div class="widget-content">
									<div class="form-group col-lg-12" style='margin-top: 20px;'>
	                                    <div class='col-lg-2 pull-left text-right lineH32'>
	                                    	分配外访人员:
	                                    </div>
	                                    <#if task.taskStatus == 0>
	                                    	<#if (regionAndStore?size == 1)>
			                                    		<div class="col-lg-10">
			                                        <div class="col-lg-2" style='padding-left:0;'>
			                                            <select class="form-control" id="region_task" disabled>
			                                        		<#list regionAndStore as province>
			                                            		<option value="${province.region_id}">${province.region_name}</option>
			                                            	</#list>
			                                            </select>
			                                        </div>
			                                        <div class="col-lg-3" style='padding-left:0;'>
			                                            <select class="form-control" id="storeId" disabled>
			                                            </select>
			                                        </div>
			                                        <div class="col-lg-2" style='padding-left:0;'>
			                                            <select class="form-control" id="empId" onchange="empChanges(this.value)">
			                                            	<option value="0">请选择</option>
			                                            </select>
			                                        </div>
			                                    </div>
	                                    	<#else>
			                                    <div class="col-lg-10">
			                                        <div class="col-lg-2" style='padding-left:0;'>
			                                            <select class="form-control" id="region_task" onchange="checkField(this.value,'#storeId')">
			                                        		<option value="0">请选择</option>
			                                        		<#list regionAndStore as province>
			                                            		<option value="${province.region_id}">${province.region_name}</option>
			                                            	</#list>
			                                            </select>
			                                        </div>
			                                        <div class="col-lg-3" style='padding-left:0;'>
			                                            <select class="form-control" id="storeId" onchange="storeChange(this.value,'#empId')">
			                                            	<option value="0">请选择</option>
			                                            </select>
			                                        </div>
			                                        <div class="col-lg-2" style='padding-left:0;'>
			                                            <select class="form-control" id="empId" onchange="empChanges(this.value)">
			                                            	<option value="0">请选择</option>
			                                            </select>
			                                        </div>
			                                    </div>
	                                    	</#if>
	                                    	<div class="col-lg-10 taskCountDivs" style="display:none;">
						                      	  <h6 style="font-weight: bold;margin-left: 131px;">所选的外访人员当前任务量为:<span id="taskCounts" style="color:red;"></span>个.</h6>
						                    </div>
	                                    <#else>
	                                    <div class="col-lg-10">
	                                        <div class="col-lg-2" style='padding-left:0;'>
	                                            <select class="form-control" disabled>
	                                        		<option value="">${task.storeProvince}</option>
	                                            </select>
	                                        </div>
	                                        <div class="col-lg-3" style='padding-left:0;'>
	                                            <select class="form-control" disabled>
	                                            <option value="">${task.storeName}</option>
	                                            </select>
	                                        </div>
	                                        <div class="col-lg-2" style='padding-left:0;'>
	                                            <select class="form-control" disabled>
	                                            <option value="">${task.empName}</option>
	                                            </select>
	                                        </div>
	                                    </div>
	                                    </#if>
	                                </div>
								</div>
							</div>
						</div>
					</div>
					<!--任务完成进度start-->
					 <div class="row">
                        <div class="col-md-12" id="">
                            <div class="widget alert-Box">
                                <div class="widget-head clearfix">
                                    <div class="pull-left">任务完成进度</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                </div>
                                <div class="widget-content">
                                	<div class="page-head" style='background:transparent;'>
						                <h2 class="pull-left" style='font-weight:bold;'>居住地照片采集</h2>
						                <div class="clearfix"></div>
						            </div>
                                    <div class="padd">
                                    	<ul class="col-md-12">
                                    		<#if homeAddressList??>
	            							<#list homeAddressList as item>
	                                    		<li class='photoList'>
	                                    			<div class='photo-pic'>
	                                    				<img src="${item.smallUrl}" onerror="nofind()">
	                                    			</div>
	                                    			<div class='origUrlBG'>
	                                    				<div class='btns pull-right' style='margin:10px 20px 0 0;'>
	                                    					<button class="btn btn-success mlt7 btn-left" type="button" >
	                                    						左转
	                                                        </button>
	                                                        <button class="btn btn-success mlt7 btn-right" type="button">
	                                    						右转
	                                                        </button>
	                                    				</div>
	                                    				<img class='origUrl' src="${item.origUrl}">
	                                    				<span class='closeImg'>×</span>
	                                    			</div>
	                                    			<h5 class='photo-name'>${item.name}</h5>
	                                    			<h5 class='photo-area'>位置</h6>
	                                    			<p class='photo-zb' onclick="mapShow('${item.lng},${item.lat}')">${item.lng},${item.lat}</p>
	                                    		</li>
                                    		</#list>
                                    		</#if>
                                    		<li style='clear:both;'></li>
                                    	</ul>		
                                    </div>
	                                <!--居住地照片采集end-->
	                            	<div class="page-head" style='background:transparent;'>
						                <h2 class="pull-left" style='font-weight:bold;'>工作地照片采集</h2>
						                <div class="clearfix"></div>
						            </div>
	                                <div class="padd">
	                                	<ul class="col-md-12">
	                                		<#if workAddressList??>
	            							<#list workAddressList as item>
	                                    		<li class='photoList'>
	                                    			<div class='photo-pic'>
	                                    				<img src="${item.smallUrl}" onerror="nofind()">
	                                    			</div>
	                                    			<div class='origUrlBG'>
	                                    				<div class='btns pull-right' style='margin:10px 20px 0 0;'>
	                                    					<button class="btn btn-success mlt7 btn-left" type="button" >
	                                    						左转
	                                                        </button>
	                                                        <button class="btn btn-success mlt7 btn-right" type="button" >
	                                    						右转
	                                                        </button>
	                                    				</div>
	                                    				<img class='origUrl' src="${item.origUrl}">
	                                    				<span class='closeImg'>×</span>
	                                    			</div>
	                                    			<h5 class='photo-name'>${item.name}</h5>
	                                    			<h5 class='photo-area'>位置</h6>
	                                    			<p class='photo-zb' onclick="mapShow('${item.lng},${item.lat}')">${item.lng},${item.lat}</p>
	                                    		</li>
                                    		</#list>
                                    		</#if>
	                                		<li style='clear:both;'></li>
	                                	</ul>		
	                                </div>
	                                <!--工作地照片采集end-->
	                                <div class="page-head" style='background:transparent;'>
						                <h2 class="pull-left" style='font-weight:bold;'>外访报告</h2>
						                <div class="clearfix"></div>
						            </div>
						            <div class="padd">
	                                	<ul class="col-md-12">
	                                		<textarea maxlength='200'  style="width:450px;height:150px"' disabled>${task.reportText}</textarea >
	                                	</ul>
	                                </div>
	                             </div>
                             </div>
                        </div>
                	</div>
					<!--任务完成进度end-->
					<div class="row">
						<div class="col-md-12">
							<div class="widget alert-Box">
							 	<div class="widget-head clearfix">
	                                <div class="pull-left">操作</div>
	                                <div class="widget-icons pull-right">
	                                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
	                                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
	                                </div>
	                            </div>
								<div class="widget-content">
									<div class="form-group col-lg-12" style='margin-top: 20px;'>
									<#if (task.taskStatus !=8)>
	                                    <div class="form-group col-lg-12">
                                            <div class='col-lg-2 pull-left text-right'>
                                            	是否已缴费:
                                            </div>
                                            <div class="col-lg-4">
                                            	<#if (task.taskStatus < 3)>
                                            	未缴费
                                            	<#elseif (task.taskStatus !=8)>
                                            	已缴费
                                            	</#if>
                                            </div>
                                        </div>  
                                        </#if>
                                        <#if (task.taskStatus < 3)>
                                         <div class="form-group col-lg-12">
                                            <div class='col-lg-2 pull-left text-right lineH32'>
                                            	是否放弃任务:
                                            </div>
                                            <div class="col-lg-4 lineH32" id="removeStatus">
                                            	<input type="radio" name="removeStatus" value="8" class="form-radio">是
												<input type="radio" name="removeStatus" value="0" class="form-radio">否
                                            </div>
                                        </div> 
                                         <div class="form-group col-lg-12">
                                            <div class='col-lg-2 pull-left text-right lineH32'>
                                            	放弃任务原因:
                                            </div>
                                            <div class="col-lg-10" >
                                            	<textarea id="removeText" maxlength='200' style="width:450px;height:150px"'></textarea >
                                            </div>
                                        </div> 
                                        <#elseif (task.taskStatus ==3)>
                                        <div class="form-group col-lg-12">
                                            <div class='col-lg-2 pull-left text-right lineH32'>
                                            	是否通过:
                                            </div>
                                            <div class="col-lg-4 lineH32" id="passStatus">
                                            	<input type="radio" name="passStatus" value="4" class="form-radio">是
												<input type="radio" name="passStatus" value="5" class="form-radio">否
                                            </div>
                                        </div> 
                                         <div class="form-group col-lg-12">
                                            <div class='col-lg-2 pull-left text-right lineH32'>
                                            	处理备注:
                                            </div>
                                            <div class="col-lg-10" >
                                            	<textarea id="passText" maxlength='200' style="width:450px;height:150px"'></textarea >
                                            </div>
                                        </div> 
                                        <#elseif (task.taskStatus >3)>
                                        <div class="form-group col-lg-12">
                                            <div class='col-lg-2 pull-left text-right lineH32'>
                                            	任务状态:
                                            </div>
                                            <div class="col-lg-4 lineH32">
                                            	${task.taskStatusName}
                                            </div>
                                        </div> 
                                         <div class="form-group col-lg-12">
                                            <div class='col-lg-2 pull-left text-right lineH32'>
                                            	处理备注:
                                            </div>
                                            <div class="col-lg-4">
                                            	<textarea id="passText" maxlength='200' style="width:450px;height:150px"' disabled>${task.memo}</textarea>
                                            </div>
                                        </div> 
                                        </#if>
                                        <!-- Buttons -->
                                        <#if (task.taskStatus <4)>
                                        <div class="form-group">
                                            <!-- Buttons -->
                                            <div class="col-lg-12 text-center">
                                                <button class="btn btn-success mlt7" type="button" id="updateTaskInfo">
                                                    <i class="icon-ok"></i>
                                                    	保存
                                                </button>
                                            </div>
                                        </div>
                                        </#if>
	                                </div>
								</div>
							</div>
						</div>
					</div>
					</form>
				</div>
				
					<!-- Buttons -->
							<div class="form-group">
								<!-- Buttons -->
								<!--<div class="col-lg-12 text-left">
										<a href="${root}/service/creditEvaluation/indexTelPeriod" class="btn btn-success mlt7"><i class="icon-ok"></i> 返回</a>
								</div>-->
							</div>

			<!-- Matter ends -->
        </div>

        <!-- Mainbar ends -->
        <div class="clearfix"></div>
        <!--mapAlert-->
        <div class="mapBG">
        	<span class='closeMap' onclick="mapHide()">×</span>
        	<div id="mapContainer" tabindex="0">
	        </div>
        </div>
    <!-- Content ends -->
    <!-- Footer starts -->
    <!-- Scroll to top -->
    <!-- Footer ends -->
     <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.0&key=2bd17c7e33e5350b151c4cf72cba5930"></script>
     <script>seajs.use("task/getTaskInfo.js");</script>
     <script>
  		$(function(){
  			if('${task.taskStatus}' == 0 && '${task.storeId}' != 0){
  				$("#region_task option[value='${task.storeProvinceCode}']").prop("selected","selected");
	  			var region_checkedval = '${task.storeProvinceCode}';
	  			checkField(region_checkedval,'#storeId');
	  			$("#storeId option[value='${task.storeId}']").prop("selected","selected");
	  			storeChange('${task.storeId}','#empId');
  		    };
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
  		 
  		 function storeChange(storeId,id){  
			var postdata={
					"storeId" : storeId
				}
			$.post(root+"/service/organize/getWorkerByStoreId",postdata,function(data){
				var dataList=data.workerList;
				var workList = '<option value="0">请选择</option>';
				if(dataList.length>0){
					var workLists=dataList.map(function(item,index){
						var row=[
						         '<option value="',item.id,'">',item.name,'</option>'
						         ];
						return row.join("");
					}).join("");
					workList+=workLists;
				}
				$(id).html(workList);
	});
} 
	function nofind(){
		var img=event.srcElement;
		img.src=root+"/img/errorImg.png";
		img.onerror=null; 
	
	};
  	</script>  
</body>
</html>