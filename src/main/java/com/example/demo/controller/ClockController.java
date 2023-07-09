package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.data.Clock;
import com.example.demo.data.Employee;
import com.example.demo.service.ClockService;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 勤怠情報 Controller
 *
 */
@Controller
public class ClockController {
	/**
	 * 勤怠情報 Service
	 */
	@Autowired
	private ClockService clockService;

	/**
	 * 社員情報　Service
	 */
	@Autowired
	private EmployeeService employeeService;

	/**
	 * デフォルトコンストラクタ
	 */
	public ClockController() {

	}

	/**
	 * コンストラクタ
	 * @param clockService
	 */
	public ClockController(ClockService clockService) {
		this.clockService = clockService;
	}

	@PostMapping("/attendance/clockRegister")
	public String registerClock(@RequestParam("button") String button, @RequestParam("employeeId") Number employeeId,
			Model model) throws JsonMappingException, JsonProcessingException {
		clockService.registerClock(button, employeeId);

		List<Employee> employeeList = employeeService.getEmployee(employeeId);
		List<Clock> clockList = clockService.getClock(employeeId);

		model.addAttribute("employeeList", employeeList);
		model.addAttribute("clockList", clockList);

		return "employee-detail.html";
	}
}
