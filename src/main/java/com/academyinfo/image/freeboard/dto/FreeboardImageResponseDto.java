package com.academyinfo.image.freeboard.dto;

import com.academyinfo.image.freeboard.domain.entity.FreeboardImageEntity;
import com.academyinfo.image.freeboard.files.FileType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class FreeboardImageResponseDto {
	private int iindex;
	private String originalFilename;
	private String storeFilename;
	private FileType fileType;
	
	// freeboard 테이블과 조인
	private int fbindex;
	
	@Builder
	public FreeboardImageResponseDto(int iindex, String originalFilename, String storeFilename, FileType fileType, int fbindex) {
		this.iindex = iindex;
		this.originalFilename = originalFilename;
		this.storeFilename = storeFilename;
		this.fileType = fileType;
		this.fbindex = fbindex;
	}
	
	@Builder
	public FreeboardImageResponseDto(FreeboardImageEntity freeboardImageEntity) {
		this.iindex = freeboardImageEntity.getIindex();
		this.originalFilename = freeboardImageEntity.getOriginalFilename();
		this.storeFilename = freeboardImageEntity.getStoreFilename();
		this.fileType = freeboardImageEntity.getFileType();
		this.fbindex = freeboardImageEntity.getFbindex().getFbindex();
	}
}
