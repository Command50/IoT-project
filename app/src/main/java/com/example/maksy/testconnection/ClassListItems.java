package com.example.maksy.testconnection;

public class ClassListItems
{
    private String SensorName;
    private String Temperature;

    public ClassListItems(String SensorName, String temperature)
    {
        this.Temperature = temperature;
        this.SensorName = SensorName;
    }

    public String getSensorName() {
        return SensorName;
    }

    public String getTemperature() {
        return Temperature;
    }
}
