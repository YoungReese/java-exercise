package com.ly.enumeration;

public enum Direction {
    FRONT("front", "前面"), BEHIND("behind", "后面"), LEFT("left", "左边"), RIGHT("right", "右边");

    private String name;
    private String desc;

    Direction(String name, String desc) {
        System.out.println("initialize a enum instance");
        this.name = name;
        this.desc = desc;
    }


    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
