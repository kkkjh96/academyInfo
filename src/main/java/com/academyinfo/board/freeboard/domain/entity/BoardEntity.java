package com.academyinfo.board.freeboard.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.academyinfo.board.comment.domain.entity.CommentEntity;
import com.academyinfo.common.entity.TimeEntity;
import com.academyinfo.image.freeboard.domain.entity.FreeboardImageEntity;
import com.academyinfo.member.domain.entity.MemberEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "freeboard")
public class BoardEntity extends TimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fbindex;
	
	@Column(length = 20, nullable = false) 
	private String title;
	
	// @Column(length = 200, nullable = false)
	@Column(columnDefinition = "TEXT", nullable = false)
	private String contents;
	
	@Column(columnDefinition = "integer default 0")
	private int count;
	
	/* 신고 카운트 */
	@Column(columnDefinition = "integer default 0")
	private int report;
	
	// Member와 N:1조인
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MINDEX")
	private MemberEntity mindex;
	
	/*
	 * @Column
	 * private String writer;
	 */
	
	// 이미지 테이블과 1:N 조인
	@OneToMany(mappedBy = "fbindex", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<FreeboardImageEntity> iindex;
	
	// 댓글과 1:N 조인
	@OneToMany(mappedBy = "fbindex", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<CommentEntity> cmtindex;
	
	@Builder
	public BoardEntity(int fbindex, String title, String contents, int count, int report, MemberEntity mindex, List<FreeboardImageEntity> iindex, List<CommentEntity> cmtindex) {
		this.fbindex = fbindex;
		this.title = title;
		this.contents = contents;
		this.count = count;
		this.report = report;
		
		this.mindex = mindex;
		this.iindex = iindex;
		this.cmtindex = cmtindex;
	}
}
