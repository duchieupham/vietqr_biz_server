package com.vietqr.org.repository;

import com.vietqr.org.entity.MerchantRoleEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchantRoleRepository extends JpaRepository<MerchantRoleEntity, String> {
    @Query(value = "SELECT * FROM merchant_role where id = :id LIMIT 1", nativeQuery = true)
    MerchantRoleEntity findMerchantRoleById(@Param("id") String id);
}
