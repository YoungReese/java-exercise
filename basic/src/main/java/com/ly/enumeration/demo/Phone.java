package com.ly.enumeration.demo;

import java.util.HashMap;
import java.util.Map;

public enum Phone {
    APPLE((byte) 0, "苹果"), SAMSUNG((byte) 1, "三星");

    private byte value;
    private String desc;

    Phone(byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public byte getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    private static Map<Byte, Phone> map = new HashMap<>();

    static {
        for (Phone phone : Phone.values()) {
            map.put(Byte.valueOf(phone.getValue()),phone);
        }
    }

    public static Phone valueOf(byte value) {
        return map.get(Byte.valueOf(value));
    }

}
