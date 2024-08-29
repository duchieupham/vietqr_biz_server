package com.vietqr.org.repository;

import com.vietqr.org.entity.MerchantSupportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantSupportRepository extends JpaRepository<MerchantSupportEntity, String> {
}
