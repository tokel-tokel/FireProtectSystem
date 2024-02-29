package ru.hse.edu.stalivanov.drivers;

import java.util.ArrayList;

public class LinkedSmokeDetector implements SmokeDetector
{
    private SmokeDetector base;
    private ArrayList<WashingSystem> washingSystems;

    public LinkedSmokeDetector(SmokeDetector base, ArrayList<WashingSystem> washingSystems)
    {
        this.base = base;
        this.washingSystems = new ArrayList<>(washingSystems);
    }

    @Override
    public boolean isSmoke()
    {
        return base.isSmoke();
    }

    public Iterable<WashingSystem> getWashingSystems()
    {
        return washingSystems;
    }
}
