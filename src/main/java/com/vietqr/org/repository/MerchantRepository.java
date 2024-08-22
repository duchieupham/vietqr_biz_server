package com.vietqr.org.repository;

import com.vietqr.org.entity.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantEntity, Long> {
    @Query(value = "SELECT * FROM merchant WHERE id = :id", nativeQuery = true)
    Optional<MerchantEntity> findMerchantById(@Param("id") String id);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM merchant m WHERE m.name = :name)", nativeQuery = true)
    boolean existsByName(@Param("name") String name);
}
