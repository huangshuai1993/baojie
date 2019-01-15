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

import com.baojie.manage.back.baojie.form.StaffDownLoad;
import com.baojie.manage.back.baojie.form.StaffForm;
import com.baojie.manage.back.baojie.form.TowerForm;
import com.baojie.manage.back.baojie.service.BStaffService;
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
@RequestMapping("/bstaff")
public class BStaffController extends BaseController {
	@Autowired
	private BStaffService staffService;
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
	@RequestMapping("/csvDownLoadAllStaff")
	public void csvDownLoadAllMaterial(HttpServletRequest request,HttpServletResponse response, Integer pageNumber, Integer pageSize, Long towerId,String staffName) throws Exception {
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
		PageResults<StaffForm> allStaff = staffService.getAllStaff(pageNumber, pageSize, towerId, staffName);
		List<StaffDownLoad> list = BeanUtils.copyByList(allStaff.getList(), StaffDownLoad.class);
		List<Map<String, Object>> csvData = list.stream().map(d -> JsonUtils.parseObjectAsJackson(d, new TypeReference<Map<String, Object>>() {
		})).collect(Collectors.toList());
		long totalCount = allStaff.getTotalCount();
		String sheetNamePrefix = DateUtil.getDateStr(DateUtil.TIME_STR_FORMAT);
         Map<String, String> csvHeader = CsvDownloadUtil.getCSVHeader(StaffDownLoad.class);
         CsvDownloadUtil.writeHeader(csvHeader, sheetNamePrefix, response);
         CsvDownloadUtil.writeData(csvHeader, csvData, response);
         csvData.clear();
         //总页数
         long totalPageNum = (totalCount / pageSize) + (totalCount % pageSize == 0 ? 0 : 1);
         if (totalPageNum > 1) {
             for (int i = 2; i <= totalPageNum; i++) {
            	 pageNumber = i;
            	 PageResults<StaffForm> staff = staffService.getAllStaff(pageNumber, pageSize, towerId, staffName);
            	 list = BeanUtils.copyByList(staff.getList(), StaffDownLoad.class);    
            	 csvData =list.stream()
                         .map(d -> JsonUtils.parseObjectAsJackson(d, new TypeReference<Map<String, Object>>() {
                         })).collect(Collectors.toList());
	                 CsvDownloadUtil.writeData(csvHeader, csvData, response);
	                 csvData.clear();
             }
         }
	}
	
	/**
	 * 获取所有人员
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @param towerName
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getAllStaff")
	public String getAllStaff(Model model, Integer pageNumber, Integer pageSize, Long towerId,String staffName)
			throws BizException {
		logger.info("getAllPosition [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", towerId=" + towerId
				+ ", staffName=" + staffName);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 30;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<StaffForm> allStaff = staffService.getAllStaff(pageNumber, pageSize, towerId, staffName);
		model.addAttribute("allStaff", allStaff.getList());
		pageUtil.setTotalCount((int) allStaff.getTotalCount());
		model.addAttribute("page", pageUtil);
		List<TowerForm> queryAll = towerService.queryAll();
		model.addAttribute("towerList", queryAll);
		model.addAttribute("searchTowerId", towerId);
		model.addAttribute("searchName", staffName);
		Map<String, String> staffStatusType = staffService.getStaffStatusType();
		model.addAttribute("staffStatusType", staffStatusType);
		return "baojie/getAllStaff";
	}

	@RequestMapping("/addOrUpdateStaff")
	@ResponseBody
	public Map<String, Object> addOrUpdateStaff(HttpServletRequest request, @RequestBody StaffForm staffForm)
			throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (staffForm == null) {
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "人员信息不能为空!");
			return map;
		}
		if(staffForm.getTowerId() == null || staffForm.getPositionId() == null){
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "楼盘及职务信息不能为空!");
			return map;
		}
		Integer result = staffService.addStaff(staffForm);
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
	@RequestMapping("/deleteStaff")
	@ResponseBody
	public Map<String, Object> deleteStaff(Long id) throws BizException {
		return staffService.deleteStaff(id);
	}
	/**
	 * 获取详情
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getStaffInfo")
	@ResponseBody
	public Map<String, Object> getStaffInfo(Long id) throws BizException {
		return staffService.getStaffInfo(id);
	}
	
	//根据楼盘id查询所有楼盘下职务信息
	@RequestMapping("/getPositionListByTowerId")
	@ResponseBody
	public Map<String, Object> getPositionListByTowerId(Long id) throws BizException {
		return staffService.getPositionListByTowerId(id);
	}
	
}
