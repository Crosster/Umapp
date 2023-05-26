package com.eeit162.FWBweb.crs.match.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eeit162.FWBweb.crs.match.model.bean.MemberDetail;
import com.eeit162.FWBweb.crs.match.model.bean.MemberMatchDTO;
import com.eeit162.FWBweb.crs.match.service.MemberService;
import com.eeit162.FWBweb.crs.match.util.MatchUtil;
import com.eeit162.FWBweb.daka.login.Members;

@Controller
public class MatchController {
	@Autowired
	private MemberService mService;

	@GetMapping("/match/dphoto/{id}")
	public ResponseEntity<byte[]> MatchGetPhoto(@PathVariable("id") Integer id) {
		Members m = mService.findMemberById(id);
		if (m == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		byte[] photo = m.getPhoto();
		return new ResponseEntity<byte[]>(photo, headers, HttpStatus.OK);
	}

	// 沒登入隨機6個
	@GetMapping("/randomResult")
	@ResponseBody
	public PagedListHolder<Members> randomResult(Model model) {
		Integer page = 1;
		Integer count = 8;
		// 得到全部，然後List<Members>洗牌
		List<Members> mList = mService.memberList();
		Collections.shuffle(mList);

		PagedListHolder<Members> plh = new PagedListHolder<Members>(mList);

		plh.setPage(page - 1);
		plh.setPageSize(count);

		model.addAttribute("quickMatchPage", plh);
		System.out.println("randomResult done!");
		return plh;
	}

	// 一般會員
	@GetMapping("/normalUserResult")
	@ResponseBody
	public PagedListHolder<MemberMatchDTO> normalUserResult(HttpSession session) {
		Integer page = 1;
		Integer count = 8;
//		Integer mid=((Members) session.getAttribute("member")).getId();
//		System.out.println(mid);
		Members m1 = (Members) session.getAttribute("member");
		String m1SexOri = null;
//		if (m1.getMemberDetail() != null) {
//			m1SexOri = m1.getMemberDetail().getSexualOrientation();
//		}
		m1 = mService.findMemberById(m1.getId());
        m1SexOri = m1.getMemberDetail().getSexualOrientation();
		// 得到性向符合的對象List，然後List洗牌
		List<Members> mList = mService.normalUserList(m1SexOri, m1.getGender());
		Collections.shuffle(mList);
		MatchUtil matchUtil = new MatchUtil();

		// 加入評分
		List<MemberMatchDTO> dtomList = new ArrayList<>();
		for (Members m2 : mList) {
			int matchRate = matchUtil.calculateMatchRate(m1, m2);
			dtomList.add(new MemberMatchDTO(m2, matchRate)); // 將 Members 對象和 matchRate 封裝到 DTO 中，並添加到新的列表中
		}

		PagedListHolder<MemberMatchDTO> plh = new PagedListHolder<MemberMatchDTO>(dtomList);

		plh.setPage(page - 1);
		plh.setPageSize(count);

		System.out.println("normalUserResult done!");
		return plh;
	}

	// 黃金會員
	@GetMapping("/goldenUserResult/{goldscore}")
	@ResponseBody
	public PagedListHolder<MemberMatchDTO> goldenUserResult(@PathVariable("goldscore") Integer goldscore,HttpSession session) {
		Integer page = 1;
		Integer count = 8;
		Members m1 = (Members) session.getAttribute("member");
		
		String m1SexOri = null;
//		if (m1.getMemberDetail() != null) {
//			m1SexOri = m1.getMemberDetail().getSexualOrientation();
//		}
		m1 = mService.findMemberById(m1.getId());
        m1SexOri = m1.getMemberDetail().getSexualOrientation();
		// 得到性向符合的對象List，然後比對List每一個分數得到超過70分對象，最後List洗牌
		List<Members> mList = mService.normalUserList(m1SexOri, m1.getGender());

		MatchUtil matchUtil = new MatchUtil();
		List<MemberMatchDTO> dtoList = new ArrayList<>();
		for (Members m2 : mList) {
			int matchRate = matchUtil.calculateMatchRate(m1, m2);
			dtoList.add(new MemberMatchDTO(m2, matchRate)); // 將 Members 對象和 matchRate 封裝到 DTO 中，並添加到新的列表中
		}

		List<MemberMatchDTO> sortedAndFilteredDTOList = dtoList.stream().filter(dto -> dto.getMatchRate() >= goldscore) // 過濾出配對分數超過70分的會員
				.sorted(Comparator.comparingInt(MemberMatchDTO::getMatchRate).reversed()) // 對這些會員按照配對分數進行排序
				.collect(Collectors.toList()); // 收集到新的列表中

		Collections.shuffle(sortedAndFilteredDTOList);

//	    List<Members> mListSort = new ArrayList<>();
//	    for(MemberMatchDTO m3 :sortedAndFilteredDTOList) {
//	        mListSort.add(m3.getMember());
//	    }
		PagedListHolder<MemberMatchDTO> plh = new PagedListHolder<MemberMatchDTO>(sortedAndFilteredDTOList);

		plh.setPage(page - 1);
		plh.setPageSize(count);

		System.out.println("goldenUserResult done!");
		return plh;
	}

	// 獲得配對率
	@GetMapping("/match/rating/{mid1}/{mid2}")
	public ResponseEntity<Integer> getMatchrating(@PathVariable("mid1") Integer mid1,
			@PathVariable("mid2") Integer mid2) {
		Members m1 = mService.findMemberById(mid1);
		Members m2 = mService.findMemberById(mid2);
		if (m1 == null || m2 == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		System.out.println("我方m1");
		System.out.println(m1);
		System.out.println("對方m2");
		System.out.println(m2);
		MatchUtil matchUtil = new MatchUtil();
		int matchRate = matchUtil.calculateMatchRate(m1, m2);

		return new ResponseEntity<>(matchRate, HttpStatus.OK);
	}

}
