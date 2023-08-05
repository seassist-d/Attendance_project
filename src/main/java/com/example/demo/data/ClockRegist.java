package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClockRegist {
	/**
	 * 社員ID
	 */
	@JsonProperty("employee_id")
	private Number employeeId;
	
	/**
	 * 出勤時間
	 */
	@JsonProperty("clock_in")
	private String clockIn;
	
	/**
	 * 退勤時間
	 */
	@JsonProperty("clock_out")
	private String clockOut;
	
	/**
	 * 休憩開始時間
	 */
	@JsonProperty("break_start")
	private String breakStart;
	
	/**
	 * 休憩終了時間
	 */
	@JsonProperty("break_end")
	private String breakEnd;
	
	/**
	 * 
	 */
	public ClockRegist() {
		this.clockIn = "";
		this.clockOut = "";
		this.breakStart = "";
		this.breakEnd = "";
	}
}
