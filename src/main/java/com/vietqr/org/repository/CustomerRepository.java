package com.vietqr.org.repository;

import com.vietqr.org.entity.MerchantCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<MerchantCustomerEntity, Long> {
    @Query(value = "SELECT * FROM merchant_customer WHERE id = :id LIMIT 1", nativeQuery = true)
    Optional<MerchantCustomerEntity> findCustomerById(@Param("id") String id);
    @Query(value = "SELECT * FROM merchant_customer", nativeQuery = true)
    List<MerchantCustomerEntity> findAllCustomer();
}
