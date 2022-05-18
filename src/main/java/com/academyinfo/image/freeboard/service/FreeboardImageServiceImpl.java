package com.academyinfo.image.freeboard.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.academyinfo.image.freeboard.domain.entity.FreeboardImageEntity;
import com.academyinfo.image.freeboard.domain.repository.FreeboardImageRepository;
import com.academyinfo.image.freeboard.dto.FreeboardImageResponseDto;
import com.academyinfo.image.freeboard.files.FileStore;
import com.academyinfo.image.freeboard.files.FileType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FreeboardImageServiceImpl implements FreeboardImageService {
	private FreeboardImageRepository freeboardImageRepository;
	// private final FileStore fileStore;
	
	/*추천강의(c_status가 approval이면서 점수가 제일 높은 4개의 강의) - 사진*/
	/*select * from imagefile; 전체조회 */
	
	@Transactional(readOnly = true)
	public List<FreeboardImageResponseDto> getImage(){
		List<FreeboardImageEntity> freeboardImageEntities = freeboardImageRepository.findAll();
		List<FreeboardImageResponseDto> imageDtoList = new ArrayList<>();
		
		if (freeboardImageEntities.isEmpty()) {
	    	return imageDtoList;
	    }

	    for (FreeboardImageEntity freeboardImageEntity : freeboardImageEntities) {
	        imageDtoList.add(this.convertEntityToDto(freeboardImageEntity));
	    }
		
		return imageDtoList;
	}
	
	// 이름으로 사진 찾기
	@Transactional(readOnly = true)
	public List<FreeboardImageResponseDto> search(String keyword){
		List<FreeboardImageEntity> freeboardImageEntities = freeboardImageRepository.findByOriginalFilename(keyword);
		List<FreeboardImageResponseDto> imageDtoList = new ArrayList<>();
		
		if (freeboardImageEntities.isEmpty()) {
	    	return imageDtoList;
	    }

	    for (FreeboardImageEntity freeboardImageEntity : freeboardImageEntities) {
	        imageDtoList.add(this.convertEntityToDto(freeboardImageEntity));
	    }
		return imageDtoList;
	}
	
	
	/*
	// 파일을 저장 >>> freeboardService에서
	@Transactional
	public List<FreeboardImageEntity> saveFiles(Map<FileType, List<MultipartFile>> multipartFileListMap) throws IOException {
		List<FreeboardImageEntity> imageFiles = fileStore.storeFiles(multipartFileListMap.get(FileType.IMAGE), FileType.IMAGE);
		List<FreeboardImageEntity> generalFiles = fileStore.storeFiles(multipartFileListMap.get(FileType.GENERAL), FileType.GENERAL);
		List<FreeboardImageEntity> result = Stream.of(imageFiles, generalFiles)
				.flatMap(f -> f.stream())
				.collect(Collectors.toList());
		
		return result;
	}
	*/
	private FreeboardImageResponseDto convertEntityToDto(FreeboardImageEntity freeboardImageEntity) {
	    return new FreeboardImageResponseDto(freeboardImageEntity);
	}
	
}
