package ru.hse.edu.stalivanov.handlers;

import ru.hse.edu.stalivanov.controllers.EmergencySituationController;
import ru.hse.edu.stalivanov.drivers.*;

import java.util.ArrayList;
import java.util.List;

public class SmokeDetectorHandler implements Handler
{
    private SmokeDetector smokeDetector;
    private List<PreventSystem> preventSystems;
    private List<SwitchablePreventSystem> switchablePreventSystems;
    private EmergencySituationController emergencySituation;
    private boolean wasSmoke = false;

    public SmokeDetectorHandler(SmokeDetector smDe, List<PreventSystem> prevSys, List<SwitchablePreventSystem> swPrevSys, EmergencySituationController emSit)
    {
        smokeDetector = smDe;
        preventSystems = new ArrayList<>(prevSys);
        switchablePreventSystems = new ArrayList<>(swPrevSys);
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
            {
                s.start();
            }
        }
        if(!isSmoke && wasSmoke)
        {
            for(var s : switchablePreventSystems)
                s.stop();
        }
        wasSmoke = isSmoke;
    }
}
