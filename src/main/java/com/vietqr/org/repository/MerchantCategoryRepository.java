package com.vietqr.org.repository;

import com.vietqr.org.dto.merchantcategory.IMerchantCategoryMidDTO;
import com.vietqr.org.dto.merchantcategory.IMerchantCategoryIdDTO;
import com.vietqr.org.entity.MerchantCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MerchantCategoryRepository extends JpaRepository<MerchantCategoryEntity, String> {

    @Query(value = "SELECT mid AS mid, name AS name, status AS status FROM merchant_category WHERE id = :id LIMIT 1", nativeQuery = true)
    Optional<IMerchantCategoryIdDTO> findMerchantCategoryById(@Param("id") String id);

    @Query(value = "SELECT id AS id, name AS name, status AS status FROM merchant_category WHERE mid = :mid AND status = 1", nativeQuery = true)
    List<IMerchantCategoryMidDTO> findMerchantCategoryByMid(@Param("mid") String mid);
}
