package com.eeit162.FWBweb.crs.match.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.eeit162.FWBweb.crs.match.model.bean.MemberDetail;
import com.eeit162.FWBweb.crs.match.service.MemberDetailService;
import com.eeit162.FWBweb.crs.match.service.MemberService;
import com.eeit162.FWBweb.daka.login.Members;

@Controller
public class MemberDetailController {
	@Autowired
	private MemberService mService;
	@Autowired
	private MemberDetailService mdService;

	// GET+POST表單處理
	@GetMapping("/match/mdp")
	public String memberDetailPage(Model model,HttpSession session) {
		Members sessionMember = (Members) session.getAttribute("member");
		if (sessionMember == null) {
			return "redirect:/members/login";
		}

		Members md = new Members();
		model.addAttribute("memberAtt", md);
		return "match/MemberDetailRegist";
	}

	@PostMapping("/match/mdp")
	public String createMemberDetail(@ModelAttribute("memberAtt") Members members, Model model,HttpSession session) {
//		Members m = mService.findMemberById(members.getId());
		
		
		Integer id = ((Members)session.getAttribute("member")).getId();
//		Members m0 = ((Members)session.getAttribute("member"));

		
		Members m = mService.findMemberById(id);
//		System.out.println(id);
		
		if (m == null || m.getMemberDetail() == null) {
			System.out.println("null");
			// 設定外來鍵
			members.getMemberDetail().setMember(members);
			String sexualOrientation = members.getMemberDetail().getSexualOrientation();
			if("喜歡男性".equals(sexualOrientation) ) {
				members.getMemberDetail().setSexualOrientation("M");
			}else if ("喜歡女性".equals(sexualOrientation)) {
				members.getMemberDetail().setSexualOrientation("F");
			}else {
				//沒填資料預設
				if("M".equals(members.getGender())){
					members.getMemberDetail().setSexualOrientation("F");
				}
				if("F".equals(members.getGender())){
					members.getMemberDetail().setSexualOrientation("M");
				}
			}	
			mdService.insertMemberDetail(members.getMemberDetail());

			m.getMemberDetail();
			session.setAttribute("memberDT", m);
			model.addAttribute("message", "新增成功");
		} else {
			model.addAttribute("message", "新增失敗");
		}
		return "redirect:/";
	}
	
//	@GetMapping("/match/updateMdp/{id}")
//	public String getMemberDetailEditPage(@PathVariable("id") Integer id,Model model,HttpSession session) {
//		Members sessionMember = (Members) session.getAttribute("member");
//		if (sessionMember == null) {
//			return "redirect:/";
//		}
//		Members members = mService.findMemberById(id);
//		model.addAttribute("memberDetailupdateBean", members);
//		return "match/editMemberDetailPage";
//	}

//	@GetMapping("/match/MDupdate")
//	public String updateMemberDetail(@ModelAttribute("memberAtt") Members member) {
//		Members mFind = mService.findMemberById(member.getId());
//		Integer mdId = mFind.getMemberDetail().getId();
//		mFind.setMemberDetail(member.getMemberDetail());
//		mFind.getMemberDetail().setId(mdId);
//		System.out.println(mFind);
//		System.out.println(mFind.getMemberDetail());
//		mdService.updateMemberDetail(mFind.getMemberDetail());
////		mService.updateMember(mFind);
//		return "redirect:/";
//	}
	@GetMapping("/match/MDupdate")
	public String updateMemberDetail(@ModelAttribute("memberAtt") Members member,HttpSession session) {
	    Members mFind = mService.findMemberById(member.getId());
	    Integer mdId = mFind.getMemberDetail().getId();
	    MemberDetail memberDetail = mFind.getMemberDetail();
	    String sexualOrientation = member.getMemberDetail().getSexualOrientation();
	    if("喜歡男性".equals(sexualOrientation) ) {
	    	memberDetail.setSexualOrientation("M");
		}else if ("喜歡女性".equals(sexualOrientation)) {
			memberDetail.setSexualOrientation("F");
		}else {
			//沒填資料預設
			if("M".equals(member.getGender())){
				memberDetail.setSexualOrientation("F");
			}
			if("F".equals(member.getGender())){
				memberDetail.setSexualOrientation("M");
			}
		}	
//	    memberDetail.setSexualOrientation(member.getMemberDetail().getSexualOrientation());
	    memberDetail.setBodyType(member.getMemberDetail().getBodyType());
	    memberDetail.setSmoking(member.getMemberDetail().getSmoking());
	    memberDetail.setSmokingAccept(member.getMemberDetail().getSmokingAccept());
	    memberDetail.setMinAgePreference(member.getMemberDetail().getMinAgePreference());
	    memberDetail.setMaxAgePreference(member.getMemberDetail().getMaxAgePreference());
	    memberDetail.setId(mdId);
//	    System.out.println(mFind);
//	    System.out.println(memberDetail);
	    mdService.updateMemberDetail(memberDetail);

		session.setAttribute("memberDT", memberDetail);
	    return "redirect:/";
	}

	@DeleteMapping("/delete")
	public void deleteMemberDetail(Integer mid) {
		mdService.deleteMemberDetail(mid);
	}
}
