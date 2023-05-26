package com.eeit162.FWBweb.crs.match.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import com.eeit162.FWBweb.daka.login.Members;

public class MatchUtil {
	/**
	 * 
	 * @param m1 使用者
	 * @param m2 使用者配對的對象
	 * @return 配對分數
	 */
	public int calculateMatchRate(Members m1, Members m2) {

		int score = 0;
		// 比較居住地 加10分
		if (m1.getAddress() != null && m1.getAddress().equals(m2.getAddress())) {
			score += 10;
		}
		// 對方是高級會員 加10分
		if (m2.getLevel() != null && m2.getLevel() == "2") {
			score += 10;
		}
		// 是否5分鐘內有上線 加10分
		Date now = new Date();

		if (m2.getLastLogindAt() != null && now.getTime() - m2.getLastLogindAt().getTime() <= 300000) {
			score += 10;
		}
//		System.out.println("計算我方配對傾向");
//		System.out.println("目前分數：" + score);
		// 我方的配對傾向50%
		if (m1.getMemberDetail() != null) {
			// 性向(基本)
			if (!m1.getMemberDetail().getSexualOrientation().equals(m2.getGender())) {
				return 0;
			}
			// 抽菸(20)
			if (m1.getMemberDetail().getSmokingAccept() != null && !m1.getMemberDetail().getSmokingAccept().isEmpty()
					&& m2.getMemberDetail().getSmoking() != null && !m2.getMemberDetail().getSmoking().isEmpty()) {
				if ("可接受".equals(m1.getMemberDetail().getSmokingAccept())) {
					score += 20;
				} else if ("不可接受".equals(m1.getMemberDetail().getSmokingAccept())
						&& "抽".equals(m2.getMemberDetail().getSmoking())) {
					score -= 20;
				}
			}
			// 年齡(10)
			if (m2.getBirthday() != null && m1.getMemberDetail().getMaxAgePreference() != null
					&& m1.getMemberDetail().getMinAgePreference() != null) {
				// 將 java.util.Date 轉換為 java.time.LocalDate
				LocalDate m2BirthDate = Instant.ofEpochMilli(m2.getBirthday().getTime()).atZone(ZoneId.systemDefault())
						.toLocalDate();
				// 計算年齡
				int m2age = Period.between(m2BirthDate, LocalDate.now()).getYears();
//				System.out.println("m2年齡: " + m2age);
				if (m1.getMemberDetail().getMaxAgePreference() >= m2age
						&& m1.getMemberDetail().getMinAgePreference() <= m2age) {
					score += 10;
				}
			}
			// 身高(10)

			// BMI值(10)
			if (m1.getHeight() != null && m1.getWeight() != null && m1.getMemberDetail().getBodyType() != null
					&& !m1.getMemberDetail().getBodyType().isEmpty()) {
				Integer m2h = m2.getHeight();
				Integer m2w = m2.getWeight();
				Integer m2bmi = m2h / (m2w * m2w);
				if (m2bmi >= 24 && "微肉感".equals(m1.getMemberDetail().getBodyType())) {
					score += 10;
				} else if (18.5 < m2bmi && m2bmi < 24 && "普通".equals(m1.getMemberDetail().getBodyType())) {
					score += 10;
				} else if (m2bmi <= 18.5 && "偏瘦".equals(m1.getMemberDetail().getBodyType())) {
					score += 10;
				}
			}
		}
		// 對方的配對傾向40%
		if (m2.getMemberDetail() != null) {

//			System.out.println("計算對方配對傾向");
//			System.out.println("目前分數：" + score);
			// 性向(基本)
			if (!m2.getMemberDetail().getSexualOrientation().equals(m1.getGender())) {
				return 0;
			}
			// 抽菸(10)
			if (m2.getMemberDetail().getSmokingAccept() != null && !m2.getMemberDetail().getSmokingAccept().isEmpty()
					&& m1.getMemberDetail().getSmoking() != null && !m1.getMemberDetail().getSmoking().isEmpty()) {
				if ("可接受".equals(m2.getMemberDetail().getSmokingAccept())) {
					score += 20;
				} else if ("不可接受".equals(m2.getMemberDetail().getSmokingAccept())
						&& "抽".equals(m1.getMemberDetail().getSmoking())) {
					score -= 20;
				}
			}
			// 年齡(10)
			if (m1.getBirthday() != null && m2.getMemberDetail().getMaxAgePreference() != null
					&& m2.getMemberDetail().getMinAgePreference() != null) {
				LocalDate m1BirthDate = Instant.ofEpochMilli(m1.getBirthday().getTime()).atZone(ZoneId.systemDefault())
						.toLocalDate();
				// 計算年齡
				int m1age = Period.between(m1BirthDate, LocalDate.now()).getYears();
//				System.out.println("m1年齡: " + m1age);
				if (m2.getMemberDetail().getMaxAgePreference() >= m1age
						&& m2.getMemberDetail().getMinAgePreference() <= m1age) {
					score += 10;
				}
			}
			// 身高(10)
			// BMI值(10)
			if (m1.getHeight() != null && m1.getWeight() != null && m2.getMemberDetail().getBodyType() != null
					&& !m2.getMemberDetail().getBodyType().isEmpty()) {
				Integer m1h = m1.getHeight();
				Integer m1w = m1.getWeight();
				Integer m1bmi = m1h / (m1w * m1w);
				if (m1bmi >= 24 && "微肉感".equals(m2.getMemberDetail().getBodyType())) {
					score += 10;
				} else if (18.5 < m1bmi && m1bmi < 24 && "普通".equals(m2.getMemberDetail().getBodyType())) {
					score += 10;
				} else if (m1bmi <= 18.5 && "偏瘦".equals(m2.getMemberDetail().getBodyType())) {
					score += 10;
				}
			}
		}

		// 超過100變為100
		if (score > 100) {
			score = 100;
		}

		return score;
	}
}
