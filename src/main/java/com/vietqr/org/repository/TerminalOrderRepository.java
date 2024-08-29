package com.vietqr.org.repository;

import com.vietqr.org.entity.TerminalOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TerminalOrderRepository extends JpaRepository<TerminalOrderEntity, Long> {
    @Query(value = "SELECT * FROM terminal_oder WHERE id = :id LIMIT 1", nativeQuery = true)
    Optional<TerminalOrderEntity> findTerminalOderEntityById(@Param("id") String id);
}
