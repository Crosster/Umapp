package com.eeit162.FWBweb.daka.login;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.security.auth.login.FailedLoginException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import com.eeit162.FWBweb.cheng.activity.bean.RegistBean;

@Service
public class MembersService {
	@Autowired
	private  MembersDao membersDao;
	@Autowired
	private MailService mailService;
	
	
	
	public Members login( String email ,String password) throws FailedLoginException {
		
		if(email==null ||password==null||email.trim().isEmpty()||password.trim().isEmpty()) {
			throw new FailedLoginException("帳號密碼不可為空");
		}
		Members members = membersDao.findByEmailAndPassword(email, password);
		
		if(members!=null) {
			
		
			return members;
		}else {
			throw new FailedLoginException("帳號或密碼不正確");
		}
		
	}
	
	public boolean isAccountExist(String email) {
	    Members member = membersDao.findByEmail(email);
	    return member != null;
	}
	
	
	public  String insertMember(Members members) {
		membersDao.save(members);
		
		return "success";
	}
	
	
	public List<Members> findAllMembers() {
		return membersDao.findAll();
	}


	
	public Members findMembersById( Integer id) {
		return membersDao.findById(id).get();
	}
	
	
	public Members findMemberPhotoById(Integer id) {
		Optional<Members> omem = membersDao.findById(id);
		if(omem.isEmpty()) {
			return null;
		}
		Members memberPh= omem.get();
		return  memberPh;
	}
	
	public byte[] getMemberPhotoByID2(Integer mID) {
		Optional<Members> oMem = membersDao.findById(mID);
		Members pet = oMem.orElse(new Members());
		return pet.getPhoto();
	}
	
	
	
	@Transactional
	public  Members updateMember(Integer id,String username ,String email ,byte[] photo,String gender,String address,
		Integer height,Integer weight,String job,Integer phone,Date birthday) {
		Optional<Members> option = membersDao.findById(id);
		
		if(option.isPresent()) {
			Members mem=option.get();
			mem.setUsername(username);
			mem.setEmail(email);
			mem.setGender(gender);
			mem.setPhoto(photo);
			mem.setAddress(address);
			mem.setHeight(height);
			mem.setWeight(weight);
			mem.setJob(job);
			mem.setPhone(phone);
			mem.setBirthday(birthday);
			return mem;
		}
		return null;
	}
	
	@Transactional
	public  Members updateMember2(Integer id,String username ,String email ,String gender,String address,
		Integer height,Integer weight,String job,Integer phone,Date birthday) {
		Optional<Members> option = membersDao.findById(id);
		
		if(option.isPresent()) {
			Members mem=option.get();
			mem.setUsername(username);
			mem.setEmail(email);
			mem.setGender(gender);			
			mem.setAddress(address);
			mem.setHeight(height);
			mem.setWeight(weight);
			mem.setJob(job);
			mem.setPhone(phone);
			mem.setBirthday(birthday);
			return mem;
		}
		return null;
	}
	
	@Transactional
	public  Members updateMember3(Integer id,String status) {
		Optional<Members> option = membersDao.findById(id);
		
		if(option.isPresent()) {
			Members mem=option.get();
			mem.setStatus(status);
			return mem;
		}
		return null;
	}
	
	
	public String generateVerificationCode() {
		return UUID.randomUUID().toString();
	}

	public Members getMemberByVerificationCode(String code) {
	    return membersDao.findByCode(code);
	}
	
	
	public Page<Members>findByPage(Integer members){
		PageRequest pgb=PageRequest.of(members-1,6,Sort.Direction.DESC,"id");
		Page<Members>page=membersDao.findAll(pgb);
		return page;
		
	}
	
	@Transactional
	public  Members swichLevel(Integer id,String level) {
		Optional<Members> option = membersDao.findById(id);
		
		if(option.isPresent()) {
			Members mem=option.get();
			mem.setLevel(level);
			return mem;
		}
		return null;
	}
	
}


	
