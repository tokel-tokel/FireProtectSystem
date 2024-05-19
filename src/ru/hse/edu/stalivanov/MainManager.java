package ru.hse.edu.stalivanov;

import ru.hse.edu.stalivanov.config.StopStrategy;

public class MainManager
{
    private StopStrategy stopStrategy;

    public void stop()
    {
        stopStrategy.stop();
    }
}
