package com.eeit162.FWBweb.daka.member.model.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eeit162.FWBweb.daka.login.Members;
import com.eeit162.FWBweb.daka.login.MembersService;
import com.eeit162.FWBweb.daka.member.model.Service.PhotosService;
import com.eeit162.FWBweb.daka.member.model.beam.Likes;
import com.eeit162.FWBweb.daka.member.model.beam.Photos;

@Controller
public class PhotosController {
	@Autowired
	private MembersService membersService;

	@Autowired
	private PhotosService photosService;

	//
	@GetMapping("members/photowall")
	public String photocrud(@RequestParam(required = false) Integer id, Model model, HttpSession session) {
		Members members = null;

		Members sessionMember = (Members) session.getAttribute("member");
		if (sessionMember != null) {
			members = membersService.findMembersById(sessionMember.getId());
		}
		if (members == null) {
			return "redirect:/members/login";
		}
		model.addAttribute("members", members);
		return "login/addphoto";
	}

	// 新增圖片
	@PostMapping(value = "members/photowall", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String addPhoto(@ModelAttribute Photos p, @RequestParam(required = false) MultipartFile pictureContent,
			HttpSession session, Model model) throws IOException {
		Members sessionMember = (Members) session.getAttribute("member");
		Members members = null;
		if (sessionMember != null) {
			members = membersService.findMembersById(sessionMember.getId());
		}
		model.addAttribute("members", members);
		Page<Photos> photospage = photosService.findPhotocount(sessionMember.getId(), 1);
		model.addAttribute("page", photospage);
		if (pictureContent != null && !pictureContent.isEmpty()) {

			try (BufferedInputStream bis = new BufferedInputStream(pictureContent.getInputStream())) {
				p.setPicture(bis.readAllBytes());

			}
		} else {

			try (InputStream is = getClass().getResourceAsStream("/no_image.png");
					BufferedInputStream bis = new BufferedInputStream(is)) {
				p.setPicture(bis.readAllBytes());
			}

		}

		p.setMembers(sessionMember);
		photosService.addPhoto(p);

		return "redirect:/members/photoshow";
	}

	// 以搜尋mId進行分頁
	@GetMapping("members/photoshow")
	public String showphoto(@RequestParam(name = "show", defaultValue = "1") Integer page, Model model,
			HttpSession session) {
		Members members = null;

		Members sessionMember = (Members) session.getAttribute("member");

		if (sessionMember != null) {

			members = membersService.findMembersById(sessionMember.getId());
		}
		if (members == null) {
			return "redirect:/members/login";
		}
		Integer bb = sessionMember.getId();
		model.addAttribute("members", members);

		Page<Photos> photospage = photosService.findPhotocount(bb, page);
//		List<Photos> photos = photospage.getContent();

		model.addAttribute("page", photospage);
		return "login/allphoto";

	}

	// 測試程式
	// 以搜尋pId進行分頁
	@GetMapping("members/allphotoshow")
	public String allphotoshow(@RequestParam(name = "show", defaultValue = "1") Integer page, Model model,
			HttpSession session) {
		Members members = null;

		Members sessionMember = (Members) session.getAttribute("member");
		if (sessionMember != null) {
			members = membersService.findMembersById(sessionMember.getId());
		}
		if (members == null) {
			return "redirect:/members/login";
		}
		model.addAttribute("members", members);
		

		Page<Photos> photospage = photosService.findByPage(page);
//		List<Photos> photos = photospage.getContent();
		model.addAttribute("page", photospage);

//		for (Photos photo : photospage.getContent()) {
//	        String likeStatus = photosService.checkIfLiked(sessionMember.getId(), photo.getPid());
//	        photo.setLikeStatus(likeStatus);
//	    }

		return "login/allmemberphoto";
	}

	// 顯示圖片1
	@GetMapping(value = "/getPicture", produces = "image/*")
	@ResponseBody
	public byte[] getPetPhotoByPID(@RequestParam(defaultValue = "0") Integer pID) throws IOException {

		byte[] petPhoto = photosService.getPetPhotoByID(pID);

		if (petPhoto == null) {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(ResourceUtils.getFile("classpath:\\no_image.png")));
			petPhoto = bis.readAllBytes();
			bis.close();
		}

		return petPhoto;
	}

	// 顯示圖片2
	@GetMapping("/showpictur/{pid}")
	public ResponseEntity<byte[]> showphoto(@PathVariable Integer pid) {
		Photos photo = photosService.findPictureById(pid);
		byte[] photoFile = photo.getPicture();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);

