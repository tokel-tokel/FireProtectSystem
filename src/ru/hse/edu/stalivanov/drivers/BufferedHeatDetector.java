package ru.hse.edu.stalivanov.drivers;

import java.util.function.Supplier;

public class BufferedHeatDetector extends BufferedDriver<HeatMap> implements HeatDetector
{
    public BufferedHeatDetector(HeatDetector detector)
    {
        super(detector::getHeatMap, detector);
    }

    @Override
    public HeatMap getHeatMap()
    {
        return super.get();
    }
}
