package com.baojie.manage.back.baojie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baojie.manage.back.baojie.form.SalaryForm;
import com.baojie.manage.back.baojie.form.TowerForm;
import com.baojie.manage.back.baojie.service.BTowerService;
import com.baojie.manage.back.baojie.service.SalaryService;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.common.util.PageUtil;
import com.baojie.manage.base.controller.BaseController;
import com.baojie.manage.base.exception.BizException;

@Controller
@RequestMapping("/salary")
public class SalaryController extends BaseController {
	
	@Autowired
	private BTowerService towerService;
	
	@Autowired
	private SalaryService salaryService;

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
