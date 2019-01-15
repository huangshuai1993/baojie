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
import com.baojie.manage.back.baojie.form.TowerForm;
import com.baojie.manage.back.baojie.form.enums.MaterialTypeEnum;
import com.baojie.manage.back.baojie.service.BTowerService;
import com.baojie.manage.back.baojie.service.MaterialService;
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
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/material")
public class MaterialController extends BaseController {
	@Autowired
	private MaterialService materialService;

	@Autowired
	private BTowerService towerService;

	
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
	public void csvDownLoadAllMaterial(HttpServletRequest request,HttpServletResponse response, Integer pageNumber, Integer pageSize, Long towerId,String beginTime,String endTime) throws Exception {
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
		PageResults<MaterialForm> allMaterial = materialService.getAllMaterial(pageNumber, pageSize, towerId,beginTime,endTime);
		List<MaterialDownLoad> list = BeanUtils.copyByList(allMaterial.getList(), MaterialDownLoad.class);
		List<Map<String, Object>> csvData = list.stream().map(d -> JsonUtils.parseObjectAsJackson(d, new TypeReference<Map<String, Object>>() {
		})).collect(Collectors.toList());
		long totalCount = allMaterial.getTotalCount();
		String sheetNamePrefix = DateUtil.getDateStr(DateUtil.TIME_STR_FORMAT);
         Map<String, String> csvHeader = CsvDownloadUtil.getCSVHeader(MaterialDownLoad.class);
         CsvDownloadUtil.writeHeader(csvHeader, sheetNamePrefix, response);
         CsvDownloadUtil.writeData(csvHeader, csvData, response);
         csvData.clear();
         //总页数
         long totalPageNum = (totalCount / pageSize) + (totalCount % pageSize == 0 ? 0 : 1);
         if (totalPageNum > 1) {
             for (int i = 2; i <= totalPageNum; i++) {
            	 pageNumber = i;
            	 PageResults<MaterialForm> material = materialService.getAllMaterial(pageNumber, pageSize, towerId,beginTime,endTime);
            	 list = BeanUtils.copyByList(material.getList(), MaterialDownLoad.class);    
            	 csvData =list.stream()
                         .map(d -> JsonUtils.parseObjectAsJackson(d, new TypeReference<Map<String, Object>>() {
                         })).collect(Collectors.toList());
	                 CsvDownloadUtil.writeData(csvHeader, csvData, response);
	                 csvData.clear();
             }
         }
	}
	
	
	/**
	 * 获取所有物料信息
	 * 
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @param towerId
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getAllMaterial")
	public String getAllMaterial(Model model, Integer pageNumber, Integer pageSize, Long towerId,String beginTime,String endTime) throws BizException {
		logger.info(
				"getAllMaterial [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", towerId=" + towerId);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 30;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<MaterialForm> allMaterial = materialService.getAllMaterial(pageNumber, pageSize, towerId,beginTime,endTime);
		model.addAttribute("allMaterial", allMaterial.getList());
		pageUtil.setTotalCount((int) allMaterial.getTotalCount());
		model.addAttribute("page", pageUtil);
		model.addAttribute("searchTowerId", towerId);
		List<TowerForm> queryAll = towerService.queryAll();
		model.addAttribute("towerList", queryAll);
		Map<String, String> materialType = materialService.getMaterialType();
		model.addAttribute("materialTypes", materialType);
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
		if (materialForm.getTowerId() == null) {
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
	 * 
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getMaterialInfo")
	@ResponseBody
	public Map<String, Object> getMaterialInfo(Long id) throws BizException {
		return materialService.getMaterialInfo(id);
	}
}
