package com.academyinfo.image.academy.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academyinfo.image.academy.domain.entity.AcademyImageEntity;

@Repository
public interface AcademyImageRepository extends JpaRepository<AcademyImageEntity,Integer> {
    List<AcademyImageEntity> findByName(@Param("keyword") String keyword);
}
