package com.vietqr.org.repository;

import com.vietqr.org.dto.terminalinventory.ITerminalInventoryDTO;
import com.vietqr.org.entity.TerminalInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TerminalInventoryRepository extends JpaRepository<TerminalInventoryEntity, String> {
    @Query(value = "SELECT EXISTS(SELECT 1 FROM terminal_inventory WHERE id = :id)", nativeQuery = true)
    int existsTerminalInventoryById(@Param(value = "id") String id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE terminal_inventory SET quantity = :quantity, time_updated = :timeUpdated WHERE id = :id", nativeQuery = true)
    void updateQuantityTerminalInventoryById(
            @Param(value = "id") String id,
            @Param(value = "quantity") int quantity,
            @Param(value = "timeUpdated") long timeUpdated
    );

    @Query(value = "SELECT tid, product_id AS productId, quantity, exp, mfg"
            + " FROM terminal_inventory"
            + " WHERE id = :id LIMIT 1"
            , nativeQuery = true)
    ITerminalInventoryDTO findTerminalInventoryById(@Param(value = "id") String id);

    @Query(value = "SELECT tid, product_id AS productId, quantity, exp, mfg"
            + " FROM terminal_inventory"
            + " WHERE tid = tid"
            , nativeQuery = true)
    List<ITerminalInventoryDTO> findTerminalInventoryByTid(@Param(value = "tid") String tid);

    @Modifying
    @Transactional
    @Query(value = "UPDATE terminal_inventory SET exp = :exp, mfg = :mfg, time_updated = :timeUpdated WHERE id = :id", nativeQuery = true)
    void updateExpTerminalInventoryById(
            @Param(value = "id") String id,
            @Param(value = "exp") long exp,
            @Param(value = "mfg") long mfg,
            @Param(value = "timeUpdated") long timeUpdated
    );
}
