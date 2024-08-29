package com.vietqr.org.repository;

import com.vietqr.org.entity.BoxDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoxDeviceRepository extends JpaRepository<BoxDeviceEntity, String> {
    @Query(value = "SELECT * FROM (SELECT qr_box_id FROM terminal WHERE mid = :mid) AS t" +
            " INNER JOIN box_device AS b" +
            " ON t.qr_box_id = b.id"
            , nativeQuery = true)
    Optional<List<BoxDeviceEntity>> findBoxDeviceByMid(@Param(value = "mid") String mid);
}
