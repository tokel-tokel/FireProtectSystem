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
        if(!turnedOn)
        {
            for(var i : driverManager.getAlarms())
                i.turnOn();
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
