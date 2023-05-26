package com.eeit162.FWBweb.crs.match.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eeit162.FWBweb.crs.match.model.bean.Friend;
import com.eeit162.FWBweb.crs.match.service.FriendService;
import com.eeit162.FWBweb.crs.match.service.MemberService;
import com.eeit162.FWBweb.daka.login.Members;

@RestController
//@RequestMapping("/friendapi")
public class FriendContorller {
	@Autowired
	private MemberService mService;
	@Autowired
	private FriendService fService;

	@GetMapping("/friend/get/{mid}/{status}")
	public List<Friend> getFriendList(@PathVariable("mid") Integer mid, @PathVariable("status") String status) {
		List<Friend> fList = new ArrayList<>();
		System.out.println(status);
		if ("checked".equals(status)) {
			fList = fService.findAllFriendByMemberId(mid, "已確認");
		}
		if ("invite".equals(status)) {
			fList = fService.findInviteFriendByMemberId(mid, "未審核");
		}
		if ("uncheck".equals(status)) {
			fList = fService.findUncheckFriendByMemberId(mid, "未審核");
		}
		System.out.println(fList);
		return fList;
	}

	@GetMapping("/friend/add/{mid1}/{mid2}")
	public ResponseEntity<String> makeFriendWithStatus(@PathVariable("mid1") Integer mid1,
			@PathVariable("mid2") Integer mid2) {
		Friend friend = new Friend();
		Members member1 = mService.findMemberById(mid1);
		Members member2 = mService.findMemberById(mid2);
		// 自己不能加自己好友
		if (member1.getId().equals(member2.getId())) {
			return new ResponseEntity<String>("friend add fail", HttpStatus.UNAUTHORIZED);
		}
		// 同一個好友不能邀請兩次
//		if(fService.findInviteFriendByMemberId(mid2, null))

		friend.setMember1(member1);
		friend.setMember2(member2);
		friend.setStatus("未審核");
		fService.createFriend(friend);
		return new ResponseEntity<String>("friend add susccess", HttpStatus.OK);
	}

	// 還沒寫登入身分判斷
	@PutMapping("/friend/update/{friendId}/{mid}")
	public String updateFriend(@PathVariable("friendId") Integer friendId, @PathVariable("mid") Integer mid) {
		if (friendId != null ) {
			fService.updateFriend("已確認", friendId, mid);
			return "ok";
		}
		return "update fail";
	}

	// 還沒寫登入身分判斷
	@DeleteMapping("/friend/delete/{mid1}/{mid2}")
	public void deleteFriend(@PathVariable("mid1") Integer mid1, @PathVariable("mid2") Integer mid2) {
		fService.deleteFriend(mid1, mid2);

	}
}
