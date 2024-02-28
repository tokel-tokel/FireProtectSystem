package ru.hse.edu.stalivanov;

import ru.hse.edu.stalivanov.drivers.*;
import java.util.ArrayList;

public class DriverManager
{
    private ArrayList<SmokeDetector> smokeDetectors;
    private ArrayList<HeatDetector> heatDetectors;
    private ArrayList<Window> windows;
    private ArrayList<Alarm> alarms;
    private ArrayList<AutoCloseableWidow> closeableWidows;
    private ArrayList<WashingSystem> washingSystems;
    private Phone phone;
}
