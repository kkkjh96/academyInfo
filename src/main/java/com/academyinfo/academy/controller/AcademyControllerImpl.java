package com.academyinfo.academy.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.academyinfo.Class.dto.ClassResponseDto;
import com.academyinfo.academy.dto.AcademyRequestDto;
import com.academyinfo.academy.dto.AcademyResponseDto;
import com.academyinfo.academy.service.AcademyService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/academy")
@Controller
public class AcademyControllerImpl implements AcademyController {
	
	AcademyService academyService;
	
	@GetMapping("/add")
	public String add(Model model) {
		return "/academy/add";
	}
	
	@PostMapping("/post")
	public String add(AcademyRequestDto academyRequestDto) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 현재 로그인한 사용자의 정보를 가져온다
		String id = ((UserDetails)principal).getUsername(); // 사용자의 Id
		
		academyService.savePost(id, academyRequestDto);
		
		return "redirect:/user/cpnClassList";
	}
	
	@GetMapping("/post/{no}")
	public String detail(@PathVariable("no") int no, Model model) {
        AcademyResponseDto academyDTO = academyService.getPost(no);
        List<ClassResponseDto> classes = academyDTO.getCindex();
        
        boolean isLogin = false;
        boolean isWriter = false;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 현재 로그인한 사용자의 정보를 가져온다
        
        // 로그인 상태 체크
        if (!principal.toString().equals("anonymousUser")) {
        	String id = ((UserDetails)principal).getUsername(); // 사용자의 Id
        	
        	isLogin = true;
        	model.addAttribute("loginUser", id);
        	
        	// 게시글 작성자 본인확인
        	if (academyDTO.getMid().equals(id)) {
        		isWriter = true;
        	} 
        }
        
        model.addAttribute("isLogin", isLogin);
        model.addAttribute("isWriter", isWriter);
        
        // 학원 내 강의 관련 기능
        if (classes != null && !classes.isEmpty()) {
        	model.addAttribute("classes", classes);
        	model.addAttribute("classesCnt", classes.size());
        } else {
        	model.addAttribute("classesCnt", 0);
        }
        
        model.addAttribute("academyDto", academyDTO);		
		
		return "/academy/detail";
	}
	
	@GetMapping("/post/edit/{no}")
	public String edit(@PathVariable("no") int no, Model model) {
		AcademyResponseDto academyDTO = academyService.getPost(no);
		
		model.addAttribute("academyDto", academyDTO);
		
		return "/academy/update";
	}
	
	@PutMapping("/post/edit/{no}")
	public String update(AcademyRequestDto academyDTO) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 현재 로그인한 사용자의 정보를 가져온다
		String id = ((UserDetails)principal).getUsername(); // 사용자의 Id
	  	
	    academyService.savePost(id, academyDTO);
		
		return "redirect:/academy/post/{no}";
	}
	
	@DeleteMapping("post/{no}")
	public String delete(@PathVariable("no") int no) {
		
		academyService.deletePost(no);
		
		return "redirect:/user/mypage";
	}
}
