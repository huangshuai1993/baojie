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

import com.baojie.manage.back.baojie.form.ConfigDetailForm;
import com.baojie.manage.back.baojie.form.ConfigForm;
import com.baojie.manage.back.baojie.service.ConfigService;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.common.util.PageUtil;
import com.baojie.manage.base.controller.BaseController;
import com.baojie.manage.base.exception.BizException;

@Controller
@RequestMapping("/config")
public class ConfigController extends BaseController {
	@Autowired
	private ConfigService configService;
	
	
	/**
	 * 查询配置项列表
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @param describe
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getAllConfig")
	public String getAllConfig(Model model, Integer pageNumber, Integer pageSize, String configDesc) throws BizException {
		logger.info("getAllConfig [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize+ ", configDesc=" + configDesc);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 30;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<ConfigForm> allConfig = configService.getAllConfig(pageNumber, pageSize, configDesc);
		model.addAttribute("configDesc", configDesc);
		model.addAttribute("allConfig", allConfig.getList());
		pageUtil.setTotalCount((int) allConfig.getTotalCount());
		model.addAttribute("page", pageUtil);
		return "baojie/getAllConfig";
	}

	
	/**
	 * 查询配置明细列表
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @param configuration
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getAllConfigDetail")
	public String getAllConfigDetail(Model model, Integer pageNumber, Integer pageSize, String configuration,String configDetailDesc) throws BizException {
		logger.info("getAllConfig [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize+ ", configDetailDesc=" + configDetailDesc);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 30;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<ConfigDetailForm> allConfigDetail = configService.getAllConfigDetail(pageNumber, pageSize, configuration,configDetailDesc);
		model.addAttribute("configuration", configuration);
		model.addAttribute("allConfigDetail", allConfigDetail.getList());
		pageUtil.setTotalCount((int) allConfigDetail.getTotalCount());
		model.addAttribute("page", pageUtil);
		List<ConfigForm> configList = configService.queryConfigAll();
		model.addAttribute("configList", configList);
		model.addAttribute("configuration", configuration);
		model.addAttribute("configDetailDesc", configDetailDesc);
		return "baojie/getAllConfigDetail";
	}
	
	/**
	 * 添加配置项 修改配置项
	 * @param request
	 * @param configForm
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/addOrUpdateConfig")
	@ResponseBody
	public Map<String, Object> addOrUpdateConfig(HttpServletRequest request, @RequestBody ConfigForm configForm)
			throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (configForm == null) {
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "配置信息不能为空!");
			return map;
		}
		return configService.addOrUpdateConfig(configForm);
	}
	/**
	 *  添加配置明细项 修改配置明细项
	 * @param request
	 * @param configDetailForm
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/addOrUpdateConfigDetail")
	@ResponseBody
	public Map<String, Object> addOrUpdateConfigDetail(HttpServletRequest request, @RequestBody ConfigDetailForm configDetailForm)
			throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (configDetailForm == null) {
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "配置信息不能为空!");
			return map;
		}
		return configService.addOrUpdateConfigDetail(configDetailForm);
	}
	/**
	 *  查询配置项详情
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getInfoConfig")
	@ResponseBody
	public Map<String, Object> getInfoConfig(Long id) throws BizException{
		return configService.getInfoConfig(id);
	}
	/**
	 *  查询配置项明细详情
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getInfoConfigDetail")
	@ResponseBody
	public Map<String, Object> getInfoConfigDetail(Long id) throws BizException{
		return configService.getInfoConfigDetail(id);
	}
	/**
	 * 删除配置明细
	 */
	@RequestMapping("/deleteConfigDetail")
	@ResponseBody
	public Map<String, Object> deleteConfigDetail(Long id) throws BizException {
		return configService.deleteConfigDetail(id);
	}
	/**
	 * 删除配置项
	 */
	@RequestMapping("/deleteConfig")
	@ResponseBody
	public Map<String, Object> deleteConfig(Long id) throws BizException {
		return configService.deleteConfig(id);
	}
	
}
