package ru.hse.edu.stalivanov.drivers;

public class BufferedSmokeDetector implements SmokeDetector, Updatable
{
    private SmokeDetector base;
    private boolean bufIsSmoke;

    public BufferedSmokeDetector(SmokeDetector base)
    {
        this.base = base;
    }

    @Override
    public boolean isSmoke()
    {
        return bufIsSmoke;
    }

    @Override
    public void update()
    {
        bufIsSmoke = base.isSmoke();
    }
}
