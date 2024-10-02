package com.vietqr.org.repository;

import com.vietqr.org.dto.terminalorder.ITerminalOrderInfoDTO;
import com.vietqr.org.entity.TerminalOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface TerminalOrderRepository extends JpaRepository<TerminalOrderEntity, String> {
    @Query(value = "SELECT * FROM terminal_order WHERE id = :id LIMIT 1", nativeQuery = true)
    Optional<TerminalOrderEntity> findTerminalOderEntityById(@Param("id") String id);

    @Query(value = "SELECT tid AS tid, customer_id AS customerId, "
            + "staff_id AS staffId, total_amount AS totalAmount, "
            + "vat_amount AS vatAmount, time_created AS timeCreated, "
            + "time_paid AS timePaid, status AS status "
            + "FROM terminal_order "
            + "WHERE id = :id LIMIT 1"
            , nativeQuery = true)
    Optional<ITerminalOrderInfoDTO> findTerminalOderInfoById(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE terminal_order SET status = :status "
            + "WHERE id = :id"
            , nativeQuery = true)
    void updateTerminalOderStatusById(@Param("id") String id, @Param("status") int status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE terminal_order SET time_paid = :timePaid, status = 1 "
            + "WHERE id = :id"
            , nativeQuery = true)
    void updateTerminalOderPaidById(@Param("id") String id, @Param("timePaid") long timePaid);
}
