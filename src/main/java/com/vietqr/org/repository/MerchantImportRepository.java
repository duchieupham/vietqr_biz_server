package com.vietqr.org.repository;

import com.vietqr.org.entity.MerchantImportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantImportRepository extends JpaRepository<MerchantImportEntity, String> {
}
