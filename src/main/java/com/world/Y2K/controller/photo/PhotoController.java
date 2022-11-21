package com.world.Y2K.controller.photo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.world.Y2K.exception.PhotoException;
import com.world.Y2K.model.vo.Photo;
import com.world.Y2K.service.photo.PhotoImageStore;
import com.world.Y2K.service.photo.PhotoService;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Controller
public class PhotoController {
	
	@Autowired
	private PhotoImageStore photoImageStore; 
	
	@Autowired
	private PhotoService pService;
	
	@RequestMapping("/photo.ph")
	public String photo(Model model) {
		
		List<Photo> images = pService.photoList();
		
		model.addAttribute("images", images);
		
	
		return "photo";
	}
	
	@RequestMapping("/show.ph")
	public ModelAndView selectImg(
			HttpSession session, ModelAndView mv,
			//@RequestParam("username") String username
			@RequestParam("photoNo") Long photoNo
			) {
		Photo p = pService.selectImg(photoNo);
		
		mv.addObject("photo", p);
		
		
		return mv;
	}
	
	@RequestMapping("/upload.ph")
	public String upload() {

		return "upload";
	}
	
	@RequestMapping("/image")
	public String imageUpload(@ModelAttribute Photo p,
			@RequestParam(value="file", required=false) MultipartFile file,
			HttpServletRequest request, Model model) {
		
		//System.out.println(file);
		
		if(file.isEmpty()) {
			throw new PhotoException("실패");
		}
		
		System.out.println("controller" +p.getPhotoName());
		photoImageStore.insertImage(p, file, request);
		
		return "redirect:photo.ph";
	}
	
	
	@RequestMapping("/delete.ph")
	public String deleteImage(
			@RequestParam("photoNo") Long photoNo
			) {
		
		System.out.println("result");
		
		pService.deletetImg(photoNo);
	
			return "redirect:photo.ph";
			
		}
	
	}
	
	
	
	
	
