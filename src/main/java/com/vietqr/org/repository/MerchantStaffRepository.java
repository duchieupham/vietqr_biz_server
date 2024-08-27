package com.vietqr.org.repository;

import com.vietqr.org.entity.MerchantStaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MerchantStaffRepository extends JpaRepository<MerchantStaffEntity, String> {
    @Query(value = "SELECT * FROM merchant_staff"
            + " WHERE id = :id LIMIT 1"
            , nativeQuery = true)
    MerchantStaffEntity findMerchantStaffById(@Param(value = "id") String id);

    @Query(value = "SELECT * FROM merchant_staff"
            + " WHERE mid = :mid"
            , nativeQuery = true)
    List<MerchantStaffEntity> findAllMerchantStaffByMid(@Param(value = "mid") String mid);

    @Query(value = "SELECT * FROM merchant_staff"
            + " WHERE tid = :tid"
            , nativeQuery = true)
    List<MerchantStaffEntity> findAllMerchantStaffByTid(@Param(value = "tid") String tid);

    @Query(value = "SELECT COUNT(*) FROM merchant_staff"
            + " WHERE id = :id"
            , nativeQuery = true)
    int countMerchantStaffById(@Param(value = "id") String id);
}
