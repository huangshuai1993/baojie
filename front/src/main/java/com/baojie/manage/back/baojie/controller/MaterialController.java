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

import com.baojie.manage.back.baojie.form.MaterialForm;
import com.baojie.manage.back.baojie.form.PositionForm;
import com.baojie.manage.back.baojie.form.TowerForm;
import com.baojie.manage.back.baojie.service.BTowerService;
import com.baojie.manage.back.baojie.service.MaterialService;
import com.baojie.manage.back.baojie.service.PositionService;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.common.util.PageUtil;
import com.baojie.manage.base.controller.BaseController;
import com.baojie.manage.base.exception.BizException;

@Controller
@RequestMapping("/material")
public class MaterialController extends BaseController {
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private BTowerService towerService;

	/**
	 * 获取所有职位信息
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @param towerName
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getAllMaterial")
	public String getAllMaterial(Model model, Integer pageNumber, Integer pageSize, Long towerId)
			throws BizException {
		logger.info("getAllMaterial [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", towerId="
				+ towerId);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<MaterialForm> allMaterial = materialService.getAllMaterial(pageNumber, pageSize, towerId);
		model.addAttribute("allMaterial", allMaterial.getList());
		pageUtil.setTotalCount((int) allMaterial.getTotalCount());
		model.addAttribute("page", pageUtil);
		model.addAttribute("searchTowerId", towerId);
		List<TowerForm> queryAll = towerService.queryAll();
		model.addAttribute("towerList", queryAll);
		return "baojie/getAllMaterial";
	}

	@RequestMapping("/addOrUpdateMaterial")
	@ResponseBody
	public Map<String, Object> addOrUpdateMaterial(HttpServletRequest request, @RequestBody MaterialForm materialForm)
			throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (materialForm == null) {
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "物料信息不能为空!");
			return map;
		}
		if(materialForm.getTowerId() == null){
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "楼盘信息不能为空!");
			return map;
		}
		Integer result = materialService.addMaterial(materialForm);
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
	 * 删除物料信息
	 * 
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/deleteMaterial")
	@ResponseBody
	public Map<String, Object> deleteMaterial(Long id) throws BizException {
		return materialService.deleteMaterial(id);
	}
	/**
	 * 获取职务详情
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getMaterialInfo")
	@ResponseBody
	public Map<String, Object> getMaterialInfo(Long id) throws BizException{
		return materialService.getMaterialInfo(id);
	}
}
