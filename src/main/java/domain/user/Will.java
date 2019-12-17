/*
 * @(#)Will.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package domain.user;

public enum Will {
    Hit("y"),
    Stay("n");

    private String value;

    Will(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
