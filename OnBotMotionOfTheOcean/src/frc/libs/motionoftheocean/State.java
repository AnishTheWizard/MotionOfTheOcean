package frc.libs.motionoftheocean;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class State {

    double x;
    double y;
    double theta;

    double v;
    double a;

    ArrayList<Double> dynamicStates;
    ArrayList<Boolean> binaryStates;

    ArrayList<BooleanSupplier> parallelConditions;
    ArrayList<BooleanSupplier> raceConditions;

    public State(double[] chassisMotion, ArrayList<Double> dynamicStates, ArrayList<Boolean> binaryStates, ArrayList<BooleanSupplier> parallelConditions, ArrayList<BooleanSupplier> raceConditions) {
        x = chassisMotion[0];
        y = chassisMotion[1];
        theta = chassisMotion[2];

        v = chassisMotion[3];
        a = chassisMotion[4];

        this.dynamicStates = dynamicStates;
        this.binaryStates = binaryStates;

        this.parallelConditions = parallelConditions;
        this.raceConditions = raceConditions;
    }

    @Override
    public String toString(){
        return "deez";
    }


}
