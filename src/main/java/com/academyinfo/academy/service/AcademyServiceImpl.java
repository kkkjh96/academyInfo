package com.academyinfo.academy.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academyinfo.academy.domain.entity.AcademyEntity;
import com.academyinfo.academy.domain.repository.AcademyRepository;
import com.academyinfo.academy.dto.AcademyRequestDto;
import com.academyinfo.academy.dto.AcademyResponseDto;
import com.academyinfo.member.domain.entity.MemberEntity;
import com.academyinfo.member.domain.repository.MemberRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AcademyServiceImpl implements AcademyService {
	private AcademyRepository academyRepository;
	private MemberRepository memberRepository;
	
	// builder로 게시된 글 상세보기
	@Transactional
	public AcademyResponseDto getPost(int aindex) {
	    Optional<AcademyEntity> academyEntityWrapper = academyRepository.findById(aindex);
	    // findbyId : Primary key 값을 where 조건으로 데이터를 DB로부터 가져오는 method
	    AcademyEntity academyEntity = academyEntityWrapper.get();
	    // Wrapper.get()로 academyEntity 객체를 가져옴

	    // academyDto academyDTO = convertEntityToDto(academyEntity);
	    AcademyResponseDto academyDTO = convertEntityToDto(academyEntity);
	    
	    return academyDTO;
	}
	
	// insert
	@Transactional
	public int savePost(String id, AcademyRequestDto academyRequestDto) {
		Optional<MemberEntity> memberEntityWrapper = memberRepository.findById(id);
        MemberEntity member = memberEntityWrapper.get();        
        
        academyRequestDto.setMindex(member); // member 외래키 설정
		
		return academyRepository.save(academyRequestDto.toEntity()).getAindex();
	}
	
	// delete
	@Transactional
    public void deletePost(int aindex) {
        academyRepository.deleteById(aindex);
        // deleteById : Primary key 값을 where 조건으로 데이터를 DB에서 삭제
    }
	
	private AcademyResponseDto convertEntityToDto(AcademyEntity academyEntity) {
		AcademyResponseDto academyResponseDto = new AcademyResponseDto(academyEntity);		
		return academyResponseDto;
	}
}
