package com.academyinfo.image.Class.dto;

import com.academyinfo.image.Class.domain.entity.ClassImageEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ClassImageResponseDto {
	private int iindex;
	private String name;
	private String path;
	
	// class 테이블과 조인
	private int cindex;
	
	@Builder
	public ClassImageResponseDto(int iindex, String name, String path, int cindex) {
		this.iindex = iindex;
		this.name = name;
		this.path = path;
		this.cindex = cindex;
	}
	
	@Builder
	public ClassImageResponseDto(ClassImageEntity classImageEntity) {
		this.iindex = classImageEntity.getIindex();
		this.name = classImageEntity.getName();
		this.path = classImageEntity.getPath();
		this.cindex = classImageEntity.getCindex().getCindex();
	}
}
