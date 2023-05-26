package com.eeit162.FWBweb.hc.advertising.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimePeriods {
	private final List<TimePeriod> periods;

	public TimePeriods(String periodsString) {
		// 多個不連續時段 "HH:mm~HH:mm, HH:mm~HH:mm"
		this.periods = new ArrayList<>();
		String[] periodStrings = periodsString.split(", ");
		for (String periodString : periodStrings) {
			this.periods.add(new TimePeriod(periodString));
		}
	}

	public boolean includes(LocalTime time) {
		for (TimePeriod period : periods) {
			if (period.includes(time)) {
				return true;
			}
		}
		return false;
	}
}
