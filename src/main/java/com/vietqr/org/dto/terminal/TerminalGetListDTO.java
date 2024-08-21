package com.vietqr.org.dto.terminal;

public class TerminalGetListDTO {
    private String mid;
    private String userId;

    public TerminalGetListDTO() {
    }

    public TerminalGetListDTO(String mid, String userId) {
        this.mid = mid;
        this.userId = userId;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
