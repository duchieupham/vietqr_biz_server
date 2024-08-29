package com.vietqr.org.repository;

import com.vietqr.org.entity.MerchantExportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantExportRepository extends JpaRepository<MerchantExportEntity, String> {
}
