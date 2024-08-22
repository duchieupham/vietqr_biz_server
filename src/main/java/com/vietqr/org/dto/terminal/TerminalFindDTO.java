package com.vietqr.org.dto.terminal;

import javax.validation.constraints.*;

public class TerminalFindDTO {
    /*
    * Search by name, code, address, rawCode, bankId.
    * */

    @NotNull
    @NotBlank
    @NotEmpty
    private String mid;

    @NotNull
    @NotBlank
    @NotEmpty
    private String userId;

    /*
    * Type
    * 0: name
    * 1: code
    * 2: address
    * 3: bank id
    * 4: other
    * */
    private byte type;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 100)
    private String searchTerm;

    public TerminalFindDTO() {
    }

    public TerminalFindDTO(String mid, String userId, byte type, String searchTerm) {
        this.mid = mid;
        this.userId = userId;
        this.type = type;
        this.searchTerm = searchTerm;
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

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public @NotNull @NotEmpty @Size(min = 1, max = 100) String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(@NotNull @NotEmpty @Size(min = 1, max = 100) String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
