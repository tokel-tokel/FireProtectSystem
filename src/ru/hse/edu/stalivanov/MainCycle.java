package ru.hse.edu.stalivanov;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainCycle implements Runnable
{
    private HandlerManager handlers;
    private AtomicBoolean running = new AtomicBoolean(false);

    public MainCycle(HandlerManager handlers)
    {
        this.handlers = handlers;
    }

    @Override
    public void run()
    {
        running.set(true);
        while(running.get())
        {
            handlers.handleAll();
        }
    }

    public void stop()
    {
        running.set(false);
    }
}
