package ru.hse.edu.stalivanov.drivers;

import java.util.function.Supplier;

public class BufferedHeatDetector implements HeatDetector, Updatable
{
    private HeatDetector base;
    private BufferedSupplier<HeatMap> bufHeatMap;

    public BufferedHeatDetector(HeatDetector detector)
    {
        bufHeatMap = new BufferedSupplier<>(detector::getHeatMap, null);
        base = detector;
    }

    @Override
    public HeatMap getHeatMap()
    {
        return bufHeatMap.get();
    }

    @Override
    public void update()
    {
        bufHeatMap.update();
    }

    @Override
    public DriverStatus getStatus()
    {
        return base.getStatus();
    }
}
