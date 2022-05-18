package com.academyinfo.image.freeboard.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.academyinfo.image.freeboard.domain.entity.FreeboardImageEntity;
import com.academyinfo.image.freeboard.dto.FreeboardImageResponseDto;
import com.academyinfo.image.freeboard.files.FileType;

public interface FreeboardImageService {
	public List<FreeboardImageResponseDto> getImage();
	public List<FreeboardImageResponseDto> search(String keyword);
	// public List<FreeboardImageEntity> saveFiles(Map<FileType, List<MultipartFile>> multipartFileListMap) throws IOException;
}
