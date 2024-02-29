package ru.hse.edu.stalivanov.drivers;

public class BufferedHeatDetector implements HeatDetector, Updatable
{
    private HeatDetector base;
    private HeatMap bufHeatMap;

    public BufferedHeatDetector(HeatDetector base)
    {
        this.base = base;
    }

    @Override
    public HeatMap getHeatMap()
    {
        return bufHeatMap;
    }

    @Override
    public void update()
    {
        bufHeatMap = base.getHeatMap();
    }
}
