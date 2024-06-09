package ru.hse.edu.stalivanov.drivers;

import io.graversen.minecraft.rcon.MinecraftRcon;
import io.graversen.minecraft.rcon.commands.base.ICommand;

public class MinecraftSmokeDetector implements SmokeDetector
{
    private MinecraftRcon minecraftRcon;
    private int id;
    private ICommand command;
    private DriverStatus status = DriverStatus.OK;

    public MinecraftSmokeDetector(MinecraftRcon minecraftRcon, int id)
    {
        this.minecraftRcon = minecraftRcon;
        this.id = id;
        command = () -> String.format("smokedetector %d", this.id);
    }

    @Override
    public boolean isSmoke()
    {
        String response = minecraftRcon.sendSync(command).getResponseString().trim();
        if(response.equalsIgnoreCase("true"))
        {
            return true;
        }
        else if(response.equalsIgnoreCase("false"))
        {
            return false;
        }
        status = DriverStatus.ERROR;
        System.out.println(response);
        return false;
    }

    @Override
    public DriverStatus getStatus()
    {
        return status;
    }
}
