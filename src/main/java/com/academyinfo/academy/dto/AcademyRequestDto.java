package com.academyinfo.academy.dto;

import java.util.List;

import com.academyinfo.Class.domain.entity.ClassEntity;
import com.academyinfo.academy.domain.entity.AcademyEntity;
import com.academyinfo.image.academy.domain.entity.AcademyImageEntity;
import com.academyinfo.member.domain.entity.MemberEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AcademyRequestDto {
	private int aindex;
	private String name;
	private int grade;
	private String location;
	private String address;
	private String companynum;
	private String info;
	
	private MemberEntity mindex;
	private List<ClassEntity> cindex;
	private List<AcademyImageEntity> iindex;
	
	public AcademyEntity toEntity() {
		AcademyEntity academyEntity = AcademyEntity.builder()
				.aindex(aindex)
				.name(name)
				.grade(grade)
				.location(location)
				.address(address)
				.companynum(companynum)
				.info(info)
				.mindex(mindex)
				.cindex(cindex)
				.iindex(iindex)
				.build();
		
		return academyEntity;
	}
}
