package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.data.Clock;
import com.example.demo.data.Employee;
import com.example.demo.service.ClockService;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 社員情報表示　Controller
 *
 */
@Controller
public class EmployeeController {
	/**
	 * 社員情報　Service
	 */
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 勤怠情報 Service
	 */
	@Autowired
	private ClockService clockService;

	/**
	 * デフォルトコンストラクタ
	 */
	public EmployeeController() {

	}

	/**
	 * コンストラクタ
	 * @param employeeService
	 */
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/*
	 * 社員情報一覧画面表示
	 * @param model
	 * @return 社員情報一覧画面のHTML
	 */
	@GetMapping("/attendance/employeeList")
	public String getEmployeeList(Model model) throws JsonMappingException, JsonProcessingException {
		List<Employee> employeeList = employeeService.getEmployee(null);

		model.addAttribute("employeeList", employeeList);

		return "employee-list.html";
	}

	@PostMapping("/attendance/employeeDetail")
	public String getEmployeeDetail(@RequestParam("employeeId") Number employeeId, Model model)
			throws JsonMappingException, JsonProcessingException {
		List<Employee> employeeList = employeeService.getEmployee(employeeId);;
		List<Clock> clockList = clockService.getClock(employeeId);

		model.addAttribute("employeeList", employeeList);
		model.addAttribute("clockList", clockList);

		return "employee-detail.html";
	}
}
