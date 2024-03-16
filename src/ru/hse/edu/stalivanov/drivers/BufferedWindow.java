package ru.hse.edu.stalivanov.drivers;

import java.util.function.Supplier;

public class BufferedWindow extends BufferedDriver<Boolean> implements Window
{
    public BufferedWindow(Window window)
    {
        super(window::isOpened, window);
    }

    @Override
    public boolean isOpened()
    {
        return super.get();
    }
}
