package ru.hse.edu.stalivanov.drivers;

public class BufferedWindow implements Window, Updatable
{
    private BufferedSupplier<Boolean> bufIsOpened;
    private Driver base;

    public BufferedWindow(Window window)
    {
        bufIsOpened = new BufferedSupplier<>(window::isOpened);
        base = window;
    }

    @Override
    public boolean isOpened()
    {
        return bufIsOpened.get();
    }

    @Override
    public void update()
    {
        bufIsOpened.update();
    }

    @Override
    public DriverStatus getStatus()
    {
        return base.getStatus();
    }
}
