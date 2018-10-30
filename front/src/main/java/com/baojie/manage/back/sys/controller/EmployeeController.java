package com.baojie.manage.back.sys.controller;

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
import com.baojie.manage.back.organize.form.StoreForm;
import com.baojie.manage.back.organize.service.OrganizeManageService;
import com.baojie.manage.back.sys.dto.EmployeeDto;
import com.baojie.manage.back.sys.dto.PersonaDto;
import com.baojie.manage.back.sys.form.EmployeeForm;
import com.baojie.manage.back.sys.form.PersonaForm;
import com.baojie.manage.back.sys.service.EmployeeService;
import com.baojie.manage.back.sys.service.PersonaService;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.GetUniqueNoUtil;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.common.util.PageUtil;
import com.baojie.manage.base.controller.BaseController;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Lists;

@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private OrganizeManageService StoreService;

	/**
	 * 获得员工列表
	 * 
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @param personaName
	 * @param parameName
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getAllEmployees")
	public String getAllEmployees(Model model, Integer pageNumber, Integer pageSize, String personaName,
			String parameName) throws BizException {
		logger.info("employee [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		PageUtil pageUtil = new PageUtil(pageSize);
		pageUtil.setPageIndex(pageNumber);
		PageResults<EmployeeDto> employeePageList = employeeService.getEmployeePageList(personaName, parameName,
				pageNumber, pageSize);
		List<EmployeeDto> list = employeePageList.getList();
		List<EmployeeForm> formList = Lists.newArrayList();
		for (EmployeeDto employeeDto : list) {
			formList.add(new EmployeeForm(employeeDto));
		}
		model.addAttribute("employeeList", formList);
		pageUtil.setTotalCount((int) employeePageList.getTotalCount());
		model.addAttribute("page", pageUtil);
		List<PersonaDto> allPersonas = personaService.getAllPersonas();
		List<PersonaForm> personas = Lists.newArrayList();
		for (PersonaDto personaDto : allPersonas) {
			personas.add(new PersonaForm(personaDto));
		}
		/*List<StoreDto> storeDtoList = StoreService.getStoreList();
		List<StoreForm> storeForm = Lists.newArrayList();
		for (StoreDto storeDto : storeDtoList) {
			storeForm.add(new StoreForm(storeDto));
		}
		model.addAttribute("stores", storeForm);*/
		model.addAttribute("personas", personas);
		return "employee/getAllEmployees";
	}

	/**
	 * 添加员工
	 * 
	 * @param request
	 * @param response
	 * @param employee
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/addEmployee")
	@ResponseBody
	public Map<String, Object> addEmployee(HttpServletRequest request,
			@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
			@RequestParam(value = "realName") String realName,
			@RequestParam(value = "employIDCardNum") String employIDCardNum,
			@RequestParam(value = "phone") String phone, @RequestParam(value = "personaId") Long personaId,
			Long storeId) throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(realName)) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "登录名或真实姓名不能为空");
			return map;
		}
		EmployeeDto employee = new EmployeeDto();
		Long result = null;
		String custNo = GetUniqueNoUtil.getCustNo();
		employee.setUsername(username);
		employee.setPassword(password);
		employee.setRealName(realName);
		employee.setEmployIDCardNum(employIDCardNum);
		employee.setPhone(phone);
		employee.setEmpRegTime(new Date());
		// 可用
		employee.setEmpStatus("1");
		employee.setCustNo(custNo);
		employee.setStoreId(storeId);
		result = employeeService.addEmployee(employee, personaId);
		if (result.equals(0l)) {
			map.put(Const.retCode, Boolean.FALSE);
			map.put(Const.retMsg, "该员工已经存在!");
			return map;
		}
		map.put(Const.retCode, Boolean.TRUE);
		map.put(Const.retMsg, "添加成功!");
		return map;
	}

	/**
	 * 更新员工状态
	 * 
	 * @param request
	 * @param empStatus
	 * @param custNo
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/updateEmpState")
	@ResponseBody
	public Map<String, Object> updateEmpState(HttpServletRequest request,
			@RequestParam(value = "empStatus") String empStatus, @RequestParam(value = "custNo") String custNo)
			throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		map = employeeService.updateEnable(empStatus, custNo);
		return map;
	}

	/**
	 * 更新密码
	 * 
	 * @param request
	 * @param password
	 * @param custNo
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public Map<String, Object> updatePassword(HttpServletRequest request,
			@RequestParam(value = "password") String password, @RequestParam(value = "repassword") String repassword,
			@RequestParam(value = "custNo") String custNo) throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(password)) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "新密码不能为空!");
			return map;
		}
		;
		if (password.length() < 4 || password.length() > 16) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "密码在4-16位之间!");
			return map;
		}
		;
		if (StringUtils.isEmpty(repassword)) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "重复密码不能为空!");
			return map;
		}
		;
		if (!password.equals(repassword)) {
			map.put("retCode", false);
			map.put("retMsg", "两次密码不相等!");
			return map;
		}
		;
		return employeeService.updatePassword(repassword, custNo);

	}

	/**
	 * 删除员工
	 * 
	 * @param custNo
	 * @return
	 */
	@RequestMapping("/deleteEmployee")
	@ResponseBody
	public Map<String, Object> deleteEmployee(String custNo) {
		return employeeService.deleteEmployee(custNo);
	}

	/**
	 * 得到管理员的详细信息
	 * 
	 * @param custNo
	 * @return
	 */
	@RequestMapping("/getEmployeeInfo")
	@ResponseBody
	public Map<String, Object> getEmployeeInfo(String custNo) throws BizException {
		return employeeService.getEmployeeInfo(custNo);
	}

	/**
	 * 更新员工
	 * 
	 * @param request
	 * @param username
	 * @param password
	 * @param realName
	 * @param employIDCardNum
	 * @param phone
	 * @param personaId
	 * @return
	 */
	@RequestMapping("/updateEmployee")
	@ResponseBody
	public Map<String, Object> updateEmployee(HttpServletRequest request,
			@RequestParam(value = "username") String username, @RequestParam(value = "realName") String realName,
			@RequestParam(value = "employIDCardNum") String employIDCardNum,
			@RequestParam(value = "phone") String phone, @RequestParam(value = "personaId") Long personaId,
			@RequestParam(value = "employeeId") Long employeeId, @RequestParam(value = "custNo") String custNo,
			Long storeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(realName)) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "登录名或真实姓名不能为空");
			return map;
		}
		if (custNo == null) {
			map.put(Const.retCode, false);
			map.put(Const.retMsg, "custNo参数不能为空");
			return map;
		}
		EmployeeDto employee = new EmployeeDto();
		employee.setCustNo(custNo);
		employee.setUsername(username);
		employee.setRealName(realName);
		employee.setEmployIDCardNum(employIDCardNum);
		employee.setPhone(phone);
		employee.setEmployeeId(employeeId);
		employee.setStoreId(storeId);
		if (personaId == 1) {
			employee.setEmployeeType("1");
		} else {
			employee.setEmployeeType("2");
		}
		return employeeService.updateEmployee(employee, personaId);

	}

}
