package com.academyinfo.image.academy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academyinfo.image.academy.domain.entity.AcademyImageEntity;
import com.academyinfo.image.academy.domain.repository.AcademyImageRepository;
import com.academyinfo.image.academy.dto.AcademyImageResponseDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AcademyImageServiceImpl implements AcademyImageService {
	private AcademyImageRepository academyImageRepository;
	
	/*추천강의(c_status가 approval이면서 점수가 제일 높은 4개의 강의) - 사진*/
	/*select * from academyImagefile; 전체조회 */
	
	@Transactional(readOnly = true)
	public List<AcademyImageResponseDto> getImage(){
		List<AcademyImageEntity> academyImageEntities = academyImageRepository.findAll();
		List<AcademyImageResponseDto> academyImageDtoList = new ArrayList<>();
		
		if (academyImageEntities.isEmpty()) {
	    	return academyImageDtoList;
	    }

	    for (AcademyImageEntity academyImageEntity : academyImageEntities) {
	        academyImageDtoList.add(this.convertEntityToDto(academyImageEntity));
	    }
		
		return academyImageDtoList;
	}
	
	// 이름으로 사진 찾기
	@Transactional(readOnly = true)
	public List<AcademyImageResponseDto> search(String keyword){
		List<AcademyImageEntity> academyImageEntities = academyImageRepository.findByName(keyword);
		List<AcademyImageResponseDto> academyImageDtoList = new ArrayList<>();
		
		if (academyImageEntities.isEmpty()) {
	    	return academyImageDtoList;
	    }

	    for (AcademyImageEntity academyImageEntity : academyImageEntities) {
	        academyImageDtoList.add(this.convertEntityToDto(academyImageEntity));
	    }
		return academyImageDtoList;
	}
	
	private AcademyImageResponseDto convertEntityToDto(AcademyImageEntity academyImageEntity) {
	    return new AcademyImageResponseDto(academyImageEntity);
	}
}
