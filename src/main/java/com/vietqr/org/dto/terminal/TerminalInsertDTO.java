package com.vietqr.org.dto.terminal;

import javax.validation.constraints.*;

public class TerminalInsertDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 200)
    private String address;

    @NotNull
    @NotBlank
    @NotEmpty
    private String mid;

    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 15)
    private String code;

    @NotNull
    @NotBlank
    @NotEmpty
    private String bankId;

    public TerminalInsertDTO() {
    }

    public @NotNull @NotBlank @NotEmpty @Size(min = 1, max = 100) String getName() {
        return name;
    }

    public void setName(@NotNull @NotBlank @NotEmpty @Size(min = 1, max = 100) String name) {
        this.name = name;
    }

    public @NotNull @NotBlank @NotEmpty @Size(min = 1, max = 200) String getAddress() {
        return address;
    }

    public void setAddress(@NotNull @NotBlank @NotEmpty @Size(min = 1, max = 200) String address) {
        this.address = address;
    }

    public @NotNull @NotBlank @NotEmpty String getMid() {
        return mid;
    }

    public void setMid(@NotNull @NotBlank @NotEmpty String mid) {
        this.mid = mid;
    }

    public @NotBlank @NotEmpty @Size(min = 1, max = 15) String getCode() {
        return code;
    }

    public void setCode(@NotBlank @NotEmpty @Size(min = 1, max = 15) String code) {
        this.code = code;
    }

    public @NotNull @NotBlank @NotEmpty String getBankId() {
        return bankId;
    }

    public void setBankId(@NotNull @NotBlank @NotEmpty String bankId) {
        this.bankId = bankId;
    }
}
