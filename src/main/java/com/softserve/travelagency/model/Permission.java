package com.softserve.travelagency.model;

public enum Permission {
    DEVELOPERS_USER("developers:user"),
    DEVELOPERS_ADMIN("developers:admin");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
