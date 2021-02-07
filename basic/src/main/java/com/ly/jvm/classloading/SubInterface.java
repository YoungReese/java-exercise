package com.ly.jvm.classloading;

/**
 * 子接口只能extends父接口
 * 子类只能implements父接口
 */
public interface SubInterface extends SuperInterface {
    static final int SUB_CONSTANT = 2000;
}
