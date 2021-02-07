package com.ly.designpattern.observer;

/**
 * liyang 2020-12-24
 */
public class WeatherStationDemo {
    public static void main(String[] args) {
        WeatherDataSubject subject = new WeatherDataSubject();
        CurrentConditionsDisplayObserver observer = new CurrentConditionsDisplayObserver(subject);
        subject.registerObserver(observer);
        subject.setMeasurements(80, 65, 1.4f);
        subject.setMeasurements(82, 70, 1.2f);
        subject.unregisterObserver(observer);
        subject.setMeasurements(78, 90, 1.6f);
    }
}
