package com.baojie.manage.back.task.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baojie.manage.back.organize.dto.StoreDto;
import com.baojie.manage.back.organize.dto.WorkerDto;
import com.baojie.manage.back.organize.form.ReginAndStoreForm;
import com.baojie.manage.back.organize.form.WorkerForm;
import com.baojie.manage.back.organize.service.OrganizeManageService;
import com.baojie.manage.back.sys.form.Region;
import com.baojie.manage.back.task.dto.BPhotoDto;
import com.baojie.manage.back.task.dto.BTaskDto;
import com.baojie.manage.back.task.form.BPhotoForm;
import com.baojie.manage.back.task.form.BTaskForm;
import com.baojie.manage.back.task.service.BTaskService;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.DateTimeUtil;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.common.util.PageUtil;
import com.baojie.manage.base.common.util.SysDqHelper;
import com.baojie.manage.base.controller.BaseController;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Lists;

@Controller
@RequestMapping("btask")
public class BTasksController extends BaseController {
	@Autowired
	private BTaskService taskService;
	@Autowired
	private OrganizeManageService organizeManageService;

	@RequestMapping("/updateTaskInfo")
	@ResponseBody
	public Map<String, Object> updateTaskInfo(HttpServletRequest request, @RequestParam(value = "id") Long id,
			@RequestParam(value = "taskStatus") Integer taskStatus, Long storeId, Long empId, Integer removeStatus,
			String removeText, Integer passStatus, String passText) throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (id == null || taskStatus == null) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "参数不能为空");
			return map;
		}
		taskService.updateTaskInfo(id, taskStatus, storeId, empId, removeStatus, removeText, passStatus, passText);
		map.put(Const.retCode, true);
		map.put(Const.retMsg, "任务修改成功");
		return map;
	}

	@RequestMapping("/getTaskInfo")
	public String getTaskInfo(HttpServletRequest request, Model model, Long id) throws BizException {
		logger.info("getTaskInfo [get]: id=" + id);
		BTaskDto taskInfo = taskService.getTaskInfo(id);
		BTaskForm form = new BTaskForm(taskInfo);
		model.addAttribute("task", form);
		// 判断登录人员权限 返回地区及门店列表
		Long employeeId = (Long) request.getAttribute("employeeId");
		List<ReginAndStoreForm> regionAndStore = organizeManageService.getRegionAndStore(employeeId);
		model.addAttribute("regionAndStore", regionAndStore);
		// 获取任务信息 图片
		List<BPhotoForm> homeAddressList = Lists.newArrayList();// 居住地
		List<BPhotoForm> workAddressList = Lists.newArrayList();// 工作地
		List<BPhotoDto> dtoList = taskService.getPhotoListByTaskId(id);
		for (BPhotoDto dto : dtoList) {
			/*if (PhotoTypeEnum.HOMEADDRESS == PhotoTypeEnum.getEnum(dto.getType())) {
				homeAddressList.add(new BPhotoForm(dto));
			} else {
				workAddressList.add(new BPhotoForm(dto));
			}*/
		}
		model.addAttribute("homeAddressList", homeAddressList);
		model.addAttribute("workAddressList", workAddressList);
		return "task/getTaskInfo";
	}

	@RequestMapping("/getTaskList")
	public String getTaskList(HttpServletRequest request, Model model, Integer pageNumber, Integer pageSize,
			String name, String mobile, Long workId, Integer taskStatus, Long searchStoreId) throws BizException {
		logger.info("worker [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		if (workId == null) {// 外访id:全部
			workId = 0L;
		}
		if (taskStatus == null) {// 状态:全部
			taskStatus = 10;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		// 条件查询返回
		model.addAttribute("searchName", name);
		model.addAttribute("searchMobile", mobile);
		model.addAttribute("searchWorkId", workId);
		model.addAttribute("searchStatus", taskStatus);
		model.addAttribute("searchStoreId", searchStoreId);
		// 获取枚举-状态
		//model.addAttribute("taskStatus", TaskStatusEnum.values());
		// 获取省份区域信息
		String code = null;
		List<Region> lookDqByParentCode = SysDqHelper.lookDqByParentCode(code);
		model.addAttribute("regionList", lookDqByParentCode);
		// 权限判断
		Long storeId = (Long) request.getAttribute("storeId");
		if (storeId != null) {
			searchStoreId = storeId;
		} else {
			if (searchStoreId == null) {
				searchStoreId = 0L;// 管理员全部
			}
		}
		// 调用service
		PageResults<BTaskDto> taskList = taskService.getTaskList(name, mobile, workId, taskStatus, searchStoreId,
				pageNumber, pageSize);
		List<BTaskDto> list = taskList.getList();
		List<BTaskForm> formList = Lists.newArrayList();
		for (BTaskDto bTaskDto : list) {
			formList.add(new BTaskForm(bTaskDto));
		}
		model.addAttribute("taskList", formList);
		pageUtil.setTotalCount((int) taskList.getTotalCount());
		model.addAttribute("page", pageUtil);
		// 获取所有门店
		List<StoreDto> storeListByStoreId = organizeManageService.getStoreListByStoreId(storeId);
		model.addAttribute("storeList", storeListByStoreId);
		// 判断登录人员权限 返回地区及门店列表
		Long employeeId = (Long) request.getAttribute("employeeId");
		List<ReginAndStoreForm> regionAndStore = organizeManageService.getRegionAndStore(employeeId);
		model.addAttribute("regionAndStore", regionAndStore);
		if (storeId != null) {// 返回门店主管页面
			// 获取门店下所有员工
			List<WorkerDto> workerListByStoreId = organizeManageService.getWorkerListByStoreId(storeId);
			List<WorkerForm> workForm = Lists.newArrayList();
			for (WorkerDto workerDto : workerListByStoreId) {
				workForm.add(new WorkerForm(workerDto));
			}
			model.addAttribute("workForm", workForm);
			return "task/getTaskLists";
		} else {// 返回超级管理员页面
			return "task/getTaskList";
		}
	}

	@RequestMapping("/addTask")
	@ResponseBody
	public Map<String, Object> addTask(HttpServletRequest request, @RequestParam(value = "name") String name,
			@RequestParam(value = "idcardNo") String idcardNo, @RequestParam(value = "birthday") String birthday,
			@RequestParam(value = "mobile") String mobile, @RequestParam(value = "gender") Integer gender,
			@RequestParam(value = "marriage") Integer marriage, @RequestParam(value = "income") Integer income,
			@RequestParam(value = "hasCautioner") Integer hasCautioner, String cautionerName, String cautionerIdcardNo,
			String cautionerRelation, @RequestParam(value = "homeProvinceCode") String homeProvinceCode,
			@RequestParam(value = "homeCityCode") String homeCityCode,
			@RequestParam(value = "homeRegionCode") String homeRegionCode,
			@RequestParam(value = "homeAddress") String homeAddress,
			@RequestParam(value = "workProvinceCode") String workProvinceCode,
			@RequestParam(value = "workCityCode") String workCityCode,
			@RequestParam(value = "workRegionCode") String workRegionCode,
			@RequestParam(value = "workAddress") String workAddress, @RequestParam(value = "product") Integer product,
			@RequestParam(value = "amount") Integer amount, @RequestParam(value = "source") Integer source,
			Integer storeId, Long empId) throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(idcardNo) || birthday == null
				|| StringUtils.isEmpty(mobile)) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "参数不能为空");
			return map;
		}
		// 转化类型
		Date date = DateTimeUtil.StrToDate(birthday);
		BTaskDto task = new BTaskDto();
		task.setName(name);
		task.setIdcardNo(idcardNo);
		task.setBirthday(date);
		task.setMobile(mobile);
		task.setGender(gender);
		task.setMarriage(marriage);
		task.setIncome(income);
		task.setHasCautioner(hasCautioner);
		if (hasCautioner == 1) {
			task.setCautionerName(cautionerName);
			task.setCautionerIdcardNo(cautionerIdcardNo);
			task.setCautionerRelation(cautionerRelation);
		}
		task.setHomeProvinceCode(homeProvinceCode);
		task.setHomeCityCode(homeCityCode);
		task.setHomeRegionCode(homeRegionCode);
		task.setHomeAddress(homeAddress);
		task.setWorkProvinceCode(workProvinceCode);
		task.setWorkCityCode(workCityCode);
		task.setWorkRegionCode(workRegionCode);
		task.setWorkAddress(workAddress);
		task.setProduct(product);
		task.setAmount(amount);
		task.setSource(source);
		if (empId != null && empId != 0) {
			task.setEmpId(empId);
			//task.setTaskStatus(TaskStatusEnum.OK_DISTRIBUTION.getId());
		}
		if (storeId != null) {
			task.setStoreId(storeId);
		}
		taskService.addBTask(task);
		map.put(Const.retCode, Boolean.TRUE);
		map.put(Const.retMsg, "添加成功!");
		return map;
	}

	@RequestMapping("/removeTask")
	@ResponseBody
	public Map<String, Object> removeTask(HttpServletRequest request, @RequestParam(value = "memo") String memo,
			@RequestParam(value = "id") Long id) throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (id == null || memo == null) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "参数不能为空");
			return map;
		}
		taskService.removeTask(id, memo);
		map.put(Const.retCode, true);
		map.put(Const.retMsg, "更新成功!");
		return map;
	}

	/**
	 * 分配任务
	 * 
	 * @param request
	 * @param memo
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/allocatingTask")
	@ResponseBody
	public Map<String, Object> allocatingTask(HttpServletRequest request, @RequestParam(value = "id") Long id,
			@RequestParam(value = "empId") Long empId, @RequestParam(value = "storeId") Long storeId)
			throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (id == null || empId == null || storeId == null) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "参数不能为空");
			return map;
		}
		taskService.allocatingTask(id, empId, storeId);
		map.put(Const.retCode, true);
		map.put(Const.retMsg, "更新成功!");
		return map;
	}

	/**
	 * 根据员工id获得该员工任务量
	 * 
	 * @param request
	 * @param empId
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/findTaskCountByEmpId")
	@ResponseBody
	public Map<String, Object> findTaskCountByEmpId(HttpServletRequest request,
			@RequestParam(value = "empId") Long empId) throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (empId == null) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "参数不能为空");
			return map;
		}
		List<Integer> taskStatus = Lists.newArrayList();
		//taskStatus.add(TaskStatusEnum.OK_DISTRIBUTION.getId());
		//taskStatus.add(TaskStatusEnum.NO_PAY.getId());
		Integer count = taskService.findTaskCountByEmpId(empId, taskStatus);
		map.put("count", count);
		map.put(Const.retCode, true);
		map.put(Const.retMsg, "更新成功!");
		return map;
	}
}
