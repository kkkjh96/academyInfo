package com.academyinfo.image.freeboard.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.academyinfo.board.freeboard.domain.entity.BoardEntity;
import com.academyinfo.image.freeboard.files.FileType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="freeboardimage")
public class FreeboardImageEntity {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iindex;
	
	@Column
	private String originalFilename;
	
	@Column
	private String storeFilename;
	
	@Enumerated(EnumType.STRING)
	private FileType fileType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fbindex")
	private BoardEntity fbindex;

	@Builder
	public FreeboardImageEntity(int iindex, String originalFilename, String storeFilename, FileType fileType, BoardEntity fbindex){
		this.iindex = iindex;
		this.originalFilename = originalFilename;
		this.storeFilename = storeFilename;
		this.fileType = fileType;
		this.fbindex = fbindex;
	}
}
