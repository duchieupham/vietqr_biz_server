package com.vietqr.org.dto.terminal;

import javax.validation.constraints.*;

public class TerminalUpdateDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    private String id;

    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 200)
    private String address;

    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 15)
    private String code;

    @NotBlank
    @NotEmpty
    private String bankId;

    @NotNull
    @NotBlank
    @NotEmpty
    private String userId;

    public TerminalUpdateDTO() {
    }

    public @NotNull @NotBlank @NotEmpty String getId() {
        return id;
    }

    public void setId(@NotNull @NotBlank @NotEmpty String id) {
        this.id = id;
    }

    public @NotBlank @NotEmpty @Size(min = 1, max = 100) String getName() {
        return name;
    }

    public void setName(@NotBlank @NotEmpty @Size(min = 1, max = 100) String name) {
        this.name = name;
    }

    public @NotBlank @NotEmpty @Size(min = 1, max = 200) String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank @NotEmpty @Size(min = 1, max = 200) String address) {
        this.address = address;
    }

    public @NotBlank @NotEmpty @Size(min = 1, max = 15) String getCode() {
        return code;
    }

    public void setCode(@NotBlank @NotEmpty @Size(min = 1, max = 15) String code) {
        this.code = code;
    }

    public @NotBlank @NotEmpty String getBankId() {
        return bankId;
    }

    public void setBankId(@NotBlank @NotEmpty String bankId) {
        this.bankId = bankId;
    }

    public @NotNull @NotBlank @NotEmpty String getUserId() {
        return userId;
    }

    public void setUserId(@NotNull @NotBlank @NotEmpty String userId) {
        this.userId = userId;
    }
}
