package ru.hse.edu.stalivanov.controllers;

import ru.hse.edu.stalivanov.DriverManager;

public class EmergencySituationController
{
    private DriverManager driverManager;
    private boolean turnedOn = false;

    public EmergencySituationController(DriverManager driverManager)
    {
        this.driverManager = driverManager;
    }

    public void turnOn()
    {
        for(var i : driverManager.getAlarms())
            i.start();
        if(driverManager.getPhone() != null)
            driverManager.getPhone().emergencyCall();
        turnedOn = true;
    }

    public void turnOff()
    {
        if(turnedOn)
        {
            for(var i : driverManager.getSwitchableAlarms())
                i.stop();
        }
    }
}
