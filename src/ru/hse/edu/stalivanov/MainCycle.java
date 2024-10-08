package ru.hse.edu.stalivanov;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainCycle implements Runnable
{
    private HandlerManager handlers;
    private AtomicBoolean running = new AtomicBoolean(false);
    private boolean realRunning = false;

    public MainCycle(HandlerManager handlers)
    {
        this.handlers = handlers;
    }

    @Override
    public void run()
    {
        running.set(true);
        realRunning = true;
        while(running.get())
        {
            handlers.handleAll();
        }
        realRunning = false;
    }

    public Future<Boolean> stop()
    {
        running.set(false);
        return new Future<>()
        {
            private boolean b = realRunning;

            @Override
            public boolean cancel(boolean b)
            {
                return false;
            }

            @Override
            public boolean isCancelled()
            {
                return false;
            }

            @Override
            public boolean isDone()
            {
                if(!b)
                    return true;
                else
                    return !realRunning;
            }

            @Override
            public Boolean get() throws InterruptedException, ExecutionException
            {
                while(!isDone()) ;
                return true;
            }

            @Override
            public Boolean get(long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException
            {
                return get();
            }
        };
    }
}
