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

import com.baojie.manage.back.baojie.form.MaterialDownLoad;
import com.baojie.manage.back.baojie.form.MaterialForm;
import com.baojie.manage.back.baojie.form.SalaryDownLoad;
import com.baojie.manage.back.baojie.form.SalaryForm;
import com.baojie.manage.back.baojie.form.TowerForm;
import com.baojie.manage.back.baojie.service.BTowerService;
import com.baojie.manage.back.baojie.service.SalaryService;
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
@RequestMapping("/salary")
public class SalaryController extends BaseController {
	
	@Autowired
	private BTowerService towerService;
	
	@Autowired
	private SalaryService salaryService;
	
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
	@RequestMapping("/csvDownLoadAllMaterial")
	public void csvDownLoadAllMaterial(HttpServletRequest request,HttpServletResponse response, Integer pageNumber, Integer pageSize, Long towerId,String searchName,String time) throws Exception {
		logger.info(
				"csvDownLoadAllMaterial [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", towerId=" + towerId);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 30;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<SalaryForm> allSalary = salaryService.getAllSalary(pageNumber, pageSize, towerId, searchName,time);
		List<SalaryDownLoad> list = BeanUtils.copyByList(allSalary.getList(), SalaryDownLoad.class);
		List<Map<String, Object>> csvData = list.stream().map(d -> JsonUtils.parseObjectAsJackson(d, new TypeReference<Map<String, Object>>() {
		})).collect(Collectors.toList());
		long totalCount = allSalary.getTotalCount();
		String sheetNamePrefix = DateUtil.getDateStr(DateUtil.TIME_STR_FORMAT);
         Map<String, String> csvHeader = CsvDownloadUtil.getCSVHeader(MaterialForm.class);
         CsvDownloadUtil.writeHeader(csvHeader, sheetNamePrefix, response);
         CsvDownloadUtil.writeData(csvHeader, csvData, response);
         csvData.clear();
         //总页数
         long totalPageNum = (totalCount / pageSize) + (totalCount % pageSize == 0 ? 0 : 1);
         if (totalPageNum > 1) {
             for (int i = 2; i <= totalPageNum; i++) {
            	 pageNumber = i;
            	 PageResults<SalaryForm> salary = salaryService.getAllSalary(pageNumber, pageSize, towerId, searchName,time);
            	 list = BeanUtils.copyByList(salary.getList(), SalaryDownLoad.class);    
            	 csvData =list.stream()
                         .map(d -> JsonUtils.parseObjectAsJackson(d, new TypeReference<Map<String, Object>>() {
                         })).collect(Collectors.toList());
	                 CsvDownloadUtil.writeData(csvHeader, csvData, response);
	                 csvData.clear();
             }
         }
	}
	

	/**
	 * 获取工资信息
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @param towerId
	 * @param positionName
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getAllSalary")
	public String getAllSalary(Model model, Integer pageNumber, Integer pageSize, Long towerId,String searchName,String time)
			throws BizException {
		logger.info("getAllSalary [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", towerId="
				+ towerId);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<SalaryForm> allSalary = salaryService.getAllSalary(pageNumber, pageSize, towerId, searchName,time);
		model.addAttribute("allSalary", allSalary.getList());
		pageUtil.setTotalCount((int) allSalary.getTotalCount());
		model.addAttribute("page", pageUtil);
		List<TowerForm> queryAll = towerService.queryAll();
		model.addAttribute("towerList", queryAll);
		model.addAttribute("searchTowerId", towerId);
		model.addAttribute("searchTime", time);
		return "baojie/getAllSalary";
	}

	@RequestMapping("/addSalaryMonth")
	@ResponseBody
	public Map<String, Object> addSalaryMonth(HttpServletRequest request)
			throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer result = salaryService.addSalaryMonth();
		if (result.equals(0)) {
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "操作失败!");
			return map;
		}
		if(result.equals(2)){
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "已有本月工资!");
			return map;
		}
		if(result.equals(3)){
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "无职称信息!");
			return map;
		}
		map.put(Const.retCode, Boolean.TRUE);
		map.put(Const.retMsg, "操作成功!");
		return map;
	}

	@RequestMapping("/updateStaffSalary")
	@ResponseBody
	public Map<String, Object> updateStaffSalary(HttpServletRequest request,@RequestBody SalaryForm salary)
			throws BizException {
		return salaryService.updateStaffSalary(salary);
	}

	/**
	 * 删除工资信息
	 * 
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/deleteSalary")
	@ResponseBody
	public Map<String, Object> deleteSalary(Long id) throws BizException {
		return salaryService.deleteSalary(id);
	}
	/**
	 * 获取职务详情
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getSalaryInfo")
	@ResponseBody
	public Map<String, Object> getSalaryInfo(Long id) throws BizException{
		return salaryService.getSalaryInfo(id);
	}
}
