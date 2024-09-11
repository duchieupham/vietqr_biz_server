package com.vietqr.org.dto.terminalinventory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TerminalInventoryUpdateQuantityDTO {
    @NotBlank
    private String id;

    @NotNull
    private int quantity;

    public TerminalInventoryUpdateQuantityDTO() {}

    public @NotBlank String getId() {
        return id.trim();
    }

    public void setId(@NotBlank String id) {
        this.id = id;
    }

    @NotNull
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull int quantity) {
        this.quantity = quantity;
    }
}
