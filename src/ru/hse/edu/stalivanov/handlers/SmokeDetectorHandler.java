package ru.hse.edu.stalivanov.handlers;

import ru.hse.edu.stalivanov.drivers.SmokeDetector;
import ru.hse.edu.stalivanov.drivers.WashingSystem;

import java.util.ArrayList;
import java.util.LinkedList;

public class SmokeDetectorHandler implements Handler
{
    private SmokeDetector smokeDetector;
    private ArrayList<WashingSystem> washingSystems;
    private EmergencySituation emergencySituation;
    private boolean wasSmoke = false;

    public SmokeDetectorHandler()
    {

    }

    @Override
    public void handle()
    {
        boolean isSmoke = smokeDetector.isSmoke();
        if(isSmoke && !wasSmoke)
        {
            emergencySituation.turnOn();
            for(var s : washingSystems)
                s.turnOn();
        }
        if(!isSmoke && wasSmoke)
        {
            for(var s : washingSystems)
                s.turnOff();
        }
        wasSmoke = isSmoke;
    }
}
