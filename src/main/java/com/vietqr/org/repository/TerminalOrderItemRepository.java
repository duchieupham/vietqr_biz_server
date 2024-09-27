package com.vietqr.org.repository;

import com.vietqr.org.dto.terminalorderitem.ITerminalOrderItemDTO;
import com.vietqr.org.dto.terminalorderitem.ITerminalOrderItemIdDTO;
import com.vietqr.org.entity.TerminalOrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TerminalOrderItemRepository extends JpaRepository<TerminalOrderItemEntity, String> {
    @Query(value = "SELECT product_id AS productId, "
            + "order_id AS orderId, "
            + "quantity AS quantity, "
            + "amount AS amount, "
            + "total_amount AS totalAmount, "
            + "vat AS vat, "
            + "vat_amount AS vatAmount, "
            + "discount_amount AS discountAmount "
            + "FROM terminal_order_item "
            + "WHERE id = :id "
            , nativeQuery = true)
    Optional<ITerminalOrderItemIdDTO> findTerminalOrderItemById(@Param("id") String id);

    @Query(value = "SELECT a.product_id AS productId, "
            + "a.quantity AS quantity, "
            + "a.amount AS amount, "
            + "a.total_amount AS totalAmount, "
            + "a.vat AS vat, "
            + "a.vat_amount AS vatAmount, "
            + "a.discount_amount AS discountAmount, "
            + "b.img_id AS imgId, "
            + "b.unit AS unit, "
            + "b.name AS name "
            + "FROM terminal_order_item a "
            + "INNER JOIN (SELECT id, img_id, unit, name FROM merchant_product) b "
            + "ON a.product_id = b.id "
            + "WHERE order_id = :orderId "
            , nativeQuery = true)
    List<ITerminalOrderItemDTO> findTerminalOrderItemByOrderId(@Param("orderId") String orderId);
}
