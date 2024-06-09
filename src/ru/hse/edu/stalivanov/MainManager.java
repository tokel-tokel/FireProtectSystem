package ru.hse.edu.stalivanov;

import ru.hse.edu.stalivanov.config.StopStrategy;

import java.util.concurrent.ExecutionException;

public class MainManager
{
    private MainCycle mainCycle;
    private StopStrategy stopStrategy;
    private StopControl stopControl;
    private UpdateCycle updateCycle;

    public MainManager(MainCycle mainCycle, StopStrategy stopStrategy, UpdateCycle updateCycle)
    {
        this.mainCycle =  mainCycle;
        this.stopStrategy = stopStrategy;
        this.stopControl = new ConsoleStopControl();
        this.updateCycle = updateCycle;
    }

    public void start()
    {
        Thread thread1 = new Thread(mainCycle);
        Thread thread2 = new Thread(updateCycle);
        thread1.start();
        thread2.start();
        stopControl.activate(this);
    }

    public void stop()
    {
        try
        {
            updateCycle.stop().get();
            mainCycle.stop().get();
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
        stopStrategy.stop();
    }
}
