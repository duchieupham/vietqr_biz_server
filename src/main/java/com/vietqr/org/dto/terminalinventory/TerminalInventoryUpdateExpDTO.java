package com.vietqr.org.dto.terminalinventory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TerminalInventoryUpdateExpDTO {
    @NotBlank
    private String id;

    @NotNull
    private long exp;

    @NotNull
    private long mfg;

    public TerminalInventoryUpdateExpDTO() {
    }

    public @NotBlank String getId() {
        return id;
    }

    public void setId(@NotBlank String id) {
        this.id = id;
    }

    @NotNull
    public long getExp() {
        return exp;
    }

    public void setExp(@NotNull long exp) {
        this.exp = exp;
    }

    @NotNull
    public long getMfg() {
        return mfg;
    }

    public void setMfg(@NotNull long mfg) {
        this.mfg = mfg;
    }
}
