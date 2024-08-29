package com.vietqr.org.repository;

import com.vietqr.org.entity.MerchantSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantSettingRepository extends JpaRepository<MerchantSettingEntity, String> {
}
