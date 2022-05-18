package com.academyinfo.academy.service;

import com.academyinfo.academy.dto.AcademyRequestDto;
import com.academyinfo.academy.dto.AcademyResponseDto;

public interface AcademyService {
	public AcademyResponseDto getPost(int aindex);
	public int savePost(String id, AcademyRequestDto academyRequestDto);
    public void deletePost(int aindex);
}
