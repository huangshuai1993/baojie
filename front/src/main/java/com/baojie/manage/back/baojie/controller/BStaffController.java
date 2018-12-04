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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baojie.manage.back.baojie.form.PositionForm;
import com.baojie.manage.back.baojie.form.StaffForm;
import com.baojie.manage.back.baojie.form.TowerForm;
import com.baojie.manage.back.baojie.service.BStaffService;
import com.baojie.manage.back.baojie.service.BTowerService;
import com.baojie.manage.back.baojie.service.PositionService;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.common.util.PageUtil;
import com.baojie.manage.base.controller.BaseController;
import com.baojie.manage.base.exception.BizException;

@Controller
@RequestMapping("/bstaff")
public class BStaffController extends BaseController {
	@Autowired
	private BStaffService staffService;
	@Autowired
	private BTowerService towerService;

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
	public String getAllStaff(Model model, Integer pageNumber, Integer pageSize, String towerName,String positionName)
			throws BizException {
		logger.info("getAllPosition [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", towerName="
				+ towerName);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<StaffForm> allStaff = staffService.getAllStaff(pageNumber, pageSize, towerName, positionName);
		model.addAttribute("allStaff", allStaff.getList());
		pageUtil.setTotalCount((int) allStaff.getTotalCount());
		model.addAttribute("page", pageUtil);
		List<TowerForm> queryAll = towerService.queryAll();
		model.addAttribute("towerList", queryAll);
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
