package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Employee {

	/**
	 * 社員ID
	 */
	@JsonProperty("id")
	private Number id;
	
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
	
	/**
	 * 登録日
	 */
	@JsonProperty("created_at")
	private String createdAt;
	
	/**
	 * 更新日
	 */
	@JsonProperty("updated_at")
	private String updatedAt;
}
