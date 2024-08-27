package com.vietqr.org.dto.terminal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TerminalTransferDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    private String mid;

    @NotNull
    @NotBlank
    @NotEmpty
    private String userId;

    @NotNull
    @NotBlank
    @NotEmpty
    private String from;

    @NotNull
    @NotBlank
    @NotEmpty
    private String to;

    public TerminalTransferDTO(String mid, String userId, String from, String to) {
        this.mid = mid;
        this.userId = userId;
        this.from = from;
        this.to = to;
    }

    public @NotNull @NotBlank @NotEmpty String getMid() {
        return mid;
    }

    public void setMid(@NotNull @NotBlank @NotEmpty String mid) {
        this.mid = mid;
    }

    public @NotNull @NotBlank @NotEmpty String getUserId() {
        return userId;
    }

    public void setUserId(@NotNull @NotBlank @NotEmpty String userId) {
        this.userId = userId;
    }

    public @NotNull @NotBlank @NotEmpty String getFrom() {
        return from;
    }

    public void setFrom(@NotNull @NotBlank @NotEmpty String from) {
        this.from = from;
    }

    public @NotNull @NotBlank @NotEmpty String getTo() {
        return to;
    }

    public void setTo(@NotNull @NotBlank @NotEmpty String to) {
        this.to = to;
    }
}
