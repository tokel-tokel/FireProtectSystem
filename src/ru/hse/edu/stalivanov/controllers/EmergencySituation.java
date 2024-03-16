package ru.hse.edu.stalivanov.controllers;

import ru.hse.edu.stalivanov.DriverManager;

public class EmergencySituation
{
    private DriverManager driverManager;
    private boolean turnedOn = false;

    public EmergencySituation(DriverManager driverManager)
    {
        this.driverManager = driverManager;
    }

    public void turnOn()
    {
        if(!turnedOn)
        {
            for(var i : driverManager.getAlarms())
                i.turnOn();
            for(var i : driverManager.getCloseableWindows())
                i.close();
            driverManager.getPhone().emergencyCall();
        }
        turnedOn = true;
    }

    public void turnOff()
    {
        if(turnedOn)
        {
            for(var i : driverManager.getSwitchableAlarms())
                i.turnOff();
        }
    }
}
