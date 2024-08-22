package com.vietqr.org.repository;

import com.vietqr.org.dto.terminal.ITerminalResultOfFindDTO;
import com.vietqr.org.entity.TerminalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerminalRepository extends JpaRepository<TerminalEntity, String> {
    @Query(value = "SELECT * FROM terminal"
            + " WHERE mid = :mid"
            + " AND user_id = :userId"
            , nativeQuery = true)
    List<TerminalEntity> getListOfTerminal(@Param(value = "mid") String mid, @Param(value = "userId") String userId);

    @Query(value = "SELECT * FROM terminal WHERE id = :id LIMIT 1", nativeQuery = true)
    TerminalEntity findTerminalById(@Param(value = "id") String id);

    @Query(value = "SELECT t.name AS name, t.address AS address, t.bank_id AS bankId, t.num_of_staff AS numOfStaff"
            + " FROM terminal t WHERE mid = :mid"
            + " AND ( name LIKE CONCAT('%', :searchTerm, '%')"
            + " OR address LIKE CONCAT('%', :searchTerm, '%')"
            + " OR code LIKE CONCAT('%', :searchTerm, '%')"
            + " OR raw_code LIKE CONCAT('%', :searchTerm, '%')"
            + " OR public_id LIKE CONCAT('%', :searchTerm, '%')"
            + " OR ref_id LIKE CONCAT('%', :searchTerm, '%')"
            + " OR bank_id LIKE CONCAT('%', :searchTerm, '%')"
            + " OR qr_box_id LIKE CONCAT('%', :searchTerm, '%')"
            + " OR data1 LIKE CONCAT('%', :searchTerm, '%')"
            + " OR data2 LIKE CONCAT('%', :searchTerm, '%')"
            + " OR trace_transfer LIKE CONCAT('%', :searchTerm, '%'))"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> findTerminals(@Param(value = "mid") String mid, @Param(value = "searchTerm") String searchTerm);

    @Query(value = "SELECT t.name AS name, t.address AS address, t.bank_id AS bankId, t.num_of_staff AS numOfStaff"
            + " FROM terminal t WHERE mid = :mid"
            + " AND name LIKE CONCAT('%', :name, '%')"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> findTerminalsByName(@Param(value = "mid") String mid, @Param(value = "name") String name);

    @Query(value = "SELECT t.name AS name, t.address AS address, t.bank_id AS bankId, t.num_of_staff AS numOfStaff"
            + " FROM terminal t WHERE mid = :mid"
            + " AND raw_code LIKE CONCAT('%', :rawCode, '%')",
            nativeQuery = true)
    List<ITerminalResultOfFindDTO> findTerminalsByRawCode(@Param(value = "mid") String mid, @Param(value = "rawCode") String rawCode);

    @Query(value = "SELECT t.name AS name, t.address AS address, t.bank_id AS bankId, t.num_of_staff AS numOfStaff"
            + " FROM terminal t WHERE mid = :mid"
            + " AND code LIKE CONCAT('%', :code, '%')"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> findTerminalsByCode(@Param(value = "mid") String mid, @Param(value = "code") String code);

    @Query(value = "SELECT t.name AS name, t.address AS address, t.bank_id AS bankId, t.num_of_staff AS numOfStaff"
            + " FROM terminal t WHERE mid = :mid"
            + " AND bank_id LIKE CONCAT('%', :bankId, '%')"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> findTerminalsByBankId(@Param(value = "mid") String mid, @Param(value = "bankId") String bankId);

    @Query(value = "SELECT t.name AS name, t.address AS address, t.bank_id AS bankId, t.num_of_staff AS numOfStaff"
            + " FROM terminal t WHERE mid = :mid"
            + " AND address LIKE CONCAT('%', :address, '%')"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> findTerminalsByAddress(@Param(value = "mid") String mid, @Param(value = "address") String address);
}
