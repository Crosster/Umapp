package com.eeit162.FWBweb.hc.advertising.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimePeriod {
	private final LocalTime start;
	private final LocalTime end;

	public TimePeriod(String periodString) {
		// 時間字串的格式為 "HH:mm~HH:mm"
		String[] parts = periodString.split("~");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		this.start = LocalTime.parse(parts[0], formatter);
		this.end = LocalTime.parse(parts[1], formatter);
	}

	public boolean includes(LocalTime time) {
		// 在判斷時須考慮是否跨日
		if (start.isBefore(end)) {
			// 時段不跨日
			return !time.isBefore(start) && !time.isAfter(end);
		} else {
			// 時段跨日，例如 "20:00~00:00"
			return !time.isBefore(start) || !time.isAfter(end);
		}
	}
}
