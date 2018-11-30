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

import com.baojie.manage.back.baojie.form.PositionForm;
import com.baojie.manage.back.baojie.service.PositionService;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.common.util.PageUtil;
import com.baojie.manage.base.controller.BaseController;
import com.baojie.manage.base.exception.BizException;

@Controller
@RequestMapping("position")
public class PositionController extends BaseController {
	@Autowired
	private PositionService positionService;

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
	public String getAllPosition(Model model, Integer pageNumber, Integer pageSize, String towerName)
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
		PageResults<PositionForm> allPosition = positionService.getAllPosition(pageNumber, pageSize, towerName);
		model.addAttribute("allPosition", allPosition.getList());
		pageUtil.setTotalCount((int) allPosition.getTotalCount());
		model.addAttribute("page", pageUtil);
		return "employee/getAllEmployees";
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
}
