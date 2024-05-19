package ru.hse.edu.stalivanov.handlers;

import ru.hse.edu.stalivanov.controllers.EmergencySituationController;
import ru.hse.edu.stalivanov.drivers.*;

import java.util.List;

public class SmokeDetectorHandler implements Handler
{
    private SmokeDetector smokeDetector;
    private List<PreventSystem> preventSystems;
    private List<SwitchablePreventSystem> switchablePreventSystems;
    private EmergencySituationController emergencySituation;
    private boolean wasSmoke = false;

    public SmokeDetectorHandler(List<PreventSystem> prevSys, List<SwitchablePreventSystem> swPrevSys, EmergencySituationController emSit)
    {
        preventSystems = prevSys.stream().toList();
        switchablePreventSystems = swPrevSys.stream().toList();
        emergencySituation = emSit;
    }

    @Override
    public void handle()
    {
        boolean isSmoke = smokeDetector.isSmoke();
        if(isSmoke && !wasSmoke)
        {
            emergencySituation.turnOn();
            for(var s : preventSystems)
                s.start();
            for(var s : switchablePreventSystems)
                s.start();
        }
        if(!isSmoke && wasSmoke)
        {
            for(var s : switchablePreventSystems)
                s.stop();
        }
        wasSmoke = isSmoke;
    }
}