		return new ResponseEntity<byte[]>(photoFile, headers, HttpStatus.OK);

	}

	// 跳頁到圖片編輯
	@GetMapping("/members/uppicture/{nowphPage}")
	public String uppicture(@RequestParam("pid") Integer pid, Model model,
			@PathVariable("nowphPage") Integer nowphPage) {
		Photos upp = photosService.findById(pid);
		model.addAttribute("Photos", upp);
		model.addAttribute("nowphPage", nowphPage);
		return "login/uppicture";

	}

	@GetMapping("/members/uppicturemyself/{nowphPage}")
	public String uppictureMyself(@RequestParam("pid") Integer pid, Model model,
			@PathVariable("nowphPage") Integer nowphPage) {
		Photos upp = photosService.findById(pid);
		model.addAttribute("Photos", upp);
		model.addAttribute("nowphPage", nowphPage);
		return "login/uppicture";

	}

	// 自己圖片編輯
	@PostMapping(value = "/members/uppicturemyself/{nowphPage}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String uppictureMyself(@ModelAttribute("Photos") Photos phts,
			@RequestParam(name = "pictureContent", required = false) MultipartFile pictureContent, HttpSession session,
			@RequestParam("nowphPage") Integer nowphPage) throws IOException {

		if (pictureContent != null && !pictureContent.isEmpty()) {
			photosService.updatePhotos(phts.getPid(), phts.getTitle(), phts.getText(), pictureContent.getBytes());

			try {
				BufferedInputStream bis = new BufferedInputStream(pictureContent.getInputStream());
				phts.setPicture(bis.readAllBytes());
				bis.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		photosService.updatePhotos2(phts.getPid(), phts.getTitle(), phts.getText());

		phts = photosService.getPhotos(phts.getPid());
		session.setAttribute("photo", phts);

		return "redirect:/members/photoshow?show=" + nowphPage;
	}

	// 圖片編輯
	@PostMapping(value = "/members/uppicture/{nowphPage}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String trueuppicture(@ModelAttribute("Photos") Photos phts,
			@RequestParam(name = "pictureContent", required = false) MultipartFile pictureContent, HttpSession session,
			@RequestParam("nowphPage") Integer nowphPage) throws IOException {

//		System.out.println(pictureContent);

//		MultipartFile pictureContent=phts.getPictureContent();

		if (pictureContent != null && !pictureContent.isEmpty()) {
			photosService.updatePhotos(phts.getPid(), phts.getTitle(), phts.getText(), pictureContent.getBytes());

			try {
				BufferedInputStream bis = new BufferedInputStream(pictureContent.getInputStream());
				phts.setPicture(bis.readAllBytes());
				bis.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		photosService.updatePhotos2(phts.getPid(), phts.getTitle(), phts.getText());

		phts = photosService.getPhotos(phts.getPid());
		session.setAttribute("photo", phts);
		return "redirect:/members/allphotoshow?show=" + nowphPage;

	}
	
	
	
	// 自己個人刪除貼文
	@DeleteMapping("/members/myselfdelete/{nowphPage}")
	public String deletePhotosMyself(@RequestParam("pid") Integer pid, @PathVariable("nowphPage") Integer nowphPage) {
		photosService.deleteById(pid);
		System.out.println("// 自己個人刪除貼文");
		return "redirect:/members/photoshow?show=" + nowphPage;
	}
	

	// 刪除貼文
	@DeleteMapping("/members/delete/{nowphPage}")
	public String deletePhotos(@RequestParam("pid") Integer pid, @PathVariable("nowphPage") Integer nowphPage) {
		photosService.deleteById(pid);
		System.out.println("// 刪除貼文");
		return "redirect:/members/allphotoshow?show=" + nowphPage;
	}
	

	// 進行點讚
	@GetMapping(value = "likePhotos")
	@ResponseBody
	public String likePhotos(@RequestParam Integer pid, HttpSession session) {
		Members mem = (Members) session.getAttribute("member");
		String stat = photosService.likePhotos(mem.getId(), pid);

		return stat;
	}

	@GetMapping("/loginPhoto/search")
	@ResponseBody
	public String searchLikes(@RequestParam("pid") Integer pid, @RequestParam("mid") Integer mid, HttpSession session) {
	    Members mem = (Members) session.getAttribute("member");
	    List<Likes> likeList = photosService.serchlikes(pid, mid);
	    String response = "LikeNotFound"; // 默认为未点赞状态
	    
	    // 检查当前用户是否已点赞
	    if (mem != null && likeList != null && !likeList.isEmpty()) {
	        for (Likes like : likeList) {
	            if (like.getMembers().getId().equals(mem.getId())) {
	                response = "LikeExist"; // 用户已点赞
	                break;
	            }
	        }
	    }

	    return response;
	}

}
