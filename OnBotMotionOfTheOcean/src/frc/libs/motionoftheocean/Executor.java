package frc.libs.motionoftheocean;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;

public class Executor {
    String pathName;

    ScheduledExecutorService service;

    double delay;

    ArrayList<Consumer<Double>> dynamicExecutors;
    ArrayList<Runnable> staticExecutor;

    ArrayList<State> executable;

    boolean isRunning;

    Consumer<double[]> toPose;

    int index;

    public void execute() {

    }

    public void reset() {

    }

    public void startExecutor() {

    }

    public void stopExecutor() {

    }
}
