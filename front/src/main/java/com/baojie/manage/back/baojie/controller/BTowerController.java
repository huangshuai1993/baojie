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

import com.baojie.manage.back.baojie.form.ContractForm;
import com.baojie.manage.back.baojie.form.TowerDownLoad;
import com.baojie.manage.back.baojie.form.TowerForm;
import com.baojie.manage.back.baojie.service.BContractService;
import com.baojie.manage.back.baojie.service.BTowerService;
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
@RequestMapping("/btower")
public class BTowerController extends BaseController {
	@Autowired
	private BTowerService towerService;
	
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
	@RequestMapping("/csvDownLoadAllTower")
	public void csvDownLoadAllTower(HttpServletRequest request,HttpServletResponse response, Integer pageNumber, Integer pageSize, String towerName, String functionaryName,String beginTime,String endTime) throws Exception {
		logger.info(
				"csvDownLoadAllTower [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", towerName=" + towerName);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 30;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<TowerForm> allTower = towerService.getAllTower(pageNumber, pageSize, towerName, functionaryName, beginTime, endTime);
		List<TowerDownLoad> list = BeanUtils.copyByList(allTower.getList(), TowerDownLoad.class);
		List<Map<String, Object>> csvData = list.stream().map(d -> JsonUtils.parseObjectAsJackson(d, new TypeReference<Map<String, Object>>() {
		})).collect(Collectors.toList());
		long totalCount = allTower.getTotalCount();
		String sheetNamePrefix = DateUtil.getDateStr(DateUtil.TIME_STR_FORMAT);
         Map<String, String> csvHeader = CsvDownloadUtil.getCSVHeader(TowerDownLoad.class);
         CsvDownloadUtil.writeHeader(csvHeader, sheetNamePrefix, response);
         CsvDownloadUtil.writeData(csvHeader, csvData, response);
         csvData.clear();
         //总页数
         long totalPageNum = (totalCount / pageSize) + (totalCount % pageSize == 0 ? 0 : 1);
         if (totalPageNum > 1) {
             for (int i = 2; i <= totalPageNum; i++) {
            	 pageNumber = i;
            	 PageResults<TowerForm> tower = towerService.getAllTower(pageNumber, pageSize, towerName, functionaryName, beginTime, endTime);
            	 list = BeanUtils.copyByList(tower.getList(), TowerDownLoad.class);
            	 csvData = tower.getList().stream()
                         .map(d -> JsonUtils.parseObjectAsJackson(d, new TypeReference<Map<String, Object>>() {
                         })).collect(Collectors.toList());
	                 CsvDownloadUtil.writeData(csvHeader, csvData, response);
	                 csvData.clear();
             }
         }
	}
	
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
	public String getAllTower(Model model, Integer pageNumber, Integer pageSize, String towerName, String functionaryName,String beginTime,String endTime) throws BizException {
		logger.info("getAllContract [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize+ ", towerName=" + towerName+", functionaryName=" + functionaryName);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 30;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<TowerForm> allTower = towerService.getAllTower(pageNumber, pageSize, towerName, functionaryName, beginTime, endTime);
		model.addAttribute("searchName", towerName);
		model.addAttribute("allTowerList", allTower.getList());
		pageUtil.setTotalCount((int) allTower.getTotalCount());
		model.addAttribute("page", pageUtil);
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		Map<String, String> contractMap = contractService.getAllContractMap();
		model.addAttribute("contractMap", contractMap);
		return "baojie/getAllTower";
	}


	@RequestMapping("/addOrUpdateTower")
	@ResponseBody
	public Map<String, Object> addTower(HttpServletRequest request, @RequestBody TowerForm towerForm)
			throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (towerForm == null) {
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "楼盘信息不能为空!");
			return map;
		}
		Integer result = towerService.addTower(towerForm);
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
	@RequestMapping("/getInfoTower")
	@ResponseBody
	public Map<String, Object> getTowerInfo(Long id) throws BizException{
		return towerService.getTowerInfo(id);
	}
}
