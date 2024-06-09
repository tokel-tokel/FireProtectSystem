package ru.hse.edu.stalivanov.config;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class ConfigFactory
{
    private File file;

    public ConfigFactory(File file)
    {
        this.file = file;
    }

    public Configurator getConfig()
    {
        Map<String, Object> map = null;
        try(FileInputStream fis = new FileInputStream(file))
        {
            Yaml yaml = new Yaml();
            map = yaml.load(fis);
        }
        catch(IOException e)
        {
            return null;
        }
        if(map == null)
            return null;
        if(!map.containsKey("type"))
            return null;
        String type;
        if(map.get("type") instanceof String str)
            type = str;
        else
            return null;
        if(type.equalsIgnoreCase("minecraft"))
        {
            try
            {
                FileInputStream fis = new FileInputStream(file);
                return new MinecraftConfigurator(fis);
            }
            catch(IOException e)
            {
                return null;
            }
        }
        return null;
    }
}
