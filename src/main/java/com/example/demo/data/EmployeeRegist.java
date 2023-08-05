package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EmployeeRegist {
	
	/**
	 * 氏名
	 */
	@JsonProperty("name")
	private String name;
	
	/**
	 * 出身地
	 */
	@JsonProperty("hometown")
	private String hometown;
	
	/**
	 * 入社日
	 */
	@JsonProperty("joining_month")
	private String joiningMonth;
}
