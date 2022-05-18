package com.academyinfo.image.freeboard.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.academyinfo.image.freeboard.domain.entity.FreeboardImageEntity;

public class FileStore {
	
	// 파일 경로 지정
	@Value("${file.dir}/")
	private String fileDirPath;
	
	
	// 파일의 확장자를 추출
	private String extractExt(String originalFilename) {
		int index = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(index);
		
		return ext;
	}
	
	// 저장할 파일의 이름 구성
	private String createStoreFilename(String originalFilename) {
		String uuid = UUID.randomUUID().toString(); // 파일 이름이 겹치지 않게 UUID로 설정
		String ext = extractExt(originalFilename); // 원본파일의 확장자 출력
		String storeFilename = uuid + ext;
		
		return storeFilename;
	}
	
	// 경로 생성
	public String createPath(String storeFilename, FileType fileType) {
		String path = (fileType == fileType.IMAGE) ? "images/" : "generals/"; 
		
		return fileDirPath + path + storeFilename;
	}
	
	// 파일 저장
	public FreeboardImageEntity storeFile(MultipartFile multipartFile, FileType fileType) throws IOException {
		
		// 첨부된 파일 없을 때
		if (multipartFile.isEmpty()) {  
			return null;
		}
		
		String originalFilename = multipartFile.getOriginalFilename();     // 파일 원본명
		String storeFilename = createStoreFilename(originalFilename);  // 저장 파일 이름 구성
		
		multipartFile.transferTo(new File(createPath(storeFilename, fileType)));
		
		return FreeboardImageEntity.builder()
				.originalFilename(originalFilename)
				.storeFilename(storeFilename)
				.fileType(fileType)
				.build();
	}
	
	
	// 전체 파일 저장
	public List<FreeboardImageEntity> storeFiles(List<MultipartFile> multipartFiles, FileType fileType) throws IOException {
		List<FreeboardImageEntity> files = new ArrayList<>();
		
		for (MultipartFile multipartFile : multipartFiles) {
			if (!multipartFile.isEmpty()) {
				files.add(storeFile(multipartFile, fileType));
			}
		}
		
		return files;
	}
}
