package com.baojie.manage.back.baojie.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baojie.manage.back.baojie.service.BTowerService;
import com.baojie.manage.base.controller.BaseController;
import com.baojie.manage.base.exception.BizException;

@Controller
@RequestMapping("btower")
public class BTowerController extends BaseController {
	@Autowired
	private BTowerService towerService;

	@RequestMapping("/updateTaskInfo")
	@ResponseBody
	public Map<String, Object> updateTaskInfo(HttpServletRequest request, @RequestParam(value = "id") Long id,
			@RequestParam(value = "taskStatus") Integer taskStatus, Long storeId, Long empId, Integer removeStatus,
			String removeText, Integer passStatus, String passText) throws BizException {
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}
}
