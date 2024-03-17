package ru.hse.edu.stalivanov.drivers;

public class BufferedHeatDetector implements HeatDetector, Updatable
{
    private BufferedSupplier<HeatMap> bufHeatMap;
    private Driver driver;

    public BufferedHeatDetector(HeatDetector detector)
    {
        bufHeatMap = new BufferedSupplier<>(detector::getHeatMap);
        driver = detector;
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
        return driver.getStatus();
    }
}
