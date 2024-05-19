package ru.hse.edu.stalivanov;

import ru.hse.edu.stalivanov.handlers.Handler;
import java.util.List;

public class HandlerManager
{
    private List<Handler> handlers;

    public HandlerManager(List<Handler> handlers)
    {
        this.handlers = handlers.stream().toList();
    }

    public void addHandler(Handler handler)
    {
        handlers.add(handler);
    }

    public void handleAll()
    {
        for(var h : handlers)
        {
            h.handle();
        }
    }
}
