package com.eeit162.FWBweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eeit162.FWBweb.crs.match.service.MemberService;
import com.eeit162.FWBweb.crs.match.util.MatchUtil;
import com.eeit162.FWBweb.daka.login.Members;

@SpringBootTest
class FwBwebApplicationTests {
	@Autowired
	private MemberService mService;

	@Test
	void contextLoads() {
		Members m1 = mService.findMemberById(1);
		Members m2 = mService.findMemberById(3);
		MatchUtil matchUtil = new MatchUtil();
		int calculateMatchRate = matchUtil.calculateMatchRate(m1, m2);
		System.out.println(calculateMatchRate);
	}
	

}
