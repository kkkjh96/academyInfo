package com.academyinfo.image.academy.dto;

import com.academyinfo.academy.domain.entity.AcademyEntity;
import com.academyinfo.image.academy.domain.entity.AcademyImageEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AcademyImageRequestDto {
	private int iindex;
	private String name;
	private String path;
	
	private AcademyEntity aindex;
	
	public AcademyImageEntity toEntity() {
		AcademyImageEntity imageEntity = AcademyImageEntity.builder()
				.iindex(iindex)
				.name(name)
				.path(path)
				.aindex(aindex)
				.build();
		
		return imageEntity;
	}
}
