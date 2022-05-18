package com.academyinfo.image.academy.service;

import java.util.List;

import com.academyinfo.image.academy.dto.AcademyImageResponseDto;

public interface AcademyImageService {
	public List<AcademyImageResponseDto> getImage();
	public List<AcademyImageResponseDto> search(String keyword);
}
