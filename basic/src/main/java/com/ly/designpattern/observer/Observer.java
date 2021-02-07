package com.ly.designpattern.observer;

/**
 * liyang 2020-12-24
 * 观察者
 */
public interface Observer {
    public void update(float temperature, float humidity, float pressure);
}
