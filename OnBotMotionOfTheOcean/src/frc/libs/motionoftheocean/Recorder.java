package frc.libs.motionoftheocean;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class Recorder {
    String pathName;

    boolean isPathCreated;

    double delay;

    boolean isRecording;

    ScheduledExecutorService service;

    ArrayList<DoubleSupplier> dynamicStateAccessors;
    ArrayList<BooleanSupplier> binaryStateAccessors;

    Supplier<double[]> getChassisMotion;

    ArrayList<State> recording;

    public Recorder(String pathName, boolean isPathCreated, double delay, ArrayList<DoubleSupplier> dynamicStateAccessors, ArrayList<BooleanSupplier> binaryStateAccessors, Supplier<double[]> getChassisMotion) {
        this.pathName = pathName;
        this.isPathCreated = isPathCreated;
        this.delay = delay;
        this.dynamicStateAccessors = dynamicStateAccessors;
        this.binaryStateAccessors = binaryStateAccessors;
        this.getChassisMotion = getChassisMotion;
        service = Executors.newSingleThreadScheduledExecutor();
        recording = new ArrayList<State>();
    }

    private void record() {
        if(!isPathCreated) {
            double[] chassisMotion = getChassisMotion.get();
            ArrayList<Double> dynamicStates = new ArrayList<Double>();
            ArrayList<Boolean> binaryStates = new ArrayList<Boolean>();

            for(DoubleSupplier dynamicStateAccessor : dynamicStateAccessors) {
                dynamicStates.add(dynamicStateAccessor.getAsDouble());
            }
            for(BooleanSupplier binaryStateAccessor : binaryStateAccessors) {
                binaryStates.add(binaryStateAccessor.getAsBoolean());
            }
            recording.add(new State(chassisMotion, dynamicStates, binaryStates, new ArrayList<BooleanSupplier>(), new ArrayList<BooleanSupplier>()));
        }
    }

    public void export() {

    }

    public void reset() {}

    public void start() {
        isRecording = true;
        service.scheduleAtFixedRate(this::record, 0, 10, TimeUnit.MILLISECONDS);
    }

    public void stop() {}

    public ArrayList<State> getRecording() {
        return new ArrayList<State>();
    }

    public void configure() {}
}
