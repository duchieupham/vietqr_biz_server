package com.vietqr.org.repository;

import com.vietqr.org.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query(value = "SELECT * FROM customer WHERE id = :id", nativeQuery = true)
    Optional<CustomerEntity> findCustomerById(@Param("id") String id);
}
