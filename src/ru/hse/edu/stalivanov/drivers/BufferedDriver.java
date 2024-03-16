package ru.hse.edu.stalivanov.drivers;

import java.util.function.Supplier;

public class BufferedDriver<T> implements Updatable, Supplier<T>, Driver
{
    private T bufferedData;
    private Supplier<T> baseSupplier;
    private Driver baseDriver;

    public BufferedDriver(Supplier<T> baseSupplier, Driver baseDriver)
    {
        this.baseDriver = baseDriver;
        this.baseSupplier = baseSupplier;
    }

    @Override
    public void update()
    {
        bufferedData = baseSupplier.get();
    }

    @Override
    public T get()
    {
        return bufferedData;
    }

    @Override
    public DriverStatus getStatus()
    {
        return baseDriver.getStatus();
    }
}
