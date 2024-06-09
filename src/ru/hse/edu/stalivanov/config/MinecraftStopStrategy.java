package ru.hse.edu.stalivanov.config;

import io.graversen.minecraft.rcon.service.MinecraftRconService;

public class MinecraftStopStrategy implements StopStrategy
{
    private MinecraftRconService rconService;

    public MinecraftStopStrategy(MinecraftRconService rconService)
    {
        this.rconService = rconService;
    }

    @Override
    public void stop()
    {
        rconService.disconnect();
    }
}
