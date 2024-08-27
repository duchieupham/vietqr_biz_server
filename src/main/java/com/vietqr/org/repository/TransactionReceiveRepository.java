package com.vietqr.org.repository;

import com.vietqr.org.entity.TransactionReceiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionReceiveRepository extends JpaRepository<TransactionReceiveEntity, String> {
    @Query(value = "SELECT * FROM transaction_receive"
            + " WHERE terminal_code = :terminalCode"
            , nativeQuery = true)
    List<TransactionReceiveEntity> findAllTransactionReceiveByTerminalCode(@Param(value = "terminalCode") String terminalCode);

    @Query(value = "SELECT * FROM transaction_receive"
            + " WHERE id = :id LIMIT 1"
            , nativeQuery = true)
    TransactionReceiveEntity findTransactionReceiveById(@Param(value = "id") String id);

    @Query(value = "SELECT * FROM transaction_receive"
            + " WHERE mid = :mid"
            , nativeQuery = true)
    List<TransactionReceiveEntity> findAllTransactionReceiveByMid(@Param(value = "mid") String mid);
}
