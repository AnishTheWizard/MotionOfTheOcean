package frc.libs.motionoftheocean;

import java.util.ArrayList;

public class State {

    double x;
    double y;
    double theta;

    Object[] otherStates;

    public State(double x, double y, double theta, Object[] otherStates) {
        this.x = x;
        this.y = y;
        this.theta = theta;
        this.otherStates = otherStates;
    }

    public State(double[] pose, Object[] otherStates) {
        this.x = pose[0];
        this.y = pose[1];
        this.theta = pose[2];
        this.otherStates = otherStates;
    }

    public State(double[] pose, ArrayList<Object> otherStates) {

    }


}
