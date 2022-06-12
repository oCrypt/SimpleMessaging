package me.cahrypt.simplemessaging.permission;

public enum Permissions {

    USER("simplemessaging.user"),
    ADMIN("simplemessaging.admin");

    private final String perm;

    Permissions(String perm) {
        this.perm = perm;
    }

    public String getPerm() {
        return perm;
    }
}
