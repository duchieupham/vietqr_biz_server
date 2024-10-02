package com.vietqr.org.dto.terminalorder;

import com.vietqr.org.dto.terminalorderitem.TerminalOrderItemInsertDTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class TerminalOrderInsertDTO {
    private long discount;
    @NotEmpty
    private String code;
    @NotEmpty
    private String staffId;
    @NotEmpty
    private String customerId;
    @NotEmpty
    private String tid;
    @NotNull
    private List<TerminalOrderItemInsertDTO> terminalOrderItemList;

    public TerminalOrderInsertDTO() {
    }

    public TerminalOrderInsertDTO(long discount, String code, String staffId, String customerId, String tid, List<TerminalOrderItemInsertDTO> terminalOrderItemList) {
        this.discount = discount;
        this.code = code;
        this.staffId = staffId;
        this.customerId = customerId;
        this.tid = tid;
        this.terminalOrderItemList = terminalOrderItemList;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public List<TerminalOrderItemInsertDTO> getTerminalOrderItemList() {
        return terminalOrderItemList;
    }

    public void setTerminalOrderItemList(List<TerminalOrderItemInsertDTO> terminalOrderItemList) {
        this.terminalOrderItemList = terminalOrderItemList;
    }
}
