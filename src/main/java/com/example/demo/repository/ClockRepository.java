package com.example.demo.repository;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.data.Clock;
import com.example.demo.data.ClockRegist;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 時計テーブルリポジトリ
 *
 */
@Repository
public class ClockRepository {
	/**
	 * idが一致する社員の出退勤情報取得、idがnullの場合全件取得
	 * @param employeeId
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public Clock[] getClock(Number employeeId) throws JsonMappingException, JsonProcessingException {
		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/clock";

		if (employeeId != null) {
			url += "?employeeId=" + employeeId;
		}

		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Clock[] clockList = mapper.readValue(json, Clock[].class);

		return clockList;
	}

	/**
	 * 勤怠登録
	 * @param clock 勤怠情報
	 */
	public void registerClock(ClockRegist clock) {
		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/clock";

		String body = "{\"body\":\"{"
				+ "\\\"employee_id\\\":\\\"" + clock.getEmployeeId() + "\\\","
				+ "\\\"clock_in\\\":\\\"" + clock.getClockIn() + "\\\","
				+ "\\\"break_start\\\":\\\"" + clock.getBreakStart() + "\\\","
				+ "\\\"break_end\\\":\\\"" + clock.getBreakEnd() + "\\\","
				+ "\\\"clock_out\\\":\\\"" + clock.getClockOut() + "\\\""
				+ "}\"}";

		RequestEntity<String> request = RequestEntity.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.body(body);

		RestTemplate rest = new RestTemplate();
		ResponseEntity<String> response = rest.exchange(request, String.class);
	}
}
