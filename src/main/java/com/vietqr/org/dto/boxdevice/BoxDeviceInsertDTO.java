package com.vietqr.org.dto.boxdevice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BoxDeviceInsertDTO {
    @NotEmpty
    @NotBlank
    private String deviceCode;

    @NotNull
    @NotEmpty
    @NotBlank
    private String certificate;

    @NotNull
    @NotEmpty
    @NotBlank
    private String userId;

    @NotNull
    @NotEmpty
    @NotBlank
    private String tid;

    public BoxDeviceInsertDTO() {
    }

    public BoxDeviceInsertDTO(String deviceCode, String certificate, String userId, String tid) {
        this.deviceCode = deviceCode;
        this.certificate = certificate;
        this.userId = userId;
        this.tid = tid;
    }

    public @NotEmpty @NotBlank String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(@NotEmpty @NotBlank String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public @NotNull @NotEmpty @NotBlank String getCertificate() {
        return certificate;
    }

    public void setCertificate(@NotNull @NotEmpty @NotBlank String certificate) {
        this.certificate = certificate;
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
}
