package ru.hse.edu.stalivanov;

import org.yaml.snakeyaml.Yaml;
import ru.hse.edu.stalivanov.config.ConfigFactory;
import ru.hse.edu.stalivanov.config.ConfigResult;
import ru.hse.edu.stalivanov.config.Configurator;

import java.io.File;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("config/config.yaml");
        if(!file.exists())
            throw new IllegalArgumentException();

        Configurator configurator;
        ConfigResult config;
        configurator = new ConfigFactory(file).getConfig();

        if(configurator == null)
            throw new IllegalArgumentException();
        config = configurator.config();

        MainManager manager = new MainManager(new MainCycle(config.manager()), config.stopStrategy(),
                new UpdateCycle(config.toUpdate(), 1000));
        manager.start();
    }
}