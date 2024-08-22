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
    @Size(min = 15, max = 15)
    private String rawCode;

    @NotBlank
    @NotEmpty
    private String bankId;

    @NotNull
    @NotBlank
    @NotEmpty
    private String userId;

    public TerminalUpdateDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRawCode() {
        return rawCode;
    }

    public void setRawCode(String rawCode) {
        this.rawCode = rawCode;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
