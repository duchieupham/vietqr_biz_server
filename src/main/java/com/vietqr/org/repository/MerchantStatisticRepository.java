package com.vietqr.org.repository;

import com.vietqr.org.entity.MerchantStatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantStatisticRepository extends JpaRepository<MerchantStatisticEntity, String> {
}
