package ru.hse.edu.stalivanov;

import java.util.Scanner;

public class ConsoleStopControl implements StopControl
{
    private MainManager manager;
    private Scanner in = new Scanner(System.in);

    @Override
    public void activate(MainManager manager)
    {
        this.manager = manager;
        Thread thread = new Thread(this::listening);
        thread.start();
    }

    private void listening()
    {
        try
        {
            if(in.next().equalsIgnoreCase("stop"))
            {
                manager.stop();
            }
            else
            {
                listening();
            }
        }
        catch(Exception e)
        {
            manager.stop();
        }
    }
}
