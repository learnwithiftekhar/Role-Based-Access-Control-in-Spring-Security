package com.learnwithiftekhar.rbacDemo.dto;

import jakarta.validation.constraints.NotBlank;

public class RoleToUserDto {
    @NotBlank(message = "Role name is required")
    private String roleName;

    // Getter and setter
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
