package com.vietqr.org.dto.permission;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PermissionInsertDTO {
    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    @NotNull
    @NotEmpty
    @NotBlank
    private String description;

    @NotNull
    private int category;

    @NotNull
    @NotEmpty
    @NotBlank
    private String color;

    public PermissionInsertDTO() {
    }

    public PermissionInsertDTO(String name, String description, int category, String color) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.color = color;
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

    @NotNull
    public int getCategory() {
        return category;
    }

    public void setCategory(@NotNull int category) {
        this.category = category;
    }

    public @NotNull @NotEmpty @NotBlank String getColor() {
        return color;
    }

    public void setColor(@NotNull @NotEmpty @NotBlank String color) {
        this.color = color;
    }
}
