package ru.hse.edu.stalivanov.drivers;

public interface Driver
{
    default DriverStatus getStatus()
    {
        return DriverStatus.UNDEFINED;
    }
}
