package frc.libs.motionoftheocean;

public class Executable {

    private Runnable function;
    private String name;

    public Executable(String name, Runnable function) {
        this.name = name;
        this.function = function;
    }

    public void run() {
        function.run();
    }
}
