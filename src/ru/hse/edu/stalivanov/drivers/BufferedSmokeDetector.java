package ru.hse.edu.stalivanov.drivers;

public class BufferedSmokeDetector implements SmokeDetector, Updatable
{
    private BufferedSupplier<Boolean> bufIsSmoke;
    private Driver driver;

    public BufferedSmokeDetector(SmokeDetector detector)
    {
        bufIsSmoke = new BufferedSupplier<>(detector::isSmoke,  false);
        driver = detector;
    }

    @Override
    public boolean isSmoke()
    {
        return bufIsSmoke.get();
    }

    @Override
    public void update()
    {
        bufIsSmoke.update();
    }

    @Override
    public DriverStatus getStatus()
    {
        return driver.getStatus();
    }
}
