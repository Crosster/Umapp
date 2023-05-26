package com.eeit162.FWBweb.crs.match.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeit162.FWBweb.crs.match.model.bean.MemberDetail;
import com.eeit162.FWBweb.crs.match.model.dao.MemberDetailDAO;

@Service
public class MemberDetailService {
	@Autowired
	private MemberDetailDAO mdDAO;
	
	public void insertMemberDetail(MemberDetail memberDetail) {
		mdDAO.save(memberDetail);
    }
	
//	@Transactional
//    public void updateMemberDetail(MemberDetail memberDetail) {
//		mdDAO.save(memberDetail);
//    }
	@Transactional
	public void updateMemberDetail(MemberDetail mD) {
		mdDAO.updateMemberDetail(mD.getSexualOrientation(), mD.getBodyType(), mD.getSmoking(), mD.getSmokingAccept(), mD.getMinAgePreference(), mD.getMaxAgePreference(), mD.getId());
	}
	@Transactional
    public void deleteMemberDetail(Integer mid) {
		mdDAO.deleteMemberDetail(mid);
    }
}
