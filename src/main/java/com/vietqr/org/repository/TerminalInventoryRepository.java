package com.vietqr.org.repository;

import com.vietqr.org.entity.TerminalInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalInventoryRepository extends JpaRepository<TerminalInventoryEntity, String> {
}
