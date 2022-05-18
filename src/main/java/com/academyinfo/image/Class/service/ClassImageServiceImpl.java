package com.academyinfo.image.Class.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academyinfo.image.Class.domain.entity.ClassImageEntity;
import com.academyinfo.image.Class.domain.repository.ClassImageRepository;
import com.academyinfo.image.Class.dto.ClassImageResponseDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClassImageServiceImpl implements ClassImageService {
	private ClassImageRepository classImageRepository;
	
	/*추천강의(c_status가 approval이면서 점수가 제일 높은 4개의 강의) - 사진*/
	/*select * from imagefile; 전체조회 */
	
	@Transactional(readOnly = true)
	public List<ClassImageResponseDto> getImage(){
		List<ClassImageEntity> classImageEntities = classImageRepository.findAll();
		List<ClassImageResponseDto> classImageDtoList = new ArrayList<>();
		
		if (classImageEntities.isEmpty()) {
	    	return classImageDtoList;
	    }

	    for (ClassImageEntity classImageEntity : classImageEntities) {
	        classImageDtoList.add(this.convertEntityToDto(classImageEntity));
	    }
		
		return classImageDtoList;
	}
	
	// 이름으로 사진 찾기
	@Transactional(readOnly = true)
	public List<ClassImageResponseDto> search(String keyword){
		List<ClassImageEntity> classImageEntities = classImageRepository.findByName(keyword);
		List<ClassImageResponseDto> classImageDtoList = new ArrayList<>();
		
		if (classImageEntities.isEmpty()) {
	    	return classImageDtoList;
	    }

	    for (ClassImageEntity classImageEntity : classImageEntities) {
	        classImageDtoList.add(this.convertEntityToDto(classImageEntity));
	    }
		return classImageDtoList;
	}
	
	private ClassImageResponseDto convertEntityToDto(ClassImageEntity classImageEntity) {
	    return new ClassImageResponseDto(classImageEntity);
	}
}
