package com.academyinfo.image.academy.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.academyinfo.academy.domain.entity.AcademyEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="academyimage")
public class AcademyImageEntity {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iindex;
	
	@Column
	private String name;
	
	@Column
	private String path;
	
	// academyEntity와 1:N
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aindex")
	private AcademyEntity aindex;

	@Builder
	public AcademyImageEntity(int iindex, String name, String path, AcademyEntity aindex) {
		this.iindex = iindex;
		this.name = name;
		this.path = path;
		this.aindex = aindex;
	}
}
