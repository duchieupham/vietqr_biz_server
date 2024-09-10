package com.vietqr.org.repository;

import com.vietqr.org.entity.MerchantProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MerchantProductRepository extends JpaRepository<MerchantProductEntity, String> {
    @Query(value = "SELECT * FROM merchant_product WHERE id = :id", nativeQuery = true)
    Optional<MerchantProductEntity> getMerchantProductById(@Param("id") String id);

    @Query(value = "SELECT * FROM merchant_product WHERE status = 0", nativeQuery = true)
    List<MerchantProductEntity> getAllMerchantProduct();
}
