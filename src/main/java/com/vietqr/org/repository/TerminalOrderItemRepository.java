package com.vietqr.org.repository;

import com.vietqr.org.entity.TerminalOrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalOrderItemRepository extends JpaRepository<TerminalOrderItemEntity, String> {
}
