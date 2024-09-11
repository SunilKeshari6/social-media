package com.socialmedia.posts.model;

public enum ErrorCodes {
    NOT_FOUND("1001"),
    USER_ALREAY_REGISTERED("1002"),
    VALIDATION_ERROR("2001"),
    INTERNAL_SERVER_ERROR("5001");


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
