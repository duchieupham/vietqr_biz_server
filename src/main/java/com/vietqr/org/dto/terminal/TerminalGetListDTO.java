package com.vietqr.org.dto.terminal;

import javax.validation.constraints.*;

public class TerminalGetListDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    private String mid;

    @NotNull
    @NotBlank
    @NotEmpty
    private String userId;

    public TerminalGetListDTO() {
    }

    public TerminalGetListDTO(String mid, String userId) {
        this.mid = mid;
        this.userId = userId;
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
}
