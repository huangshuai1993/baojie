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

import com.baojie.manage.back.baojie.form.TowerForm;
import com.baojie.manage.back.baojie.service.BContractService;
import com.baojie.manage.back.baojie.service.BTowerService;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.common.util.PageUtil;
import com.baojie.manage.base.controller.BaseController;
import com.baojie.manage.base.exception.BizException;

@Controller
@RequestMapping("btower")
public class BTowerController extends BaseController {
	@Autowired
	private BTowerService towerService;
	
	/**
	 * 获取所以楼盘信息
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @param towerName
	 * @param status
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getAllTower")
	public String getAllTower(Model model, Integer pageNumber, Integer pageSize, String towerName, String functionaryName) throws BizException {
		logger.info("getAllContract [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize+ ", towerName=" + towerName+", functionaryName=" + functionaryName);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<TowerForm> allTower = towerService.getAllTower(pageNumber, pageSize, towerName, functionaryName);
		model.addAttribute("allTowerList", allTower.getList());
		pageUtil.setTotalCount((int) allTower.getTotalCount());
		model.addAttribute("page", pageUtil);
		return "employee/getAllEmployees";
	}


	@RequestMapping("/addOrUpdateTower")
	@ResponseBody
	public Map<String, Object> addTower(HttpServletRequest request, @RequestBody TowerForm towerForm)
			throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (towerForm == null) {
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "合同信息不能为空!");
			return map;
		}
		Integer result = towerService.addTower(towerForm);
		if (result.equals(0)) {
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "添加失败!");
			return map;
		}
		map.put(Const.retCode, Boolean.TRUE);
		map.put(Const.retMsg, "添加成功!");
		return map;
	}
	/**
	 * 删除楼盘
	 * 
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/deleteTower")
	@ResponseBody
	public Map<String, Object> deleteTower(Long id) throws BizException {
		return towerService.deleteTower(id);
	}
	
}
