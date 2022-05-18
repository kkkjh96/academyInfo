package com.academyinfo.Class.service;

import java.util.List;

import com.academyinfo.Class.dto.ClassRequestDto;
import com.academyinfo.Class.dto.ClassResponseDto;

public interface ClassService {
	public List<ClassResponseDto> getClasslist();
	public List<ClassResponseDto> getRecommendClass();
	public List<ClassResponseDto> search(String keyword);
	public List<ClassResponseDto> searchFilter(String keyword, String[] arr_location);
	/*
	public List<ClassResponseDto> testFilter_S(String keyword, String[] arr_location_S);
	public List<ClassResponseDto> testFilter_B(String keyword, String[] arr_location_B);
	*/
	public int updateStatus(int cindex);
	public ClassResponseDto getPost(int cindex);
	public int savePost(int aindex, ClassRequestDto classRequestDto);
	public void deletePost(int cindex);
}
