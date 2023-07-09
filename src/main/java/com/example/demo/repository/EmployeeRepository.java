package com.example.demo.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.data.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 社員テーブルリポジトリ
 *
 */
@Repository
public class EmployeeRepository {
	
	/**
	 * idが一致する社員情報取得、idがnullの場合全件取得
	 * @param employeeId 社員ID
	 * @return 社員情報
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public Employee[] getEmployee(Number employeeId) throws JsonMappingException, JsonProcessingException {
		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/employee";
		
		if (employeeId != null) {
			url += "?id=" + employeeId;
		}
		
		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Employee[] employeeList = mapper.readValue(json, Employee[].class);

		return employeeList;
	}
}
