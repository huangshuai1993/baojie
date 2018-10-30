<!DOCTYPE html>
<html lang="en">
<head>
    <title>规则</title>
   
</head>

<body>
        <!-- Main bar -->
        <div class="mainbar">
            <!-- Page heading -->
			<div class="page-head">
				<h2 class="pull-left"><i class="icon-home"></i> 结果信息</h2>
				<div class="clearfix">
					<div class="bread-crumb pull-right">
					<form class="bs-example bs-example-form" role="form"  action="${contextPath}/service/rule/exportRulePdf" onsubmit="return toCheck()" method="post" >
						<input type="hidden" name = "swift_number" id = "swift_number" value="${ruleForm.swift_number}"/>
						<button class="btn btn-success btn-addEmployee" type="submit">
							<i class="icon-share-alt"></i> 导出信用报告
						</button>
					</form>	
					</div>
				</div>

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
									<div class="pull-left mtop5">反欺诈规则查询结果</div>
									<div class="widget-icons pull-right mlt10 mtop5">
										<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
										<a href="#" class="wclose"><i class="icon-remove"></i></a>
									</div>
								</div>
								<div class="widget-content">
									<table class="table table-striped table-bordered table-hover results">
										<thead>
											<tr>
												<th class="col-md-3">最终决策结果</th>
												<td class="col-md-3">
												<#if ruleForm.rule_final_decision == "Reject" >
												拒绝
												</#if>
												<#if ruleForm.rule_final_decision == "Accept" >
												通过
												</#if>
												<#if ruleForm.rule_final_decision == "Review" >
												复议
												</#if>
												</td>
												<th class="col-md-3">最终规则评分</th>
												<td class="col-md-3">${ruleForm.rule_final_weight}</td>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12">
							<div class="widget">
								<div class="widget-head clearfix">
									<div class="pull-left mtop5">非银客群特殊名单规则</div>
									<div class="widget-icons pull-right mlt10 mtop5">
										<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
										<a href="#" class="wclose"><i class="icon-remove"></i></a>
									</div>
								</div>
								<div class="widget-content">
									<table class="table table-striped table-bordered table-hover results">
										<thead>
												<tr>
													<th class="col-md-3">银行中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS010}</td>
													<th class="col-md-3">银行中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS010}</td>
											     </tr>
												 <tr>
													<th class="col-md-3">直系亲属银行中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS020}</td>
													<th class="col-md-3">直系亲属银行中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS020}</td>
											     </tr>
											
												 <tr>
													<th class="col-md-3">朋友等关系银行中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS030}</td>
													<th class="col-md-3">朋友等关系银行中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS030}</td>
											     </tr>
												 
												  <tr>
													<th class="col-md-3">银行一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS040}</td>
													<th class="col-md-3">银行一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS040}</td>
											     </tr>
												 
												 <tr>
													<th class="col-md-3">直系亲属银行一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS045}</td>
													<th class="col-md-3">直系亲属银行一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS045}</td>
											     </tr> 
												  <tr>
													<th class="col-md-3">法院失信人</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS090}</td>
													<th class="col-md-3">法院失信人权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS090}</td>
											     </tr> 
												 <tr>
													<th class="col-md-3">法院被执行人</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS100}</td>
													<th class="col-md-3">法院被执行人权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS100}</td>
											     </tr> 
											     <tr>
													<th class="col-md-3">P2P中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS135}</td>
													<th class="col-md-3">P2P中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS135}</td>
											     </tr> 
											
											    <tr>
													<th class="col-md-3">直系亲属P2P中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS136}</td>
													<th class="col-md-3">直系亲属P2P中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS136}</td>
											     </tr> 
												 
												<tr>
													<th class="col-md-3">朋友等其他关系P2P中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS137}</td>
													<th class="col-md-3">朋友等其他关系P2P中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS137}</td>
											     </tr>  
												 
												 
												 <tr>
													<th class="col-md-3">P2P一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS144}</td>
													<th class="col-md-3">P2P一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS144}</td>
											     </tr> 
												 
												 <tr>
													<th class="col-md-3">直系亲属P2P一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS145}</td>
													<th class="col-md-3">直系亲属P2P一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS145}</td>
											     </tr> 
											
												 <tr>
													<th class="col-md-3">朋友等其他关系P2P一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS146}</td>
													<th class="col-md-3">朋友等其他关系P2P一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS146}</td>
											     </tr> 
												<tr>
													<th class="col-md-3">小贷中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS150}</td>
													<th class="col-md-3">小贷中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS150}</td>
											     </tr> 
												<tr>
													<th class="col-md-3">直系亲属小贷中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS151}</td>
													<th class="col-md-3">直系亲属小贷中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS151}</td>
											     </tr> 
												<tr>
													<th class="col-md-3">朋友等其他关系小贷中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS152}</td>
													<th class="col-md-3">朋友等其他关系小贷中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS152}</td>
											     </tr> 	 
											
												 <tr>
													<th class="col-md-3">小贷一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS159}</td>
													<th class="col-md-3">小贷一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS159}</td>
											     </tr> 	
												 
												 <tr>
													<th class="col-md-3">直系亲属小贷一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS160}</td>
													<th class="col-md-3">直系亲属小贷一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS160}</td>
											     </tr> 	
												 
												 <tr>
													<th class="col-md-3">朋友等其他关系小贷一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS161}</td>
													<th class="col-md-3">朋友等其他关系小贷一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS161}</td>
											     </tr> 	
												 <tr>
													<th class="col-md-3">消费类分期中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS170}</td>
													<th class="col-md-3">消费类分期中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS170}</td>
											     </tr> 
												 	 <tr>
													<th class="col-md-3">直系亲属消费类分期中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS171}</td>
													<th class="col-md-3">直系亲属消费类分期中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS171}</td>
											     </tr> 
												 	 <tr>
													<th class="col-md-3">朋友等其他关系消费类分期中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS172}</td>
													<th class="col-md-3">朋友等其他关系消费类分期中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS172}</td>
											     </tr> 
												 	 <tr>
													<th class="col-md-3">消费类分期一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS179}</td>
													<th class="col-md-3">消费类分期一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS179}</td>
												 </tr> 
												 	 <tr>
													<th class="col-md-3">直系亲属消费类分期一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS180}</td>
													<th class="col-md-3">直系亲属消费类分期一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS180}</td>
											     </tr> 
												 <tr>
													<th class="col-md-3">朋友等其他关系消费类分期一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS181}</td>
													<th class="col-md-3">朋友等其他关系消费类分期一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS181}</td>
											     </tr> 
											
											     <tr>
													<th class="col-md-3">非银其他中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS190}</td>
													<th class="col-md-3">非银其他中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS190}</td>
											     </tr> 
												<tr>
													<th class="col-md-3">直系亲属非银其他中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS191}</td>
													<th class="col-md-3">直系亲属非银其他中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS191}</td>
											     </tr> 
												 
												  <tr>
													<th class="col-md-3">朋友等其他关系非银其他中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS192}</td>
													<th class="col-md-3">朋友等其他关系非银其他中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS192}</td>
											     </tr> 
												 
												   <tr>
													<th class="col-md-3">非银其他一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS199}</td>
													<th class="col-md-3">非银其他一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS199}</td>
											     </tr> 
												   <tr>
													<th class="col-md-3">直系亲属非银其他一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS200}</td>
													<th class="col-md-3">直系亲属非银其他一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS200}</td>
											     </tr> 
												   <tr>
													<th class="col-md-3">朋友等其他关系非银其他一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS201}</td>
													<th class="col-md-3">朋友等其他关系非银其他一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS201}</td>
											     </tr> 
												 
												    <tr>
													<th class="col-md-3">现金类分期中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS215}</td>
													<th class="col-md-3">现金类分期中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS215}</td>
											     </tr> 
												    <tr>
													<th class="col-md-3">直系亲属现金类分期中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS216}</td>
													<th class="col-md-3">直系亲属现金类分期中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS216}</td>
											     </tr> 
												<tr>
													<th class="col-md-3">朋友等其他关系现金类分期中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS217}</td>
													<th class="col-md-3">朋友等其他关系现金类分期中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS217}</td>
											     </tr> 
											
												 <tr>
													<th class="col-md-3">现金类分期一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS224}</td>
													<th class="col-md-3">现金类分期一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS224}</td>
											     </tr> 
												 <tr>
													<th class="col-md-3">直系亲属现金类分期一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS225}</td>
													<th class="col-md-3">直系亲属现金类分期一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS225}</td>
											     </tr> 
												 <tr>
													<th class="col-md-3">朋友等其他关系现金类分期一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS226}</td>
													<th class="col-md-3">朋友等其他关系现金类分期一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS226}</td>
											     </tr> 
												 
											 <tr>
													<th class="col-md-3">代偿类分期中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS235}</td>
													<th class="col-md-3">代偿类分期中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS235}</td>
											     </tr> 
												  <tr>
													<th class="col-md-3">直系亲属代偿类分期中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS236}</td>
													<th class="col-md-3">直系亲属代偿类分期中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS236}</td>
											     </tr> 
												  <tr>
													<th class="col-md-3">朋友等其他关系代偿类分期中风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS237}</td>
													<th class="col-md-3">朋友等其他关系代偿类分期中风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS237}</td>
											     </tr> 
												 
											  <tr>
													<th class="col-md-3">代偿类分期一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS244}</td>
													<th class="col-md-3">代偿类分期一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS244}</td>
											     </tr> 
												   <tr>
													<th class="col-md-3">直系亲属代偿类分期一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS245}</td>
													<th class="col-md-3">直系亲属代偿类分期一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS245}</td>
											     </tr> 
												   <tr>
													<th class="col-md-3">朋友等其他关系代偿类分期一般风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS246}</td>
													<th class="col-md-3">朋友等其他关系代偿类分期一般风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS246}</td>
											     </tr> 
												   <tr>
													<th class="col-md-3">银行高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS250}</td>
													<th class="col-md-3">银行高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS250}</td>
											     </tr> 
											
												   <tr>
													<th class="col-md-3">P2P高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS251}</td>
													<th class="col-md-3">P2P高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS251}</td>
											     </tr> 
												   <tr>
													<th class="col-md-3">小贷高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS252}</td>
													<th class="col-md-3">小贷高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS252}</td>
											     </tr> 
											  <tr>
													<th class="col-md-3">消费类分期高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS253}</td>
													<th class="col-md-3">消费类分期高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS253}</td>
											     </tr> 
												   <tr>
													<th class="col-md-3">现金类分期高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS254}</td>
													<th class="col-md-3">现金类分期高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS254}</td>
											     </tr> 
												   <tr>
													<th class="col-md-3">代偿类分期高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS255}</td>
													<th class="col-md-3">代偿类分期高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS255}</td>
											     </tr> 
											 <tr>
													<th class="col-md-3">非银其他高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS256}</td>
													<th class="col-md-3">非银其他高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS256}</td>
											     </tr> 
												  <tr>
													<th class="col-md-3">直系亲属银行高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS257}</td>
													<th class="col-md-3">直系亲属银行高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS257}</td>
											     </tr> 
												  <tr>
													<th class="col-md-3">直系亲属P2P高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS258}</td>
													<th class="col-md-3">直系亲属P2P高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS258}</td>
											     </tr> 
												  <tr>
													<th class="col-md-3">直系亲属小贷高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS259}</td>
													<th class="col-md-3">直系亲属小贷高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS259}</td>
											     </tr> 
												  <tr>
													<th class="col-md-3">直系亲属消费类分期高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS260}</td>
													<th class="col-md-3">直系亲属消费类分期高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS260}</td>
											     </tr> 
												  <tr>
													<th class="col-md-3">直系亲属现金类分期高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS261}</td>
													<th class="col-md-3">直系亲属现金类分期高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS261}</td>
											     </tr> 
												
												 <tr>
													<th class="col-md-3">直系亲属代偿类分期高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS262}</td>
													<th class="col-md-3">直系亲属代偿类分期高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS262}</td>
											     </tr> 
												  <tr>
													<th class="col-md-3">直系亲属非银其他高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS263}</td>
													<th class="col-md-3">直系亲属非银其他高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS263}</td>
											     </tr> 
												  <tr>
													<th class="col-md-3">朋友或其他关系银行高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS264}</td>
													<th class="col-md-3">朋友或其他关系银行高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS264}</td>
											     </tr> 
											  <tr>
													<th class="col-md-3">朋友或其他关系P2P高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS265}</td>
													<th class="col-md-3">朋友或其他关系P2P高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS265}</td>
											     </tr> 
												   <tr>
													<th class="col-md-3">朋友或其他关系小贷高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS266}</td>
													<th class="col-md-3">朋友或其他关系小贷高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS266}</td>
											     </tr> 
												   <tr>
													<th class="col-md-3">朋友或其他关系消费类分期高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS267}</td>
													<th class="col-md-3">朋友或其他关系消费类分期高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS267}</td>
											     </tr> 
												    <tr>
													<th class="col-md-3">朋友或其他关系现金类分期高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS268}</td>
													<th class="col-md-3">朋友或其他关系现金类分期高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS268}</td>
											     </tr> 
												    <tr>
													<th class="col-md-3">朋友或其他关系代偿类分期高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS269}</td>
													<th class="col-md-3">朋友或其他关系代偿类分期高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS269}</td>
											     </tr> 
												    <tr>
													<th class="col-md-3">朋友或其他关系非银其他高风险</th>
													<td class="col-md-3">${ruleForm.rule_name_QJS270}</td>
													<th class="col-md-3">朋友或其他关系非银其他高风险权重</th>
												    <td class="col-md-3">${ruleForm.rule_weight_QJS270}</td>
											     </tr> 
										</thead>
									</table>
								</div>
							</div>
						</div>
						</div>
					
					<div class="row">
						<div class="col-md-12">
							<div class="widget">
								<div class="widget-head clearfix">
									<div class="pull-left mtop5">非银客群多次申请规则</div>
									<div class="widget-icons pull-right mlt10 mtop5">
										<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
										<a href="#" class="wclose"><i class="icon-remove"></i></a>
									</div>
								</div>
								<div class="widget-content">
									<table class="table table-striped table-bordered table-hover results">
										<thead>
												<tr>
													<th class="col-md-3">最近在非银机构多次申请次数过多</th>
													<td class="col-md-3">${ruleForm.rule_name_QJF050}</td>
													<th class="col-md-3">最近在非银机构多次申请次数过多权重</th>
													<td class="col-md-3">${ruleForm.rule_weight_QJF050}</td>
												</tr>
													<tr>
													<th class="col-md-3">最近在银行机构多次申请次数过多</th>
													<td class="col-md-3">${ruleForm.rule_name_QJF055}</td>
													<th class="col-md-3">最近在银行机构多次申请次数过多权重</th>
													<td class="col-md-3">${ruleForm.rule_weight_QJF055}</td>
												</tr>
													<tr>
													<th class="col-md-3">最近一个月在非银机构有过多次申请</th>
													<td class="col-md-3">${ruleForm.rule_name_QJF060}</td>
													<th class="col-md-3">最近一个月在非银机构有过多次申请权重</th>
													<td class="col-md-3">${ruleForm.rule_weight_QJF060}</td>
												</tr>
													<tr>
													<th class="col-md-3">最近一个月在银行机构有过多次申请</th>
													<td class="col-md-3">${ruleForm.rule_name_QJF065}</td>
													<th class="col-md-3">最近一个月在银行机构有过多次申请权重</th>
													<td class="col-md-3">${ruleForm.rule_weight_QJF065}</td>
												</tr>

										</thead>
									</table>
								</div>
							</div>
						</div>
						</div>
					
					
					
					<div class="row">
						<div class="col-md-12">
							<div class="widget">
								<div class="widget-head clearfix">
									<div class="pull-left mtop5">非银客群多次申请月度版规则</div>
									<div class="widget-icons pull-right mlt10 mtop5">
										<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
										<a href="#" class="wclose"><i class="icon-remove"></i></a>
									</div>
								</div>
								<div class="widget-content">
									<table class="table table-striped table-bordered table-hover results">
										<thead>
											<tr>
												<th class="col-md-3">近期在非银机构有过申请</th>
												<td class="col-md-3">${ruleForm.rule_name_QAM010}</td>
												<th class="col-md-3">近期在非银机构有过申请权重</th>
												<td class="col-md-3">${ruleForm.rule_weight_QAM010}</td>
											</tr>
											
											<tr>
												<th class="col-md-3">近期在银行机构申请次数过多</th>
												<td class="col-md-3">${ruleForm.rule_name_QAM020}</td>
												<th class="col-md-3">近期在银行机构申请次数过多权重</th>
												<td class="col-md-3">${ruleForm.rule_weight_QAM020}</td>
											</tr>
											<tr>
												<th class="col-md-3">近期在非银机构有过申请</th>
												<td class="col-md-3">${ruleForm.rule_name_QAM110}</td>
												<th class="col-md-3">近期在非银机构有过申请权重</th>
												<td class="col-md-3">${ruleForm.rule_weight_QAM110}</td>
											</tr>
											<tr>
												<th class="col-md-3">近期在非银机构申请次数过多</th>
												<td class="col-md-3">${ruleForm.rule_name_QAM120}</td>
												<th class="col-md-3">近期在非银机构申请次数过多权重</th>
												<td class="col-md-3">${ruleForm.rule_weight_QAM120}</td>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
						</div>
					
					<div class="row">
						<div class="col-md-12">
							<div class="widget">
								<div class="widget-head clearfix">
									<div class="pull-left mtop5">法院被执行人规则</div>
									<div class="widget-icons pull-right mlt10 mtop5">
										<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
										<a href="#" class="wclose"><i class="icon-remove"></i></a>
									</div>
								</div>
								<div class="widget-content">
									<table class="table table-striped table-bordered table-hover results">
										<thead>
											<tr>
												<th class="col-md-3">法院失信人</th>
												<td class="col-md-3">${ruleForm.rule_name_QJE010}</td>
												<th class="col-md-3">法院失信人权重</th>
												<td class="col-md-3">${ruleForm.rule_weight_QJE010}</td>
											</tr>
											
											
											<tr>
												<th class="col-md-3">法院被执行人</th>
												<td class="col-md-3">${ruleForm.rule_name_QJE020}</td>
												<th class="col-md-3">法院被执行人</th>
												<td class="col-md-3">${ruleForm.rule_weight_QJE020}</td>
											</tr>
											<tr>
												<th class="col-md-3">曾为法院被执行人但已全部结案</th>
												<td class="col-md-3">${ruleForm.rule_name_QJE040}</td>
												<th class="col-md-3">曾为法院被执行人但已全部结案权重</th>
												<td class="col-md-3">${ruleForm.rule_weight_QJE040}</td>
											</tr>
											<tr>
												<th class="col-md-3">法院失信人已退出失信名单</th>
												<td class="col-md-3">${ruleForm.rule_name_QJE050}</td>
												<th class="col-md-3">法院失信人已退出失信名单权重</th>
												<td class="col-md-3">${ruleForm.rule_weight_QJE050}</td>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
						</div>
					
				</div>	
							<!-- Buttons -->
							<div class="form-group">
								<!-- Buttons -->
								<!--<div class="col-lg-12 text-left">
										<a href="${root}/service/rule/indexRule" class="btn btn-success mlt7"><i class="icon-ok"></i> 返回</a>
								</div>-->
							</div>
			<!-- Matter ends -->
        </div>

        <!-- Mainbar ends -->
</body>
</html>