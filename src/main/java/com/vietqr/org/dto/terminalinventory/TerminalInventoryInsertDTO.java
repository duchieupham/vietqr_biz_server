package com.vietqr.org.dto.terminalinventory;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TerminalInventoryInsertDTO {
    @NotBlank
    private String tid;

    @NotBlank
    private String productId;

    @NotNull
    private int quantity;

    @NotNull
    private long exp;

    @NotNull
    private long mfg;

    public TerminalInventoryInsertDTO() {
    }

    public @NotBlank String getTid() {
        return tid.trim();
    }

    public void setTid(@NotBlank String tid) {
        this.tid = tid;
    }

    public @NotBlank String getProductId() {
        return productId.trim();
    }

    public void setProductId(@NotBlank String productId) {
        this.productId = productId;
    }

    @NotNull
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull int quantity) {
        this.quantity = quantity;
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
