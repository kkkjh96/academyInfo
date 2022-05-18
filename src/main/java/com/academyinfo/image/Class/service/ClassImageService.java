package com.academyinfo.image.Class.service;

import java.util.List;

import com.academyinfo.image.Class.dto.ClassImageResponseDto;

public interface ClassImageService {
	public List<ClassImageResponseDto> getImage();
	public List<ClassImageResponseDto> search(String keyword);
}
