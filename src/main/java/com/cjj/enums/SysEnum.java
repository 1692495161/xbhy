package com.cjj.enums;

/**
 * @author cjj
 * @date 2020/6/28
 * @description
 */
public enum SysEnum {

    COOKIE_NAME("cookieName");

    private String value;

    SysEnum(String value) {
        this.value = value;
    }

    SysEnum() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
