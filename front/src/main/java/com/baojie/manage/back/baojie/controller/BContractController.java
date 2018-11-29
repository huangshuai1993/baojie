package com.baojie.manage.back.baojie.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baojie.manage.back.baojie.form.ContractForm;
import com.baojie.manage.back.baojie.service.BContractService;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.common.util.PageUtil;
import com.baojie.manage.base.controller.BaseController;
import com.baojie.manage.base.exception.BizException;

@Controller
@RequestMapping("/bcontract")
public class BContractController extends BaseController {
	@Autowired
	private BContractService contractService;

	@RequestMapping("/getAllContract")
	public String getAllContract(Model model, Integer pageNumber, Integer pageSize, String contractName,
			String towerName, Integer status) throws BizException {
		logger.info("getAllContract [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<ContractForm> allContract = contractService.getAllContract(pageNumber, pageSize, contractName,
				towerName, status);

		model.addAttribute("allContractList", allContract.getList());
		pageUtil.setTotalCount((int) allContract.getTotalCount());
		model.addAttribute("page", pageUtil);
		return "baojie/getAllContract";
	}

	/**
	 * 添加修改合同
	 * 
	 * @param request
	 * @param contract
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/addOrUpdateContract")
	@ResponseBody
	public Map<String, Object> addContract(HttpServletRequest request, @RequestBody ContractForm contract)
			throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (contract == null) {
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "合同信息不能为空!");
			return map;
		}
		Integer result = contractService.addContract(contract);
		if (result.equals(0)) {
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "操作失败!");
			return map;
		}
		map.put(Const.retCode, Boolean.TRUE);
		map.put(Const.retMsg, "操作成功!");
		return map;
	}
	/**
	 * 删除合同
	 * 
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/deleteContract")
	@ResponseBody
	public Map<String, Object> deleteContract(Long id) throws BizException {
		return contractService.deleteContract(id);
	}
	/**
	 * 获取详情
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getContractInfo")
	@ResponseBody
	public Map<String, Object> getContractInfo(Long id) throws BizException {
		return contractService.getContractInfo(id);
	}
	
}
