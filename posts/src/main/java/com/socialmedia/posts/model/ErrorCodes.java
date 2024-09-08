package com.socialmedia.posts.model;

public enum ErrorCodes {
    NOT_FOUND("1001"),
    USER_ALREAY_REGISTERED("1002");

    public final String code;

    ErrorCodes(String code) {
        this.code = code;
    }

    public static String valueOfLabel(String label) {
        for (ErrorCodes e : values()) {
            if (e.name().equals(label)) {
                return e.code;
            }
        }
        return null;
    }
}
