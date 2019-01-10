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

import com.baojie.manage.back.baojie.form.PositionDownLoad;
import com.baojie.manage.back.baojie.form.PositionForm;
import com.baojie.manage.back.baojie.form.TowerForm;
import com.baojie.manage.back.baojie.service.BTowerService;
import com.baojie.manage.back.baojie.service.PositionService;
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
@RequestMapping("/position")
public class PositionController extends BaseController {
	@Autowired
	private PositionService positionService;
	
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
	@RequestMapping("/csvDownLoadAllPosition")
	public void csvDownLoadAllMaterial(HttpServletRequest request,HttpServletResponse response, Integer pageNumber, Integer pageSize, Long towerId) throws Exception {
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
		PageResults<PositionForm> allPosition = positionService.getAllPosition(pageNumber, pageSize, towerId);
		List<PositionDownLoad> list = BeanUtils.copyByList(allPosition.getList(), PositionDownLoad.class);
		List<Map<String, Object>> csvData = list.stream().map(d -> JsonUtils.parseObjectAsJackson(d, new TypeReference<Map<String, Object>>() {
		})).collect(Collectors.toList());
		long totalCount = allPosition.getTotalCount();
		String sheetNamePrefix = DateUtil.getDateStr(DateUtil.TIME_STR_FORMAT);
         Map<String, String> csvHeader = CsvDownloadUtil.getCSVHeader(PositionDownLoad.class);
         CsvDownloadUtil.writeHeader(csvHeader, sheetNamePrefix, response);
         CsvDownloadUtil.writeData(csvHeader, csvData, response);
         csvData.clear();
         //总页数
         long totalPageNum = (totalCount / pageSize) + (totalCount % pageSize == 0 ? 0 : 1);
         if (totalPageNum > 1) {
             for (int i = 2; i <= totalPageNum; i++) {
            	 pageNumber = i;
            	 PageResults<PositionForm> position = positionService.getAllPosition(pageNumber, pageSize, towerId);
            	 list = BeanUtils.copyByList(position.getList(), PositionDownLoad.class);    
            	 csvData =list.stream()
                         .map(d -> JsonUtils.parseObjectAsJackson(d, new TypeReference<Map<String, Object>>() {
                         })).collect(Collectors.toList());
	                 CsvDownloadUtil.writeData(csvHeader, csvData, response);
	                 csvData.clear();
             }
         }
	}
	

	/**
	 * 获取所有职位信息
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @param towerName
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getAllPosition")
	public String getAllPosition(Model model, Integer pageNumber, Integer pageSize, Long towerId)
			throws BizException {
		logger.info("getAllPosition [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", towerId="
				+ towerId);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<PositionForm> allPosition = positionService.getAllPosition(pageNumber, pageSize, towerId);
		model.addAttribute("allPosition", allPosition.getList());
		pageUtil.setTotalCount((int) allPosition.getTotalCount());
		model.addAttribute("page", pageUtil);
		model.addAttribute("searchTowerId", towerId);
		List<TowerForm> queryAll = towerService.queryAll();
		model.addAttribute("towerList", queryAll);
		return "baojie/getAllPosition";
	}

	@RequestMapping("/addOrUpdatePosition")
	@ResponseBody
	public Map<String, Object> addOrUpdatePosition(HttpServletRequest request, @RequestBody PositionForm positionform)
			throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (positionform == null) {
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "职称信息不能为空!");
			return map;
		}
		if(positionform.getTowerId() == null){
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "楼盘信息不能为空!");
			return map;
		}
		Integer result = positionService.addPosition(positionform);
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
	 * 删除职位
	 * 
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/deletePosition")
	@ResponseBody
	public Map<String, Object> deletePosition(Long id) throws BizException {
		return positionService.deletePosition(id);
	}
	/**
	 * 获取职务详情
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getPositionInfo")
	@ResponseBody
	public Map<String, Object> getPositionInfo(Long id) throws BizException{
		return positionService.getPositionInfo(id);
	}
}
