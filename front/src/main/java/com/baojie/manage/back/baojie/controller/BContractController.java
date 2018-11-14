package com.baojie.manage.back.baojie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baojie.manage.back.baojie.form.ContractForm;
import com.baojie.manage.back.baojie.service.BContractService;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.common.util.PageUtil;
import com.baojie.manage.base.controller.BaseController;
import com.baojie.manage.base.exception.BizException;

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
		PageResults<ContractForm> allContract = contractService.getAllContract(pageNumber, pageSize, contractName, towerName, status);
		
		model.addAttribute("allContractList", allContract.getList());
		pageUtil.setTotalCount((int) allContract.getTotalCount());
		model.addAttribute("page", pageUtil);
		return "employee/getAllEmployees";
	}
}
