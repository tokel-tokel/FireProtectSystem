package ru.hse.edu.stalivanov.drivers;

public class BufferedWindow implements Window, Updatable
{
    private Window base;
    private boolean bufIsOpened;

    public BufferedWindow(Window base)
    {
        this.base = base;
    }

    @Override
    public void update()
    {
        bufIsOpened = base.isOpened();
    }

    @Override
    public boolean isOpened()
    {
        return bufIsOpened;
    }
}
