package com.baojie.manage.back.baojie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baojie.manage.back.baojie.service.BContractService;
import com.baojie.manage.back.sys.dto.EmployeeDto;
import com.baojie.manage.back.sys.dto.PersonaDto;
import com.baojie.manage.back.sys.form.EmployeeForm;
import com.baojie.manage.back.sys.form.PersonaForm;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.common.util.PageUtil;
import com.baojie.manage.base.controller.BaseController;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Lists;

@Controller
@RequestMapping("bcontract")
public class BContractController extends BaseController {
	@Autowired
	private BContractService contractService;

	@RequestMapping("/getAllContract")
	public String getAllContract(Model model, Integer pageNumber, Integer pageSize, String contractName,
			String towerName,Integer status) throws BizException {
		logger.info("getAllContract [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<EmployeeDto> employeePageList = employeeService.getEmployeePageList(personaName, parameName,
				pageNumber, pageSize);
		List<EmployeeDto> list = employeePageList.getList();
		List<EmployeeForm> formList = Lists.newArrayList();
		for (EmployeeDto employeeDto : list) {
			formList.add(new EmployeeForm(employeeDto));
		}
		model.addAttribute("employeeList", formList);
		pageUtil.setTotalCount((int) employeePageList.getTotalCount());
		model.addAttribute("page", pageUtil);
		return "employee/getAllEmployees";
}
