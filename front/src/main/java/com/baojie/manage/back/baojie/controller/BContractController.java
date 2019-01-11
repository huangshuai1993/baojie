package com.baojie.manage.back.baojie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baojie.manage.back.baojie.form.ContractDownLoad;
import com.baojie.manage.back.baojie.form.ContractForm;
import com.baojie.manage.back.baojie.service.BContractService;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.BeanUtils;
import com.baojie.manage.base.common.util.CsvDownloadUtil;
import com.baojie.manage.base.common.util.DateUtil;
import com.baojie.manage.base.common.util.JsonUtils;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.common.util.PageUtil;
import com.baojie.manage.base.controller.BaseController;
import com.baojie.manage.base.exception.BizException;
import com.fasterxml.jackson.core.type.TypeReference;

@Controller
@RequestMapping("/bcontract")
public class BContractController extends BaseController {
	@Autowired
	private BContractService contractService;

	/**
	 * 导出csv表格
	 * 
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @param towerId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/csvDownLoadAllContract")
	public void csvDownLoadAllContract(HttpServletRequest request,HttpServletResponse response, Integer pageNumber, Integer pageSize, String contractName,
			String towerName, Integer status) throws Exception {
		logger.info(
				"csvDownLoadAllMaterial [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", towerName=" + towerName);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 30;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<ContractForm> allContract = contractService.getAllContract(pageNumber, pageSize, contractName,
				towerName, status);
		List<ContractDownLoad> list = BeanUtils.copyByList(allContract.getList(), ContractDownLoad.class);
		List<Map<String, Object>> csvData = list.stream().map(d -> JsonUtils.parseObjectAsJackson(d, new TypeReference<Map<String, Object>>() {
		})).collect(Collectors.toList());
		long totalCount = allContract.getTotalCount();
		String sheetNamePrefix = DateUtil.getDateStr(DateUtil.TIME_STR_FORMAT);
         Map<String, String> csvHeader = CsvDownloadUtil.getCSVHeader(ContractDownLoad.class);
         CsvDownloadUtil.writeHeader(csvHeader, sheetNamePrefix, response);
         CsvDownloadUtil.writeData(csvHeader, csvData, response);
         csvData.clear();
         //总页数
         long totalPageNum = (totalCount / pageSize) + (totalCount % pageSize == 0 ? 0 : 1);
         if (totalPageNum > 1) {
             for (int i = 2; i <= totalPageNum; i++) {
            	 pageNumber = i;
            	 PageResults<ContractForm> contract = contractService.getAllContract(pageNumber, pageSize, contractName,
         				towerName, status);
            	 list = BeanUtils.copyByList(contract.getList(), ContractDownLoad.class);    
            	 csvData =list.stream()
                         .map(d -> JsonUtils.parseObjectAsJackson(d, new TypeReference<Map<String, Object>>() {
                         })).collect(Collectors.toList());
	                 CsvDownloadUtil.writeData(csvHeader, csvData, response);
	                 csvData.clear();
             }
         }
	}
	
	@RequestMapping("/getAllContract")
	public String getAllContract(Model model, Integer pageNumber, Integer pageSize,String contractName,
			String towerName, Integer status) throws BizException {
		logger.info("getAllContract [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 30;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<ContractForm> allContract = contractService.getAllContract(pageNumber, pageSize, contractName,
				towerName, status);
		model.addAttribute("searchName", contractName);
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
