package ru.hse.edu.stalivanov;

import ru.hse.edu.stalivanov.drivers.*;
import java.util.ArrayList;
import java.util.Collection;

public class DriverManager
{
    private ArrayList<PreventSystem> alarms;
    private ArrayList<SwitchablePreventSystem> switchableAlarms;
    private Phone phone;

    public DriverManager(Collection<PreventSystem> alarms, Collection<SwitchablePreventSystem> switchableAlarms, Phone phone)
    {
        this.alarms = new ArrayList<>(alarms);
        this.switchableAlarms = new ArrayList<>(switchableAlarms);
        this.phone = phone;
    }

    public DriverManager(Collection<PreventSystem> alarms, Collection<SwitchablePreventSystem> switchableAlarms)
    {
        this(alarms, switchableAlarms, null);
    }

    public Iterable<PreventSystem> getAlarms()
    {
        return alarms;
    }

    public Iterable<SwitchablePreventSystem> getSwitchableAlarms()
    {
        return switchableAlarms;
    }

    public Phone getPhone()
    {
        return phone;
    }
}
