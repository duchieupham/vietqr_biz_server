package com.vietqr.org.repository;

import com.vietqr.org.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PermissionRepository extends JpaRepository<PermissionEntity, String> {
    @Query(value = "SELECT * FROM permission WHERE id = :id LIMIT 1", nativeQuery = true)
    PermissionEntity findPermissionById(@Param(value = "id") String id);
}
