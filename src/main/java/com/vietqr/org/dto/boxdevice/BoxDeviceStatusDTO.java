package com.vietqr.org.dto.boxdevice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BoxDeviceStatusDTO {
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

    public BoxDeviceStatusDTO(String tid, String boxDeviceId) {
        this.tid = tid;
        this.boxDeviceId = boxDeviceId;
    }

    public @NotNull @NotEmpty @NotBlank String getTid() {
        return tid.trim();
    }

    public void setTid(@NotNull @NotEmpty @NotBlank String tid) {
        this.tid = tid;
    }

    public @NotNull @NotEmpty @NotBlank String getBoxDeviceId() {
        return boxDeviceId.trim();
    }

    public void setBoxDeviceId(@NotNull @NotEmpty @NotBlank String boxDeviceId) {
        this.boxDeviceId = boxDeviceId;
    }
}
