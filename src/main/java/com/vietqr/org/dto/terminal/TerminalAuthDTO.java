package com.vietqr.org.dto.terminal;

import javax.validation.constraints.*;

public class TerminalAuthDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    private String id;

    @NotNull
    @NotBlank
    @NotEmpty
    private String userId;

    public TerminalAuthDTO() {
    }

    public TerminalAuthDTO(String id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public @NotNull @NotBlank @NotEmpty String getId() {
        return id;
    }

    public void setId(@NotNull @NotBlank @NotEmpty String id) {
        this.id = id;
    }

    public @NotNull @NotBlank @NotEmpty String getUserId() {
        return userId;
    }

    public void setUserId(@NotNull @NotBlank @NotEmpty String userId) {
        this.userId = userId;
    }
}
