package com.vietqr.org.repository;

import com.vietqr.org.dto.productprice.IProductPriceDTO;
import com.vietqr.org.dto.productpricehistory.IProductPriceHistoryDTO;
import com.vietqr.org.dto.productpricehistory.IProductPriceHistoryStatisticDTO;
import com.vietqr.org.entity.ProductPriceHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductPriceHistoryRepository extends JpaRepository<ProductPriceHistoryEntity, String> {
    @Query(value = "SELECT i.timeline AS timeline, i.amount AS amount FROM ("
            + " SELECT amount, time_created AS timeline FROM product_price_history WHERE product_id = :productId"
            + " UNION ALL"
            + " SELECT amount, time_updated AS timeline FROM product_price WHERE product_id = :productId"
            + " ) AS i"
            + " ORDER BY i.timeline ASC"
            , nativeQuery = true)
    List<IProductPriceHistoryStatisticDTO> statisticProductPriceHistory(@Param(value = "productId") String productId);

    @Query(value = "SELECT product_id AS productId, amount AS amount, time_created AS timeCreated"
            + " FROM product_price_history"
            + " WHERE id = :id LIMIT 1"
            , nativeQuery = true)
    Optional<IProductPriceHistoryDTO> findProductPriceHistoryById(@Param(value = "id") String id);
}
