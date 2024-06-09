package ru.hse.edu.stalivanov.drivers;

import io.graversen.minecraft.rcon.MinecraftRcon;
import io.graversen.minecraft.rcon.commands.base.ICommand;

public class MinecraftWashingSystem implements SwitchablePreventSystem
{
    private boolean activated = false;
    private MinecraftRcon minecraftRcon;
    private Command command;
    private int id;

    public MinecraftWashingSystem(MinecraftRcon minecraftRcon, int id)
    {
        this.minecraftRcon = minecraftRcon;
        this.id = id;
        command = new Command();
    }

    @Override
    public void start()
    {
        command.setActivated(true);
        minecraftRcon.sendAsync(command);
        activated = true;
    }

    @Override
    public boolean isActivated()
    {
        return activated;
    }

    @Override
    public void stop()
    {
        command.setActivated(false);
        minecraftRcon.sendAsync(command);
        activated = false;
    }

    private class Command implements ICommand
    {
        private String details = "deactivate";

        public void setActivated(boolean b)
        {
            details = b ? "activate" : "deactivate";
        }

        @Override
        public String command()
        {
            return String.format("stickypiston %d %s", id, details);
        }
    }
}
