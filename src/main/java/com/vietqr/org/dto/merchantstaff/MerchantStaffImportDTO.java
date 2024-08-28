package com.vietqr.org.dto.merchantstaff;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MerchantStaffImportDTO {
    @NotNull
    @NotEmpty
    @NotBlank
    private String id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String userId;

    /*
    * Type
    * 0: id -> mid
    * 1: id -> tid
    * */
    @NotNull
    private int type;

    public MerchantStaffImportDTO() {
    }

    public MerchantStaffImportDTO(String id, String userId, int type) {
        this.id = id;
        this.userId = userId;
        this.type = type;
    }

    public @NotNull @NotEmpty @NotBlank String getId() {
        return id;
    }

    public void setId(@NotNull @NotEmpty @NotBlank String id) {
        this.id = id;
    }

    public @NotNull @NotEmpty @NotBlank String getUserId() {
        return userId;
    }

    public void setUserId(@NotNull @NotEmpty @NotBlank String userId) {
        this.userId = userId;
    }

    @NotNull
    public int getType() {
        return type;
    }

    public void setType(@NotNull int type) {
        this.type = type;
    }
}
