package ru.hse.edu.stalivanov.handlers;

import ru.hse.edu.stalivanov.controllers.EmergencySituationController;
import ru.hse.edu.stalivanov.drivers.*;

import java.util.List;

public class SmokeDetectorHandler implements Handler
{
    private SmokeDetector smokeDetector;
    private List<WashingSystem> washingSystems;
    private List<PowerSwitch> switches;
    private List<AutoCloseableWidow> widows;
    private EmergencySituationController emergencySituation;
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
            for(var i : washingSystems)
                i.turnOn();
            for(var i : switches)
                i.turnOff();
            for(var i : widows)
                i.close();
        }
        if(!isSmoke && wasSmoke)
        {
            for(var s : washingSystems)
                s.turnOff();
        }
        wasSmoke = isSmoke;
    }
}
