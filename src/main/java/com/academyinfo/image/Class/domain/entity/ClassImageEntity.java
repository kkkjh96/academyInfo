package com.academyinfo.image.Class.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.academyinfo.Class.domain.entity.ClassEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="classimage")
public class ClassImageEntity {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iindex;
	
	@Column
	private String name;
	
	@Column
	private String path;
	
	// class와 N:1
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cindex")
	private ClassEntity cindex;

	@Builder
	public ClassImageEntity(int iindex, String name, String path, ClassEntity cindex) {
		this.iindex = iindex;
		this.name = name;
		this.path = path;
		this.cindex = cindex;
	}
}
