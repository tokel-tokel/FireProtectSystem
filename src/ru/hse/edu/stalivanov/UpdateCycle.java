package ru.hse.edu.stalivanov;

import ru.hse.edu.stalivanov.drivers.BufferedSmokeDetector;
import ru.hse.edu.stalivanov.drivers.Updatable;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public class UpdateCycle implements Runnable
{
    private AtomicBoolean running = new AtomicBoolean(false);
    private boolean realRunning = false;
    private Collection<Updatable> toUpdate;
    private int interval;

    public UpdateCycle(Collection<Updatable> toUpdate, int interval)
    {
        this.toUpdate = new LinkedList<>(toUpdate);
        this.interval = interval;
    }

    @Override
    public void run()
    {
        running.set(true);
        realRunning = true;
        int delta = interval;
        while(running.get())
        {
            long millis1 = ZonedDateTime.now().toInstant().toEpochMilli();
            if(delta >= interval)
            {
                for(var u : toUpdate)
                {
                    u.update();
                }
                long millis2 = ZonedDateTime.now().toInstant().toEpochMilli();
                delta = (int) (millis2 - millis1);
            }
            else
            {
                long millis2 = ZonedDateTime.now().toInstant().toEpochMilli();
                delta += (int) (millis2 - millis1);
            }
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
                while(!isDone());
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
