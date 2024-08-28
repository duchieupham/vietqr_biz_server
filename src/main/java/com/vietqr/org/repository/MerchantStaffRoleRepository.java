package com.vietqr.org.repository;

import com.vietqr.org.entity.MerchantStaffRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantStaffRoleRepository extends JpaRepository<MerchantStaffRoleEntity, Long> {
}
