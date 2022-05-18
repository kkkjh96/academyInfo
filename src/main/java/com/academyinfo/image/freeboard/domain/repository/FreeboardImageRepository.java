package com.academyinfo.image.freeboard.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academyinfo.image.freeboard.domain.entity.FreeboardImageEntity;

@Repository
public interface FreeboardImageRepository extends JpaRepository<FreeboardImageEntity,Integer> {
    List<FreeboardImageEntity> findByOriginalFilename(@Param("keyword") String keyword);
}
