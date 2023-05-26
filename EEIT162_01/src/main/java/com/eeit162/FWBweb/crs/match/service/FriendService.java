package com.eeit162.FWBweb.crs.match.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeit162.FWBweb.crs.match.model.bean.Friend;
import com.eeit162.FWBweb.crs.match.model.dao.FriendDAO;
import com.eeit162.FWBweb.daka.login.MembersDao;

@Service
public class FriendService {
	@Autowired
	private MembersDao mDAO;
	@Autowired
	private FriendDAO fDAO;
	
	public Friend findById(Integer id) {
		return fDAO.findById(id).get();
	}
	
	public List<Friend> findAllFriendList(Integer memberId) {
		return fDAO.findAllFriendList(memberId, "已確認");
	}
	
//	查自己送的邀請(未審核)
	public List<Friend> findInviteFriendByMemberId(Integer mid,String status) {
		List<Friend> fList = fDAO.findByMid1AndStatus(mid,status);
		return fList;
	}
//	查別人送過來的邀請(未審核)
	public List<Friend> findUncheckFriendByMemberId(Integer mid,String status) {
		List<Friend> fList = fDAO.findByMid2AndStatus(mid,status);
		return fList;
	}
//	可以查全部已審核or未審核
	public List<Friend> findAllFriendByMemberId(Integer mid,String status) {
		List<Friend> fList1 = fDAO.findByMid1AndStatus(mid,status);
		List<Friend> fList2 = fDAO.findByMid2AndStatus(mid,status);
		fList1.addAll(fList2);
		return fList1;
	}

    public Friend createFriend(Friend friend) {
        return fDAO.save(friend);
    }

    @Transactional
    public void updateFriend(String status, Integer mid1, Integer mid2) {
    	fDAO.updateFriend(status, mid1, mid2);
    }

    @Transactional
    public void deleteFriend(Integer mid1,Integer mid2) {
    	fDAO.deleteFriend(mid1, mid2);
    }
    
}
