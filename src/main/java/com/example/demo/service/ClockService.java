package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.Clock;
import com.example.demo.data.ClockRegist;
import com.example.demo.repository.ClockRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 勤怠情報 Service
 *
 */
@Service
public class ClockService {
	/**
	 * 勤怠情報 Repository
	 */
	@Autowired
	private ClockRepository clockRepository;

	/**
	 * デフォルトコンストラクタ
	 */
	public ClockService() {

	}

	/**
	 * コンストラクタ
	 * @param clockRepository
	 */
	public ClockService(ClockRepository clockRepository) {
		this.clockRepository = clockRepository;
	}

	/**
	 * idが一致する社員の出退勤情報取得、idがnullの場合全件取得
	 * @param employeeId 社員ID
	 * @return 勤怠情報
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public List<Clock> getClock(Number employeeId) throws JsonMappingException, JsonProcessingException {
		Clock[] clockList = clockRepository.getClock(employeeId);

		return Arrays.asList(clockList);
	}

	/**
	 * 勤怠情報登録
	 * @param button
	 * @param employeeId
	 */
	public void registerClock(String button, Number employeeId) {
		ClockRegist clock = new ClockRegist();
		
		clock.setEmployeeId(employeeId);

		DateTimeFormatter dataTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		String date = dataTimeFormat.format(LocalDateTime.now());

		if ("出勤".equals(button)) {
			clock.setClockIn(date);
		}
		
		if ("休憩開始".equals(button)) {
			clock.setBreakStart(date);
		}
		
		if ("休憩終了".equals(button)) {
			clock.setBreakEnd(date);
		}
		
		if ("退勤".equals(button)) {
			clock.setClockOut(date);
		}
		
		clockRepository.registerClock(clock);
	}
}
