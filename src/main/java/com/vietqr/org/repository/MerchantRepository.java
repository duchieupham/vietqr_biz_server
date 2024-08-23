package com.vietqr.org.repository;

import com.vietqr.org.entity.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantEntity, Long> {
    @Query(value = "SELECT * FROM merchant WHERE id = :id", nativeQuery = true)
    Optional<MerchantEntity> findMerchantById(@Param("id") String id);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM merchant m WHERE m.name = :name)", nativeQuery = true)
    int existsByName(@Param("name") String name);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM merchant m WHERE m.publish_id = :publishId)", nativeQuery = true)
    int existsByPublishId(@Param("publishId") String publishId);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM merchant m WHERE m.national_id = :nationalId)", nativeQuery = true)
    int existsByNationalId(@Param("nationalId") int nationalId);

    @Query(value = "SELECT * FROM merchant WHERE time_updated_status IS NOT NULL AND time_updated_status >= :sixMonthsAgo", nativeQuery = true)
    List<MerchantEntity> findDeletedMerchants(@Param("sixMonthsAgo") Long sixMonthsAgo);

}
