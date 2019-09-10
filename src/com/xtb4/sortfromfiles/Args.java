package com.xtb4.sortfromfiles;

public enum Args {
    TYPE_STRING("-s"),
    TYPE_INTEGER("-i"),

    ORDER_ASC("-a"),
    ORDER_DESC("-d");

    public static final Args DEFAULT_ORDER = ORDER_ASC;

    public final String token;

    Args(String token) {
        this.token = token;
    }
}