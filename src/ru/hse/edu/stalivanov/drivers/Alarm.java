package ru.hse.edu.stalivanov.drivers;

public interface Alarm extends Driver
{
    void turnOn();
    boolean isTurnedOn();
}
