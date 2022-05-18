package com.academyinfo.image.academy.dto;

import com.academyinfo.image.academy.domain.entity.AcademyImageEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class AcademyImageResponseDto {
	private int iindex;
	private String name;
	private String path;
	
	// academy 테이블과 조인
	private int aindex;
	
	@Builder
	public AcademyImageResponseDto(int iindex, String name, String path, int aindex) {
		this.iindex = iindex;
		this.name = name;
		this.path = path;
		this.aindex = aindex;
	}
	
	@Builder
	public AcademyImageResponseDto(AcademyImageEntity imageEntity) {
		this.iindex = imageEntity.getIindex();
		this.name = imageEntity.getName();
		this.path = imageEntity.getPath();
		this.aindex = imageEntity.getAindex().getAindex();
	}
}
