package com.academyinfo.image.freeboard.dto;

import com.academyinfo.board.freeboard.domain.entity.BoardEntity;
import com.academyinfo.image.freeboard.domain.entity.FreeboardImageEntity;
import com.academyinfo.image.freeboard.files.FileType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FreeboardImageRequestDto {
	private int iindex;
	private String originalFilename;
	private String storeFilename;
	private FileType fileType;
	
	private BoardEntity fbindex;
	
	public FreeboardImageEntity toEntity() {
		FreeboardImageEntity imageEntity = FreeboardImageEntity.builder()
				.iindex(iindex)
				.originalFilename(originalFilename)
				.storeFilename(storeFilename)
				.fileType(fileType)
				.fbindex(fbindex)
				.build();
		
		return imageEntity;
	}
}
