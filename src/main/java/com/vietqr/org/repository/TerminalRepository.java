package com.vietqr.org.repository;

import com.vietqr.org.dto.terminal.ITerminalResultOfFindDTO;
import com.vietqr.org.entity.TerminalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TerminalRepository extends JpaRepository<TerminalEntity, String> {
    @Query(value = "SELECT  name, address, bank_id AS bankId, num_of_staff AS numOfStaff"
            + " FROM terminal"
            + " WHERE mid = :mid"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> getListOfTerminal(@Param(value = "mid") String mid);

    @Query(value = "SELECT COUNT(*) FROM terminal WHERE code = :code", nativeQuery = true)
    byte countTerminalByCode(@Param(value = "code") String code);

    @Query(value = "SELECT * FROM terminal WHERE id = :id LIMIT 1", nativeQuery = true)
    TerminalEntity findTerminalById(@Param(value = "id") String id);

    @Query(value = "SELECT name, address, bank_id AS bankId, num_of_staff AS numOfStaff"
            + " FROM terminal WHERE mid = :mid"
            + " AND ( name LIKE CONCAT('%', :searchTerm, '%')"
            + " OR address LIKE CONCAT('%', :searchTerm, '%')"
            + " OR code LIKE CONCAT('%', :searchTerm, '%')"
            + " OR public_id LIKE CONCAT('%', :searchTerm, '%')"
            + " OR ref_id LIKE CONCAT('%', :searchTerm, '%')"
            + " OR bank_id LIKE CONCAT('%', :searchTerm, '%')"
            + " OR qr_box_id LIKE CONCAT('%', :searchTerm, '%')"
            + " OR data1 LIKE CONCAT('%', :searchTerm, '%')"
            + " OR data2 LIKE CONCAT('%', :searchTerm, '%')"
            + " OR trace_transfer LIKE CONCAT('%', :searchTerm, '%'))"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> findTerminals(@Param(value = "mid") String mid, @Param(value = "searchTerm") String searchTerm);

    @Query(value = "SELECT name, address, bank_id AS bankId, num_of_staff AS numOfStaff"
            + " FROM terminal WHERE mid = :mid"
            + " AND name LIKE CONCAT('%', :name, '%')"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> findTerminalsByName(@Param(value = "mid") String mid, @Param(value = "name") String name);

    @Query(value = "SELECT name, address, bank_id AS bankId, num_of_staff AS numOfStaff"
            + " FROM terminal WHERE mid = :mid"
            + " AND code LIKE CONCAT('%', :code, '%')"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> findTerminalsByCode(@Param(value = "mid") String mid, @Param(value = "code") String code);

    @Query(value = "SELECT name, address, bank_id AS bankId, num_of_staff AS numOfStaff"
            + " FROM terminal WHERE mid = :mid"
            + " AND bank_id LIKE CONCAT('%', :bankId, '%')"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> findTerminalsByBankId(@Param(value = "mid") String mid, @Param(value = "bankId") String bankId);

    @Query(value = "SELECT name, address, bank_id AS bankId, num_of_staff AS numOfStaff"
            + " FROM terminal WHERE mid = :mid"
            + " AND address LIKE CONCAT('%', :address, '%')"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> findTerminalsByAddress(@Param(value = "mid") String mid, @Param(value = "address") String address);

    @Transactional
    @Modifying
    @Query(value = "UPDATE terminal"
            + " SET name = :name, address = :address, code = :code, bankId = :bankId"
            + " WHERE id = :id"
            , nativeQuery = true)
    void updateTerminal(@Param("id") String id,
                        @Param("name") String name,
                        @Param("address") String address,
                        @Param("code") String code,
                        @Param("bankId") String bankId);

    @Query(value = "SELECT COUNT(*) FROM terminal t"
            + " INNER JOIN merchant m ON m.id = t.mid"
            + " WHERE t.id = :id"
            + " AND m.user_id = :userId"
            , nativeQuery = true)
    int countTerminalByAuth(@Param("id") String id, @Param("userId") String userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE terminal"
            + " SET status = false"
            + " WHERE id = :id"
            + " AND user_id = :userId"
            , nativeQuery = true)
    void deleteTerminal(@Param("id") String id, @Param("userId") String userId);
}
