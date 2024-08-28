package com.vietqr.org.dto.merchantrole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class MerchantRoleInsertDTO {
    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    @NotNull
    @NotEmpty
    @NotBlank
    private String description;

    @NotNull
    @NotEmpty
    @NotBlank
    private List<String> listPermissionId;

    public MerchantRoleInsertDTO() {
    }

    public MerchantRoleInsertDTO(String name, String description, List<String> listPermissionId) {
        this.name = name;
        this.description = description;
        this.listPermissionId = listPermissionId;
    }

    public @NotNull @NotEmpty @NotBlank String getName() {
        return name;
    }

    public void setName(@NotNull @NotEmpty @NotBlank String name) {
        this.name = name;
    }

    public @NotNull @NotEmpty @NotBlank String getDescription() {
        return description;
    }

    public void setDescription(@NotNull @NotEmpty @NotBlank String description) {
        this.description = description;
    }

    public @NotNull @NotEmpty @NotBlank List<String> getListPermissionId() {
        return listPermissionId;
    }

    public void setListPermissionId(@NotNull @NotEmpty @NotBlank List<String> listPermissionId) {
        this.listPermissionId = listPermissionId;
    }
}
