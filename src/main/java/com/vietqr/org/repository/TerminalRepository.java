package com.vietqr.org.repository;

import com.vietqr.org.entity.TerminalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerminalRepository extends JpaRepository<TerminalEntity, String> {
    @Query(value =  "SELECT * FROM terminal WHERE mid = :mid AND user_id = :userId", nativeQuery = true)
    List<TerminalEntity> getListOfTerminal(@Param(value = "mid")String mid, @Param(value = "userId")String userId);

    @Query(value =  "SELECT * FROM terminal WHERE id = :id LIMIT 1", nativeQuery = true)
    TerminalEntity getTerminalById(@Param(value = "id")String id);
}
