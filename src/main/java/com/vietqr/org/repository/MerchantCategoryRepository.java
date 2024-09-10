package com.vietqr.org.repository;

import com.vietqr.org.entity.MerchantCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MerchantCategoryRepository extends JpaRepository<MerchantCategoryEntity, String> {

    @Query(value = "SELECT * FROM merchant_category WHERE id = :id", nativeQuery = true)
    Optional<MerchantCategoryEntity> findMerchantCategoryById(@Param("id") String id);

    @Query(value = "SELECT * FROM merchant_category WHERE status = true", nativeQuery = true)
    List<MerchantCategoryEntity> getAllMerchantCategory();
}
