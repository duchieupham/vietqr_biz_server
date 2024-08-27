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
    @Query(value = "SELECT name, address, bank_id AS bankId, num_of_staff AS numOfStaff"
            + " FROM terminal"
            + " WHERE mid = :mid"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> getListOfTerminal(@Param(value = "mid") String mid);

    @Query(value = "SELECT * FROM terminal WHERE mid = :mid", nativeQuery = true)
    List<TerminalEntity> findTerminalsByMid(@Param(value = "mid") String mid);

    @Query(value = "SELECT COUNT(*) FROM terminal WHERE code = :code", nativeQuery = true)
    int countTerminalByCode(@Param(value = "code") String code);

    @Query(value = "SELECT * FROM terminal WHERE id = :id LIMIT 1", nativeQuery = true)
    TerminalEntity findTerminalById(@Param(value = "id") String id);

    @Query(value = "SELECT name, address, bank_id AS bankId, num_of_staff AS numOfStaff"
            + " FROM terminal WHERE mid = :mid"
            + " AND ( LOWER(name) LIKE CONCAT('%', :searchTerm, '%')"
            + " OR LOWER(address) LIKE CONCAT('%', :searchTerm, '%')"
            + " OR LOWER(code) LIKE CONCAT('%', :searchTerm, '%')"
            + " OR LOWER(public_id) LIKE CONCAT('%', :searchTerm, '%')"
            + " OR LOWER(ref_id) LIKE CONCAT('%', :searchTerm, '%')"
            + " OR LOWER(bank_id) LIKE CONCAT('%', :searchTerm, '%')"
            + " OR LOWER(qr_box_id) LIKE CONCAT('%', :searchTerm, '%')"
            + " OR LOWER(data1) LIKE CONCAT('%', :searchTerm, '%')"
            + " OR LOWER(data2) LIKE CONCAT('%', :searchTerm, '%')"
            + " OR LOWER(trace_transfer) LIKE CONCAT('%', :searchTerm, '%'))"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> findTerminals(@Param(value = "mid") String mid, @Param(value = "searchTerm") String searchTerm);

    @Query(value = "SELECT name, address, bank_id AS bankId, num_of_staff AS numOfStaff"
            + " FROM terminal WHERE mid = :mid"
            + " AND LOWER(name) LIKE CONCAT('%', :name, '%')"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> findTerminalsByName(@Param(value = "mid") String mid, @Param(value = "name") String name);

    @Query(value = "SELECT name, address, bank_id AS bankId, num_of_staff AS numOfStaff"
            + " FROM terminal WHERE mid = :mid"
            + " AND LOWER(code) LIKE CONCAT('%', :code, '%')"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> findTerminalsByCode(@Param(value = "mid") String mid, @Param(value = "code") String code);

    @Query(value = "SELECT name, address, bank_id AS bankId, num_of_staff AS numOfStaff"
            + " FROM terminal WHERE mid = :mid"
            + " AND LOWER(bank_id) LIKE CONCAT('%', :bankId, '%')"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> findTerminalsByBankId(@Param(value = "mid") String mid, @Param(value = "bankId") String bankId);

    @Query(value = "SELECT name, address, bank_id AS bankId, num_of_staff AS numOfStaff"
            + " FROM terminal WHERE mid = :mid"
            + " AND LOWER(address) LIKE CONCAT('%', :address, '%')"
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
            + " SET status = :status, time_updated_status = :timeUpdatedStatus"
            + " WHERE id = :id"
            , nativeQuery = true)
    void updateTerminalStatusById(@Param("id") String id,
                                  @Param("status") boolean status,
                                  @Param("timeUpdatedStatus") long timeUpdatedStatus);

    @Query(value = "SELECT t.name, t.address, t.bank_id AS bankId, t.num_of_staff AS numOfStaff FROM terminal t"
            + " INNER JOIN merchant m ON m.id = t.mid"
            + " WHERE t.mid = :mid"
            + " AND m.user_id > :userId"
            + " AND t.time_updated_status > :timeLine"
            , nativeQuery = true)
    List<ITerminalResultOfFindDTO> getListOfTerminalDeleted(@Param(value = "mid") String mid,
                                                            @Param(value = "userId") String userId,
                                                            @Param("timeLine") long timeLine);

    @Query(value = "SELECT COUNT(*) FROM terminal WHERE mid = :mid", nativeQuery = true)
    int countTerminalsByMid(@Param("mid") String mid);

    @Query(value = "SELECT COUNT(*) FROM terminal WHERE public_id = :publicId", nativeQuery = true)
    int countTerminalByPublicId(@Param(value = "publicId") String publicId);

    @Query(value = "SELECT COUNT(*) FROM terminal WHERE id = :id", nativeQuery = true)
    int countTerminalById(@Param(value = "id") String id);
}
