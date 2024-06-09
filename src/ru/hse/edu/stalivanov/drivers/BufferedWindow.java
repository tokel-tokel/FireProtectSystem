package ru.hse.edu.stalivanov.drivers;

public class BufferedWindow implements Window, Updatable
{
    private BufferedSupplier<Boolean> bufIsOpened;
    private Driver driver;

    public BufferedWindow(Window window)
    {
        bufIsOpened = new BufferedSupplier<>(window::isOpened, false);
        driver = window;
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
        return driver.getStatus();
    }
}
