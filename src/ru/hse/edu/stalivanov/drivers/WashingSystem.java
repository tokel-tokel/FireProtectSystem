package ru.hse.edu.stalivanov.drivers;

public interface WashingSystem extends Driver
{
    void turnOn();
    void turnOff();
    boolean turnedOn();
}
