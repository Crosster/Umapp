package com.eeit162.FWBweb.daka.member.model.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eeit162.FWBweb.daka.login.Members;
import com.eeit162.FWBweb.daka.login.MembersDao;
import com.eeit162.FWBweb.daka.member.model.beam.Likes;
import com.eeit162.FWBweb.daka.member.model.beam.Photos;
import com.eeit162.FWBweb.daka.member.model.dao.LikesDao;
import com.eeit162.FWBweb.daka.member.model.dao.PhotosDao;


@Service
public class PhotosService {
	@Autowired
	private PhotosDao photosDao;
	@Autowired
	private MembersDao membersDao;
	@Autowired
	private LikesDao likesDao;
	
	public String addPhoto(Photos p ) {
		photosDao.save(p);
		return "success";
	}
	//搜尋pId進行分頁
	public Page<Photos> findByPage(Integer photopage) {
		
		PageRequest pgb = PageRequest.of(photopage-1,8,Sort.Direction.DESC,"pid");
		Page<Photos> page =photosDao.findAll(pgb);
		
		return page;
	}
	//搜尋mId進行分頁
	public Page<Photos> findPhotocount(Integer mId,Integer photopage) {
		PageRequest pgb = PageRequest.of(photopage-1,8,Sort.Direction.DESC,"mId");
		Page<Photos> page =photosDao.findPhotoByMemberId(mId,pgb);
		return page;
	}
	
	
	
	public byte[] getPetPhotoByID(Integer pid) {
		Optional<Photos> oPto =photosDao.findById(pid);
		Photos pet = oPto.orElse(new Photos());
		return pet.getPicture();
	}
	
	public Photos findPictureById(Integer pid) {
		Optional<Photos> ophto = photosDao.findById(pid);
		if(ophto.isEmpty()) {
			return null;
		}
		Photos photos= ophto.get();
		return  photos;
	}
	
	public Photos findById(Integer pid) {
		Optional<Photos> ph = photosDao.findById(pid);
		if (ph.isPresent()) {
			return ph.get();
		}
		return null;
	}
	@Transactional
	public Photos updatePhotos(Integer pid, String title,String text ,byte[] pictuer) {
		Optional<Photos> optin = photosDao.findById(pid);
		if(optin.isPresent()) {
			Photos phts = optin.get();
			phts.setTitle(title);
			phts.setText(text);
			phts.setPicture(pictuer);
			return phts;
		}
		return null;
	}
	
	@Transactional
	public Photos updatePhotos2(Integer pid, String title,String text) {
		Optional<Photos> optin = photosDao.findById(pid);
		if(optin.isPresent()) {
			Photos phts = optin.get();
			phts.setTitle(title);
			phts.setText(text);
			
			return phts;
		}
		return null;
	}

	
	public void deleteById(Integer pid) {
		photosDao.deleteById(pid);
		
	}

	public Photos getPhotos(Integer pid) {
		
		return null;
	}
	//點讚
	public String likePhotos(Integer id,Integer pid) {
		Photos ph=photosDao.findById(pid).get();
		Members mem=membersDao.findById(id).get();
		for(Likes li:ph.getLikes() ) {
			if (li.getMembers().getId()==id&&li.getPhotos().getPid()==pid) {
				ph.getLikes().remove(li);
				mem.getLikes().remove(li);
				
				likesDao.deleteById(li.getLid());
				return "unLiked";
			}
		}
		Likes newliLikes=new Likes(new Date(),mem,ph);
				likesDao.save(newliLikes);
		return "liked";
	}
	
	
	
//	public String checkIfLiked(Integer id, Integer pId) {
//		Members members =membersDao.findById(id).orElse(null);
//		Photos photos= photosDao.findById(pId).orElse(null);
//		 if (members != null && photos != null) {
//		        for (Likes like : members.getLikes()) {
//		            if (like.getPhotos().equals(photos)) {
//		                return "liked";
//		            }
//		        }
//		    }
//		 return "unLiked";
//	}

	public List<Likes> serchlikes(Integer pid, Integer mid) {
		return likesDao.findLikeByPidAndMid(pid, mid);
	}
	
	

}
