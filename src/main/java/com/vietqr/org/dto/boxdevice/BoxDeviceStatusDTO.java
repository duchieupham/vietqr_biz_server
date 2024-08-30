package com.vietqr.org.dto.boxdevice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BoxDeviceStatusDTO {
    @NotNull
    @NotEmpty
    @NotBlank
    private String userId;

    @NotNull
    @NotEmpty
    @NotBlank
    private String tid;

    @NotNull
    @NotEmpty
    @NotBlank
    private String boxDeviceId;

    public BoxDeviceStatusDTO() {
    }

    public BoxDeviceStatusDTO(String userId, String tid, String boxDeviceId) {
        this.userId = userId;
        this.tid = tid;
        this.boxDeviceId = boxDeviceId;
    }

    public @NotNull @NotEmpty @NotBlank String getUserId() {
        return userId;
    }

    public void setUserId(@NotNull @NotEmpty @NotBlank String userId) {
        this.userId = userId;
    }

    public @NotNull @NotEmpty @NotBlank String getTid() {
        return tid;
    }

    public void setTid(@NotNull @NotEmpty @NotBlank String tid) {
        this.tid = tid;
    }

    public @NotNull @NotEmpty @NotBlank String getBoxDeviceId() {
        return boxDeviceId;
    }

    public void setBoxDeviceId(@NotNull @NotEmpty @NotBlank String boxDeviceId) {
        this.boxDeviceId = boxDeviceId;
    }
}
