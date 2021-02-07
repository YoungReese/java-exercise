package com.ly.designpattern.observer;

/**
 * liyang 2020-12-24
 */
public class CurrentConditionsDisplayObserver implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherData;

    public CurrentConditionsDisplayObserver(Subject weatherData) {
        this.weatherData = weatherData;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        System.out.println("Current conditions:\n\t"
                + "Temperature: " + temperature + " F\n\t"
                + "Humidity: " + humidity + " %\n\t"
                + "Pressure: " + pressure + "*10^5 Pa");
    }
}
