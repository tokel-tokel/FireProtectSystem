package ru.hse.edu.stalivanov.config;

import io.graversen.minecraft.rcon.MinecraftRcon;
import io.graversen.minecraft.rcon.service.ConnectOptions;
import io.graversen.minecraft.rcon.service.MinecraftRconService;
import io.graversen.minecraft.rcon.service.RconDetails;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import ru.hse.edu.stalivanov.DriverManager;
import ru.hse.edu.stalivanov.HandlerManager;
import ru.hse.edu.stalivanov.controllers.EmergencySituationController;
import ru.hse.edu.stalivanov.drivers.*;
import ru.hse.edu.stalivanov.handlers.Handler;
import ru.hse.edu.stalivanov.handlers.SmokeDetectorHandler;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.*;

public class MinecraftConfigurator implements Configurator
{
    private HashMap<Integer, SmokeDetector> smokeDetectors;
    private List<PreventSystem> alarms;
    private HashMap<Integer, SwitchablePreventSystem> washingSystems;
    private InputStream input;
    private List<Handler> handlers;
    private List<Updatable> toUpdate;
    private MinecraftRconService rconService;

    public MinecraftConfigurator(InputStream inputStream)
    {
        input = inputStream;
        smokeDetectors = new HashMap<>();
        alarms = new LinkedList<>();
        washingSystems = new HashMap<>();
        handlers = new LinkedList<>();
        toUpdate = new LinkedList<>();
    }

    @Override
    public ConfigResult config()
    {
        Yaml yaml = new Yaml(new Constructor(Config.class, new LoaderOptions()));
        Config config = yaml.load(input);
        try
        {
            input.close();
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException();
        }
        if(config == null)
            throw new IllegalArgumentException();
        Options options = config.getOptions();

        rconService = new MinecraftRconService(new RconDetails(options.getIp(), options.getPort(), options.getPassword()),
                ConnectOptions.defaults());

        rconService.connectBlocking(Duration.ofSeconds(10));

        MinecraftRcon minecraftRcon = null;
        try
        {
            minecraftRcon = rconService.minecraftRcon().orElseThrow(IllegalStateException::new);
        }
        catch(IllegalStateException e)
        {
            e.printStackTrace(System.err);
            System.exit(14);
        }

        if(minecraftRcon == null)
            throw new IllegalArgumentException();

        for(var i : options.getSmokedetectors())
        {
            var b = new BufferedSmokeDetector(new MinecraftSmokeDetector(minecraftRcon, i));
            smokeDetectors.put(i, b);
            toUpdate.add(b);
        }

        for(var i : options.getStickypistons())
            washingSystems.put(i, new MinecraftWashingSystem(minecraftRcon, i));
        for(var i : options.getAlarms())
        {
            alarms.add(new MinecraftAlarm(minecraftRcon, i));
        }


        DriverManager driverManager = new DriverManager(alarms, new ArrayList<>());
        EmergencySituationController controller = new EmergencySituationController(driverManager);

        for(Link link : options.getLinks())
        {
            LinkedList<SwitchablePreventSystem> systems = new LinkedList<>();
            SmokeDetector smokeDetector = smokeDetectors.get(link.getSmokedetector());
            if(smokeDetector == null) err();
            for(var i : link.getStickypistons())
            {
                SwitchablePreventSystem system = washingSystems.get(i);
                if(system == null)
                    err();
                systems.add(system);
            }
            handlers.add(new SmokeDetectorHandler(smokeDetector, new ArrayList<>(), systems, controller));
        }

        HandlerManager handlerManager = new HandlerManager(handlers);
        StopStrategy stopStrategy = new MinecraftStopStrategy(rconService);

        return new ConfigResult(handlerManager, stopStrategy, toUpdate);
    }

    private void err()
    {
        rconService.disconnect();
        throw new IllegalArgumentException();
    }

    public static class Config
    {
        public String type;
        public Options options;

        public String getType()
        {
            return type;
        }

        public Options getOptions()
        {
            return options;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        public void setOptions(Options options)
        {
            this.options = options;
        }
    }

    public static class Options
    {
        public String ip;
        public String password;
        public int port;
        public List<Integer> smokedetectors;
        public List<Integer> alarms;
        public List<Integer> stickypistons;
        public List<Link> links;

        public String getPassword()
        {
            return password;
        }

        public String getIp()
        {
            return ip;
        }

        public int getPort()
        {
            return port;
        }

        public List<Integer> getSmokedetectors()
        {
            return smokedetectors;
        }

        public List<Integer> getStickypistons()
        {
            return stickypistons;
        }

        public List<Integer> getAlarms()
        {
            return alarms;
        }

        public List<Link> getLinks()
        {
            return links;
        }

        public void setIp(String ip)
        {
            this.ip = ip;
        }

        public void setPort(int port)
        {
            this.port = port;
        }

        public void setStickypistons(List<Integer> stickypistons)
        {
            this.stickypistons = new ArrayList<>(stickypistons);
        }

        public void setAlarms(List<Integer> alarms)
        {
            this.alarms = new ArrayList<>(alarms);
        }

        public void setLinks(List<Link> links)
        {
            this.links = new ArrayList<>(links);
        }

        public void setSmokedetectors(List<Integer> smokedetectors)
        {
            this.smokedetectors = new ArrayList<>(smokedetectors);
        }

        public void setPassword(String password)
        {
            this.password = password;
        }
    }

    public static class Link
    {
        public int smokedetector;
        public List<Integer> stickypistons;

        public int getSmokedetector()
        {
            return smokedetector;
        }

        public List<Integer> getStickypistons()
        {
            return stickypistons;
        }

        public void setSmokedetector(int smokedetector)
        {
            this.smokedetector = smokedetector;
        }

        public void setStickypistons(List<Integer> stickypistons)
        {
            this.stickypistons = new ArrayList<>(stickypistons);
        }
    }
}
