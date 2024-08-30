package com.vietqr.org.repository;

import com.vietqr.org.entity.BoxDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface BoxDeviceRepository extends JpaRepository<BoxDeviceEntity, String> {
    @Query(value = "SELECT * FROM (SELECT box_device_id FROM terminal WHERE mid = :mid) AS t" +
            " INNER JOIN box_device AS b" +
            " ON t.box_device_id = b.id"
            , nativeQuery = true)
    Optional<List<BoxDeviceEntity>> findBoxDeviceByMid(@Param(value = "mid") String mid);

    @Query(value = "SELECT * FROM box_device WHERE id = :id", nativeQuery = true)
    Optional<BoxDeviceEntity> findBoxDeviceById(@Param(value = "id") String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE box_device SET status = :value  WHERE id = :id", nativeQuery = true)
    void updateBoxDeviceStatusById(@Param(value = "id") String id, @Param(value = "value") int value);
}
