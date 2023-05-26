package com.eeit162.FWBweb.zh.util.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.eeit162.FWBweb.crs.match.model.bean.MemberDetail;
import com.eeit162.FWBweb.crs.match.service.MemberDetailService;
import com.eeit162.FWBweb.daka.login.Members;
import com.eeit162.FWBweb.daka.login.MembersService;

@Controller
public class InsertTable {
	
	@Autowired
	private MembersService membersService;
	
	@Autowired
	private MemberDetailService memberDetailService;
	
	
	@GetMapping("/insert/member/file")
	public void insertMemberFile() throws IOException, ParseException {
		
		String fileName = "C:\\Users\\Student\\Desktop\\EnvironmentFile\\FinalProject\\SQL\\members\\members.txt";
		
		String[] jobStrings = {"工程師","教師","學生","醫生","上班族"};
		
		List<String[]> strings = loadMemberTxt(fileName);
		
		
        
        for (int i = 0; i < strings.size(); i++) {
        	
        	String photoFile = "C:\\Users\\Student\\Desktop\\EnvironmentFile\\FinalProject\\SQL\\members\\photos\\";
        	
			byte[] memberPhoto = loadPhoto(strings.get(i)[1], photoFile);
        	
        	Members members = new Members();

        	members.setUsername(strings.get(i)[0]);
        	members.setGender(strings.get(i)[1]);
        	members.setBirthday(legalAge(strings.get(i)[2]));
        	members.setPhone(Integer.valueOf(strings.get(i)[3]));
        	members.setEmail(strings.get(i)[4]);
        	members.setAddress(strings.get(i)[5]);
        	members.setPassword(strings.get(i)[6]);
        	members.setPhoto(memberPhoto);
        	members.setLevel("1");
        	Integer height = Integer.valueOf((int) (Math.random()*(51))) + 150;
        	Integer weight = Integer.valueOf((int) (Math.random()*(61))) + 40;
        	members.setHeight(height);
        	members.setWeight(weight);
        	Integer jobIdx = Integer.valueOf((int) (Math.random()*(5)));
        	members.setJob(jobStrings[jobIdx]);
        	membersService.insertMember(members);
        	
		}
               
	}
	
	@GetMapping("/insert/member/detail")
	public void insertMemberDetail() {
		
		String[] bodyType = {"偏瘦","普通","微肉感"};
		String[] smoking = {"不抽","抽"};
		String[] smolingAccept = {"可接受","不可接受"};
		Integer minAge = 0;
		Integer maxAge = 0;
		
		
		
		List<Members> MemberList = membersService.findAllMembers();
		
		for (Members member : MemberList) {
			int bodyTypeId = (int) (Math.random()*3);
			int smokingId = (int) (Math.random()*2);
			int smolingAcceptId = (int) (Math.random()*2);
			
			minAge =  Integer.valueOf((int) (Math.random()*(60-18+1)+18));
			maxAge = Integer.valueOf((int) ((60-minAge+1)*Math.random() + minAge));
			if (minAge>maxAge) {
				minAge = maxAge;
			}
//			member.setMemberDetail(new MemberDetail());
//			member.getMemberDetail().setMember(member);
			
			MemberDetail memberDetail = new MemberDetail();
			
			memberDetail.setMember(member);
//			member.getMemberDetail().setId(member.getId());
//			member.getMemberDetail().setBodyType(bodyType[bodyTypeId]);
//			member.getMemberDetail().setMaxAgePreference(maxAge);
//			member.getMemberDetail().setMinAgePreference(minAge);
//			member.getMemberDetail().setSmoking(smoking[smokingId]);
//			member.getMemberDetail().setSmokingAccept(smolingAccept[smolingAcceptId]);
			memberDetail.setId(member.getId());
			memberDetail.setBodyType(bodyType[bodyTypeId]);
			memberDetail.setEducationLevel("");
			memberDetail.setMaxAgePreference(maxAge);
			memberDetail.setMinAgePreference(minAge);
			memberDetail.setSmoking(smoking[smokingId]);
			memberDetail.setSmokingAccept(smolingAccept[smolingAcceptId]);
			if ("M".equals(member.getGender())) {
//				member.getMemberDetail().setSexualOrientation("F");
				memberDetail.setSexualOrientation("F");
			}else if ("F".equals(member.getGender())) {
//				member.getMemberDetail().setSexualOrientation("M");
				memberDetail.setSexualOrientation("M");
			}
			
			System.out.println(memberDetail.toString());
			memberDetailService.insertMemberDetail(memberDetail);
		}
		
	}
	
	public Date legalAge(String birthday) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		Date birth = simpleDateFormat.parse(birthday);
		int birthYear = birth.getYear()+1900;
		
		if ((2023-birthYear)<18) {
			int randYear = (int) (Math.random()*20);
			birth.setYear(birthYear-randYear-1900);
		}		
		return birth;
	}
	
	public List<String[]> loadMemberTxt(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String st;
		List<String[]> strings = new ArrayList<String[]>();
		while ((st = br.readLine()) != null) {
        	strings.add(st.split(","));
	    }
        br.close();
        return strings;
		
	}
	
	public byte[] loadPhoto(String gender,String fileName) throws IOException {
		
		int photoId = 0;
		
		photoId = (int) (Math.random()*50);
		String pathString = fileName+gender+"\\"+gender+"_"+photoId+".png";
		BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(ResourceUtils.getFile(pathString)));
		byte[] photoByte = bis.readAllBytes();
		bis.close();
		
		return photoByte;
		
	}
}
