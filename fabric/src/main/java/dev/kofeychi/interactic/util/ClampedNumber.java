package dev.kofeychi.interactic.util;

import dev.kofeychi.interactic.figura.api.task.TextRendererAPI;

import java.util.Comparator;

public abstract class ClampedNumber<T> {
    public abstract T get();
    public abstract void set(T value);
}
