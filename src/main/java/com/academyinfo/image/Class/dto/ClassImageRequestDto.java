package com.academyinfo.image.Class.dto;

import com.academyinfo.Class.domain.entity.ClassEntity;
import com.academyinfo.image.Class.domain.entity.ClassImageEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClassImageRequestDto {
	private int iindex;
	private String name;
	private String path;
	
	private ClassEntity cindex;
	
	public ClassImageEntity toEntity() {
		ClassImageEntity classimageEntity = ClassImageEntity.builder()
				.iindex(iindex)
				.name(name)
				.path(path)
				.cindex(cindex)
				.build();
		
		return classimageEntity;
	}
}
