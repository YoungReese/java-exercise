package com.ly.designpattern.observer;

/**
 * liyang 2020-12-24
 * 主题发布者
 */
public interface Subject {
    public void registerObserver(Observer observer);
    public void unregisterObserver(Observer observer);
    public void notifyObservers();
}
