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

    public TerminalTransferDTO(String mid, String from, String to) {
        this.mid = mid.trim();
        this.from = from.trim();
        this.to = to.trim();
    }

    public @NotNull @NotBlank @NotEmpty String getMid() {
        return mid;
    }

    public void setMid(@NotNull @NotBlank @NotEmpty String mid) {
        this.mid = mid.trim();
    }

    public @NotNull @NotBlank @NotEmpty String getFrom() {
        return from;
    }

    public void setFrom(@NotNull @NotBlank @NotEmpty String from) {
        this.from = from.trim();
    }

    public @NotNull @NotBlank @NotEmpty String getTo() {
        return to;
    }

    public void setTo(@NotNull @NotBlank @NotEmpty String to) {
        this.to = to.trim();
    }
}
