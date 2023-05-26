package com.eeit162.FWBweb.crs.match.model.bean;

import com.eeit162.FWBweb.daka.login.Members;

public class MemberMatchDTO {
	private Members member;
    private int matchRate;

    public MemberMatchDTO(Members member, int matchRate) {
        this.member = member;
        this.matchRate = matchRate;
    }

    public Members getMember() {
        return member;
    }

    public void setMember(Members member) {
        this.member = member;
    }

    public int getMatchRate() {
        return matchRate;
    }

    public void setMatchRate(int matchRate) {
        this.matchRate = matchRate;
    }
}
