package com.vietqr.org.repository;

import com.vietqr.org.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, String> {
    @Query(value = "SELECT * FROM image WHERE id = :id", nativeQuery = true)
    ImageEntity findImageById(@Param("id") String id);

    @Query(value = "SELECT * FROM image WHERE name = :name", nativeQuery = true)
    ImageEntity findImageByName(@Param("name") String name);
}
