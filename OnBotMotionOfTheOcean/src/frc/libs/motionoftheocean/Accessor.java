package frc.libs.motionoftheocean;

import java.util.function.Supplier;

public class Accessor<T> {
    private Supplier<T> function;

    private String name;

    public Accessor(String name, Supplier<T> function) {
        this.name = name;
        this.function = function;
    }

    public T get() {
        return function.get();
    }
}
