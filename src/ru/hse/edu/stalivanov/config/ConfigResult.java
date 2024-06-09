package ru.hse.edu.stalivanov.config;

import ru.hse.edu.stalivanov.HandlerManager;
import ru.hse.edu.stalivanov.drivers.Updatable;

import java.util.Collection;

public record ConfigResult(HandlerManager manager, StopStrategy stopStrategy, Collection<Updatable> toUpdate)
{
}
