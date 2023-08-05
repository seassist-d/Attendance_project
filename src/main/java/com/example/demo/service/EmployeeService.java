package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.Employee;
import com.example.demo.data.EmployeeRegist;
import com.example.demo.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 社員情報 Service
 *
 */
@Service
public class EmployeeService {
	/**
	 * 社員情報 Repository
	 */
	private EmployeeRepository employeeRepository;
	
	/**
	 * デフォルトコンストラクタ
	 */
	public EmployeeService() {
		
	}
	
	/**
	 * コンストラクタ
	 * @param employeeRepository
	 */
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	/**
	 * idが一致する社員情報取得、idがnullの場合全件取得
	 * @param employeeId 社員ID
	 * @return 社員情報
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 */
	public List<Employee> getEmployee(Number employeeId) throws JsonMappingException, JsonProcessingException {
		Employee[] employeeList = employeeRepository.getEmployee(employeeId);
		
		return Arrays.asList(employeeList);
	}
	
	/**
	 * 社員情報登録
	 * @param name 氏名
	 * @param hometown 出身地
	 * @param joiningMonth 入社月
	 */
	public void registerEmployee(String name, String hometown, String joiningMonth) {	
		EmployeeRegist employeeRegist = new EmployeeRegist();
		employeeRegist.setName(name);
		employeeRegist.setHometown(hometown);
		employeeRegist.setJoiningMonth(joiningMonth);
		
		employeeRepository.registerEmployee(employeeRegist);
	}
}
