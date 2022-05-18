package com.academyinfo.Class.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.academyinfo.Class.dto.ClassRequestDto;
import com.academyinfo.Class.dto.ClassResponseDto;
import com.academyinfo.Class.service.ClassService;
import com.academyinfo.member_class.dto.MemberClassResponseDto;
import com.academyinfo.review.dto.ReviewResponseDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/claco")
@Controller
public class ClassControllerImpl implements ClassController {
	private ClassService classService;
	
	@GetMapping("")
	public String list() {
		return "redirect:/claco/search";
	}
	
	/*
	@GetMapping("/search")
	public String search(@RequestParam(value="keyword", required=false) String keyword, Model model) {
		List<ClassResponseDto> listFilter = classService.search(keyword);
		
		model.addAttribute("listFilter", listFilter);
		
		return "/claco/searchlist";
	}
	*/
	
	// 강의 등록화면으로 이동
	// no : academy.index
	@GetMapping("/add/{no}")
	public String add(@PathVariable("no") int aindex, Model model) {
		
		model.addAttribute("aindex", aindex);
		
		return "/claco/add";
	}
	
	
	// 강의 등록화면에서 데이터 넘어오면 DB 저장
	@PostMapping("/post/{no}")
	public String write(@PathVariable("no") int aindex, ClassRequestDto classRequestDto) {
		
		classService.savePost(aindex, classRequestDto);
			
		return "redirect:/user/cpnClassList";
	}
	
	// 강의 상세보기
	@GetMapping("/post/{no}")
	public String detail(@PathVariable("no") int no, Model model) {
		ClassResponseDto classDTO = classService.getPost(no);
		List<ReviewResponseDto> reviewList = classDTO.getRvindex();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 현재 로그인한 사용자의 정보를 가져온다    
		
		List<MemberClassResponseDto> memberClassDto = classDTO.getMcindex(); // 해당 강의 즐겨찾기 개수 확인용
		 
	    boolean isWriter = false;
	    boolean isLogin = false;
	        
	    // 로그인 상태 체크
	    if (!principal.toString().equals("anonymousUser")) {
	    	String id = ((UserDetails)principal).getUsername(); // 사용자의 Id
	     	isLogin = true;
	     	
	     	model.addAttribute("loginUser", id);
	    	
	       	// 강의정보 작성자 본인확인
	       	if (classDTO.getMid().equals(id)) {
	       		isWriter = true;
	       	}
	    }
	        
	    // 수강후기
	    if (reviewList != null && !reviewList.isEmpty()) {
	     	model.addAttribute("reviews", reviewList);
	       	model.addAttribute("reviewCnt", reviewList.size());
	    } else {
	    	model.addAttribute("reviewCnt", 0);
	    }
	    
	    // 즐겨찾기
	    if (memberClassDto != null && !memberClassDto.isEmpty()) {
		   	model.addAttribute("favorite", memberClassDto.size());
	    } else {
	    	model.addAttribute("favorite", 0);
	    }
	      
	    model.addAttribute("isLogin", isLogin);
	    model.addAttribute("isWriter", isWriter);
	   	model.addAttribute("classDto", classDTO);
	        
	   	return "/claco/detail";
	 
	}

	// 강의 정보 수정
	@GetMapping("/post/edit/{no}")
	public String edit(@PathVariable("no") int no, @RequestParam("aindex") int aindex, Model model) {
	    ClassResponseDto classDTO = classService.getPost(no);
	    
	    model.addAttribute("classDto", classDTO);
	    model.addAttribute(aindex);
	        
	    return "/claco/update";
	}

	// 강의 정보 수정 값 DB 저장
	@PutMapping("/post/edit/{no}")
	public String update(ClassRequestDto classDTO) {
	   	
		int aindex = classDTO.getAindex().getAindex();
	 	
	    classService.savePost(aindex, classDTO);

	    return "redirect:/claco/post/{no}";
	}
	    
	// 강의 정보 삭제 실행
	@DeleteMapping("/post/{no}")
	public String delete(@PathVariable("no") int no) {
	    classService.deletePost(no);
	    
	    return "redirect:/user/cpnClassList";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(value="keyword", required=false) String keyword, @RequestParam(value="arr_location", required=false) String[] arr_location, Model model) {
		List<ClassResponseDto> listFilter = null;
		
		if(arr_location.length == 0) {
			System.out.println("controller : 배열값이 넘어오지 않았습니다.");
		}
		else {
			for(int i = 0; i<arr_location.length; i++) {
				System.out.println(arr_location[i]);
			}
		}
		
		if(arr_location.length != 0) {
			listFilter = classService.searchFilter(keyword,arr_location);
		}
		else {
			listFilter = classService.search(keyword);
		}
		
		model.addAttribute("listFilter", listFilter);
		
		return "/claco/searchlist";
	}
}
