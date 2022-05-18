package com.academyinfo.Class.dto;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.academyinfo.Class.domain.entity.ClassEntity;
import com.academyinfo.image.Class.domain.entity.ClassImageEntity;
import com.academyinfo.image.Class.dto.ClassImageResponseDto;
import com.academyinfo.member_class.domain.entity.MemberClassEntity;
import com.academyinfo.member_class.dto.MemberClassResponseDto;
import com.academyinfo.review.domain.entity.ReviewEntity;
import com.academyinfo.review.dto.ReviewResponseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ClassResponseDto {
	private int cindex;
	private String name;
	private String category;
	private int price;
	private double score;
	private String status;
	private Date startperiod;
	private Date endperiod;
	private String info;
	
	// academy 테이블과의 조인 설정
	private int aindex;
	private String aname;
	private int agrade;
	private String alocation;
	
	// member - academy - class
	private Long mindex;
	private String mid;
	
	// image 테이블과 조인 설정
	private List<ClassImageResponseDto> iindex;
	
	// review 테이블과의 조인 설정
	private List<ReviewResponseDto> rvindex;
	
	// member_class 테이블과의 조인 설정
	private List<MemberClassResponseDto> mcindex;
	
	@Builder
	public ClassResponseDto(int cindex, String name, String category, int price, double score, String status, Date startperiod, Date endperiod, String location, String info,  int aindex, String aname, int agrade, String alocation, Long mindex, String mid, List<ClassImageEntity> iindex, List<ReviewEntity> rvindex, List<MemberClassEntity> mcindex) {
		this.cindex = cindex;
		this.name = name;
		this.category = category;
		this.price = price;
		this.score = score;
		this.status = status;
		this.startperiod = startperiod;
		this.endperiod = endperiod;
		this.info = info;
		this.aindex = aindex;
		this.aname = aname;
		this.alocation = alocation;
		this.agrade = agrade;
		this.mindex = mindex;
		this.mid = mid;
		
		
		// 리뷰 List 타입을 DTO 클래스로 하여 엔티티 간 무한참조 방지
		this.iindex = iindex.stream().map(ClassImageResponseDto::new).collect(Collectors.toList());
		
		this.rvindex = rvindex.stream().map(ReviewResponseDto::new).collect(Collectors.toList());
		
		this.mcindex = mcindex.stream().map(MemberClassResponseDto::new).collect(Collectors.toList());
	}
	
	@Builder
	public ClassResponseDto(ClassEntity classEntity) {
		this.cindex = classEntity.getCindex();
		this.name = classEntity.getName();
		this.category = classEntity.getCategory();
		this.price = classEntity.getPrice();
		this.score = classEntity.getScore();
		this.status = classEntity.getStatus();
		this.startperiod = classEntity.getStartperiod();
		this.endperiod = classEntity.getEndperiod();
		this.info = classEntity.getInfo();
		
		this.aindex = classEntity.getAindex().getAindex();
		this.aname = classEntity.getAindex().getName();
		this.alocation = classEntity.getAindex().getLocation();
		this.agrade = classEntity.getAindex().getGrade();
		this.mid = classEntity.getAindex().getMindex().getId();
		this.mindex = classEntity.getAindex().getMindex().getMindex();
		
		// List 타입을 DTO 클래스로 하여 엔티티 간 무한참조 방지
		this.iindex = classEntity.getIindex().stream().map(ClassImageResponseDto::new).collect(Collectors.toList());
		
		this.rvindex = classEntity.getRvindex().stream().map(ReviewResponseDto::new).collect(Collectors.toList());
		
		this.mcindex = classEntity.getMcindex().stream().map(MemberClassResponseDto::new).collect(Collectors.toList());
	}
}
