package com.academyinfo.Class.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academyinfo.Class.domain.entity.ClassEntity;
import com.academyinfo.Class.domain.repository.ClassRepository;
import com.academyinfo.Class.dto.ClassRequestDto;
import com.academyinfo.Class.dto.ClassResponseDto;
import com.academyinfo.academy.domain.entity.AcademyEntity;
import com.academyinfo.academy.domain.repository.AcademyRepository;
import com.academyinfo.image.Class.domain.repository.ClassImageRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClassServiceImpl implements ClassService {
	private ClassRepository classRepository;
	private AcademyRepository academyRepository;
	
	private static final int RECOMMAND_INT = 4;
	
	//select * from class;
	@Transactional(readOnly = true)
	public List<ClassResponseDto> getClasslist(){
		List<ClassEntity> classEntities = classRepository.findAll();
		List<ClassResponseDto> classDtoList = new ArrayList<>();
		
		if (classEntities.isEmpty()) {
			return classDtoList;
		}
		
		for (ClassEntity classEntity : classEntities) {
			classDtoList.add(this.convertEntityToDto(classEntity));
		}
		
		return classDtoList;
	}
	
	/*추천강의(c_status가 approval이면서 점수가 제일 높은 4개의 강의를 최근등록순) - 강의내용*/
	
	@Transactional(readOnly = true)
	public List<ClassResponseDto> getRecommendClass(){
		Sort sort = Sort.by(
				Sort.Order.desc("score"),
				Sort.Order.desc("cindex")
				);
		String status = "approval";
		
		List<ClassEntity> classEntities = classRepository.findByStatus(status, sort);
		List<ClassResponseDto> recommandClassList = new ArrayList<>();
		
		if (classEntities.isEmpty()) {
			return recommandClassList;
		}
		
		for(int i=0; i < RECOMMAND_INT; i++) {
			recommandClassList.add(this.convertEntityToDto(classEntities.get(i)));
		}
		
		return recommandClassList;
	}
	
	/* 키워드 검색 : 체크박스 선택 없을 때 */
	@Transactional(readOnly = true)
	public List<ClassResponseDto> search(String keyword){
		List<ClassEntity> classEntities = classRepository.findByNameContaining(keyword);
		List<ClassResponseDto> classDtoList = new ArrayList<>();
		
		if (classEntities.isEmpty()) {
			return classDtoList;
		}
		
		for (ClassEntity classEntity : classEntities) {
			classDtoList.add(this.convertEntityToDto(classEntity));
		}
		
		return classDtoList;
	}
	
	@Transactional(readOnly = true)
	public List<ClassResponseDto> searchFilter(String keyword, String[] arr_location) {
		List<ClassEntity> classEntities = classRepository.selectFilter(keyword, arr_location);
		List<ClassResponseDto> classDtoList = new ArrayList<>();
		
		if (classEntities.isEmpty()) {
			return classDtoList;
		}
		
		for (ClassEntity classEntity : classEntities) {
			classDtoList.add(this.convertEntityToDto(classEntity));
		}
		
		return classDtoList;
	}
	
	/*키워드와 서울지역 검색
	public List<ClassResponseDto> testFilter_S(String keyword, String[] arr_location_S){
		List<ClassEntity> classEntities = classRepository.testFilter_S(keyword, arr_location_S);		
		List<ClassResponseDto> classDtoList = new ArrayList<>();
		
		if (classEntities.isEmpty()) {
			return classDtoList;
		}
		
		for (ClassEntity classEntity : classEntities) {
			classDtoList.add(this.convertEntityToDto(classEntity));
		}
		
		return classDtoList;
	}
	*/
	/*키워드와 부산지역 검색
	public List<ClassResponseDto> testFilter_B(String keyword, String[] arr_location_B) {
		List<ClassEntity> classEntities = classRepository.testFilter_B(keyword, arr_location_B);
		List<ClassResponseDto> classDtoList = new ArrayList<>();
		
		if (classEntities.isEmpty()) {
			return classDtoList;
		}
		
		for (ClassEntity classEntity : classEntities) {
			classDtoList.add(this.convertEntityToDto(classEntity));
		}
		
		return classDtoList;
	}
	*/
	
	// builder로 게시된 글 상세보기
	@Transactional
	public ClassResponseDto getPost(int cindex) {
	    Optional<ClassEntity> classEntityWrapper = classRepository.findById(cindex);
	    // findbyId : Primary key 값을 where 조건으로 데이터를 DB로부터 가져오는 method
	    ClassEntity classEntity = classEntityWrapper.get();
	    // Wrapper.get()로 classEntity 객체를 가져옴

	    // classDto classDTO = convertEntityToDto(classEntity);
	    ClassResponseDto classDTO = convertEntityToDto(classEntity);
	    
	    return classDTO;
	}
	
	// insert
	@Transactional
	public int savePost(int aindex, ClassRequestDto classRequestDto) {
		Optional<AcademyEntity> academyEntityWrapper = academyRepository.findById(aindex);
        AcademyEntity academy = academyEntityWrapper.get();
        
        classRequestDto.setAindex(academy); // academy 외래키 설정
		
		return classRepository.save(classRequestDto.toEntity()).getCindex();
	}
	
	// delete
	@Transactional
    public void deletePost(int cindex) {
        classRepository.deleteById(cindex);
        // deleteById : Primary key 값을 where 조건으로 데이터를 DB에서 삭제
    }
	
	private ClassResponseDto convertEntityToDto(ClassEntity classEntity) {
		ClassResponseDto classResponseDto = new ClassResponseDto(classEntity);
		
		return classResponseDto;
	}
	
	// 강의 권한 승인
	@Transactional
	public int updateStatus(int cindex) {
		return classRepository.updateStatus(cindex);
	}
}
