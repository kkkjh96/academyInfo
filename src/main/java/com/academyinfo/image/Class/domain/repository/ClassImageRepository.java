package com.academyinfo.image.Class.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academyinfo.image.Class.domain.entity.ClassImageEntity;

@Repository
public interface ClassImageRepository extends JpaRepository<ClassImageEntity,Integer> {
    List<ClassImageEntity> findByName(@Param("keyword") String keyword);
}
