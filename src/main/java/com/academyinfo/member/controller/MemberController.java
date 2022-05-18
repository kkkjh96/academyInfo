package com.academyinfo.member.controller;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;

import com.academyinfo.member.dto.MemberRequestDto;

public interface MemberController {
    /*
    // 메인 페이지
    @GetMapping("/")
    public String index() {
        return "/index";
    }
    */

    // 회원가입 페이지
    public String dispSignup();
    
    // 회원가입 처리
    public String execSignup(@Valid MemberRequestDto memberDto, Errors errors, Model model);
    
    /*
    // 사업자 회원가입 페이지
    @GetMapping("/user/cpnsignup")
    public String dispCpnSignup() {
        return "/cpnsignup";
    }
	*/
    
    // 로그인 페이지
    public String dispLogin(@RequestParam(value = "error", required = false) String error, Model model);

    // 로그인 결과 페이지
    public String dispLoginResult();

    // 로그아웃 결과 페이지
    public String dispLogout();
    
    // 접근 거부 페이지
    public String dispDenied();

    // 마이페이지
    public String dispMyInfo(Model model);
    
    // 회원 정보 수정 페이지(일반,기업 공통)
    public String dispUserInfo(Model model);
    
    // 회원 정보 수정하기
    public String updateUserInfo(@Valid MemberRequestDto memberDto, Errors errors, Model model);
    
    // 일반회원이 찜한 강의 목록 페이지
    public String dispUserClassList();
    
    // 학원이 등록한 강의 목록 페이지
    public String dispCpnClassList(Model model);
}