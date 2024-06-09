package ru.hse.edu.stalivanov.drivers;

import io.graversen.minecraft.rcon.MinecraftRcon;
import io.graversen.minecraft.rcon.commands.base.ICommand;

public class MinecraftAlarm implements PreventSystem
{
    private boolean activated = false;
    private MinecraftRcon minecraftRcon;
    private int id;
    private ICommand command;

    public MinecraftAlarm(MinecraftRcon minecraftRcon, int id)
    {
        this.minecraftRcon = minecraftRcon;
        this.id = id;
        command = () -> String.format("alarm %d activate", this.id);
    }

    @Override
    public void start()
    {
        minecraftRcon.sendAsync(command);
        activated = true;
    }

    @Override
    public boolean isActivated()
    {
        return activated;
    }
}
