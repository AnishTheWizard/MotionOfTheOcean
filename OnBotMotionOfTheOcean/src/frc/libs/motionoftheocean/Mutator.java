package frc.libs.motionoftheocean;

import java.util.function.Consumer;

public class Mutator<T> {
    private Consumer<T> function;
    private String name;

    public Mutator(String name, Consumer<T> function) {
        this.name = name;
        this.function = function;
    }

    public void run(T input) {
        function.accept(input);
    }
}
