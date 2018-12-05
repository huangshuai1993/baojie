package com.baojie.manage.back.baojie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baojie.manage.back.baojie.service.SalaryService;
import com.baojie.manage.base.controller.BaseController;

@Controller
@RequestMapping("/salary")
public class SalaryController extends BaseController {

	@Autowired
	private SalaryService salaryService;

}
