package ru.hse.edu.stalivanov.drivers;

import java.util.function.Supplier;

public class BufferedSmokeDetector extends BufferedDriver<Boolean> implements SmokeDetector
{
    public BufferedSmokeDetector(SmokeDetector detector)
    {
        super(detector::isSmoke, detector);
    }

    @Override
    public boolean isSmoke()
    {
        return super.get();
    }
}
