package com.vietqr.org.repository;

import com.vietqr.org.dto.productprice.IProductPriceDTO;
import com.vietqr.org.entity.ProductPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, String> {
    @Query(value = "SELECT EXISTS(SELECT 1 FROM product_price WHERE id = :id)", nativeQuery = true)
    int existsProductPriceById(@Param(value = "id") String id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product_price SET amount = :amount, time_updated = :timeUpdated WHERE id = :id", nativeQuery = true)
    void updateAmountProductPriceById(
            @Param(value = "id") String id,
            @Param(value = "amount") int amount,
            @Param(value = "timeUpdated") long timeUpdated
    );

    @Query(value = "SELECT product_id AS productId, amount, trace_transfer AS traceTransfer, data1, data2"
            + " FROM product_price"
            + " WHERE id = :id"
            , nativeQuery = true)
    IProductPriceDTO findProductPriceById(@Param(value = "id") String id);

    @Query(value = "SELECT product_id AS productId, amount, trace_transfer AS traceTransfer, data1, data2"
            + " FROM product_price"
            + " WHERE product_id = productId"
            , nativeQuery = true)
    IProductPriceDTO findProductPriceByProductId(@Param(value = "productId") String productId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product_price SET data1 = :data1, time_updated = :timeUpdated WHERE id = :id", nativeQuery = true)
    void updateData1ProductPriceById(
            @Param(value = "id") String id,
            @Param(value = "data1") String data1,
            @Param(value = "timeUpdated") long timeUpdated
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE product_price SET data2 = :data2, time_updated = :timeUpdated WHERE id = :id", nativeQuery = true)
    void updateData2ProductPriceById(
            @Param(value = "id") String id,
            @Param(value = "data2") String data2,
            @Param(value = "timeUpdated") long timeUpdated
    );
}
