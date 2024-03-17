package ru.hse.edu.stalivanov.drivers;

import java.util.function.Supplier;

public class BufferedSupplier<T> implements Updatable, Supplier<T>
{
    private T bufferedData;
    private Supplier<T> baseSupplier;

    public BufferedSupplier(Supplier<T> baseSupplier)
    {
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
}
