package com.vietqr.org.dto.boxdevice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BoxDeviceFindMidDTO {
    @NotNull
    @NotEmpty
    @NotBlank
    private String userId;

    @NotNull
    @NotEmpty
    @NotBlank
    private String mid;

    public BoxDeviceFindMidDTO() {
    }

    public BoxDeviceFindMidDTO(String userId, String mid) {
        this.userId = userId;
        this.mid = mid;
    }

    public @NotNull @NotEmpty @NotBlank String getUserId() {
        return userId;
    }

    public void setUserId(@NotNull @NotEmpty @NotBlank String userId) {
        this.userId = userId;
    }

    public @NotNull @NotEmpty @NotBlank String getMid() {
        return mid;
    }

    public void setMid(@NotNull @NotEmpty @NotBlank String mid) {
        this.mid = mid;
    }
}
