package ru.hse.edu.stalivanov;

import ru.hse.edu.stalivanov.drivers.*;
import java.util.ArrayList;

public class DriverManager
{
    private ArrayList<SmokeDetector> smokeDetectors;
    private ArrayList<HeatDetector> heatDetectors;
    private ArrayList<Window> windows;
    private ArrayList<Alarm> alarms;
    private ArrayList<SwitchableAlarm> switchableAlarms;
    private Phone phone;

    private ArrayList<Updatable> updatableDevices;

    public DriverManager()
    {

    }

    public Iterable<Alarm> getAlarms()
    {
        return alarms;
    }

    public Iterable<SwitchableAlarm> getSwitchableAlarms()
    {
        return switchableAlarms;
    }

    public Phone getPhone()
    {
        return phone;
    }
}
