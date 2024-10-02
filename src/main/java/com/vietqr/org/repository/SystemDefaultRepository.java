package com.vietqr.org.repository;

import com.vietqr.org.entity.SystemDefaultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SystemDefaultRepository extends JpaRepository<SystemDefaultEntity, String> {
    @Query(value = "SELECT vat FROM system_default WHERE id = '361432e0-6e24-481b-8839-b4349c517526'", nativeQuery = true)
    double getSystemVat();
}
