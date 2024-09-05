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
    private String tid;

    public BoxDeviceInsertDTO() {
    }

    public BoxDeviceInsertDTO(String deviceCode, String certificate, String tid) {
        this.deviceCode = deviceCode;
        this.certificate = certificate;
        this.tid = tid;
    }

    public @NotEmpty @NotBlank String getDeviceCode() {
        return deviceCode.trim();
    }

    public void setDeviceCode(@NotEmpty @NotBlank String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public @NotNull @NotEmpty @NotBlank String getCertificate() {
        return certificate.trim();
    }

    public void setCertificate(@NotNull @NotEmpty @NotBlank String certificate) {
        this.certificate = certificate;
    }

    public @NotNull @NotEmpty @NotBlank String getTid() {
        return tid.trim();
    }

    public void setTid(@NotNull @NotEmpty @NotBlank String tid) {
        this.tid = tid;
    }
}
