package ru.hse.edu.stalivanov.drivers;

public interface PreventSystem extends Driver
{
    void start();
    boolean isActivated();
}
